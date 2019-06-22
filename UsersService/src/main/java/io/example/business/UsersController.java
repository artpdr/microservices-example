package io.example.business;

import com.rabbitmq.client.ConnectionFactory;
import io.example.client.EmailsServiceClient;
import io.example.dao.Postgresql;
import io.example.dto.UserDTO;
import io.example.grpc.emails.EmailConfirmation;
import io.example.grpc.emails.PasswordConfirmation;
import io.example.grpc.users.*;
import io.example.rabbitmq.Producer;
import io.grpc.netty.shaded.io.netty.handler.codec.http.HttpResponseStatus;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;

public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    public static ResponseStatus createUser(User user) {
        // TODO: Validate inputs
        try {
            if(!Postgresql.getInstance().createUser(
                    new UserDTO.Builder()
                            .setUsername(user.getUsername())
                            .setFirstName(user.getFirstName())
                            .setLastName(user.getLastName())
                            .setEmail(user.getEmail())
                            .setPasswordHash(new BCryptPasswordEncoder().encode(user.getPassword()))
                            .build())) {
                return ResponseStatus.newBuilder().setCode(400).setDetails("No user created").build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (e.getSQLState().equals(Postgresql.UNIQUE_VIOLATION)){
                return ResponseStatus.newBuilder().setCode(409).setDetails(e.getMessage()).build();
            }
            return ResponseStatus.newBuilder().setCode(500).setDetails(e.getMessage()).build();
        }
        Producer.getInstance().produceMsg("emails-confirmation", new ConnectionFactory(),
                user.getUsername(), user.getEmail());
        // TODO: Handle mail failures...
        return ResponseStatus.newBuilder().setCode(201).setDetails("OK").build();
    }

    public static UserResponse readUser(Username username) {
        try {
            UserDTO userDTO = Postgresql.getInstance().readUser(username.getUsername());
            if (userDTO.isConfirmed() && !userDTO.isDeleted()){
                return UserResponse.newBuilder()
                        .setUser(User.newBuilder()
                                .setUsername(userDTO.getUsername())
                                .setFirstName(userDTO.getFirstName())
                                .setLastName(userDTO.getLastName())
                                .setEmail(userDTO.getEmail()))
                        .setResponseStatus(ResponseStatus.newBuilder()
                                .setCode(200)
                                .setDetails("OK")
                                .build())
                        .build();
            }
            if (userDTO.isConfirmed()){
                return UserResponse.newBuilder()
                        .setResponseStatus(
                                ResponseStatus.newBuilder()
                                        .setCode(404)
                                        .setDetails("User does not exist.")
                                        .build())
                        .build();
            }
            return UserResponse.newBuilder()
                    .setResponseStatus(
                            ResponseStatus.newBuilder()
                                    .setCode(404)
                                    .setDetails("Email associated with this user isn't confirmed.")
                                    .build())
                    .build();
        } catch (SQLException e) {
            e.printStackTrace();
            return UserResponse.newBuilder()
                    .setResponseStatus(
                            ResponseStatus.newBuilder()
                                    .setCode(500)
                                    .setDetails(e.getMessage())
                                    .build())
                    .build();
        }
    }

    public static ResponseStatus updateUser(User user) {
        try {
            if(Postgresql.getInstance().updateUser(
                    new UserDTO.Builder()
                            .setUsername(user.getUsername())
                            .setFirstName(user.getFirstName())
                            .setLastName(user.getLastName())
                            .setEmail(user.getEmail())
                            .setPasswordHash(new BCryptPasswordEncoder().encode(user.getPassword()))
                            .build())){
                return ResponseStatus.newBuilder()
                        .setCode(200)
                        .setDetails("OK")
                        .build();
            }
            else{
                return ResponseStatus.newBuilder()
                        .setCode(404)
                        .setDetails("User wasn't updated.")
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseStatus.newBuilder()
                    .setCode(500)
                    .setDetails(e.getMessage())
                    .build();
        }
    }

    public static ResponseStatus deleteUser(Username username) {
        try {
            if(Postgresql.getInstance().deleteUser(username.getUsername())){
                return ResponseStatus.newBuilder()
                        .setCode(200)
                        .setDetails("OK")
                        .build();
            }
            else{
                return ResponseStatus.newBuilder()
                        .setCode(500)
                        .setDetails("User wasn't deleted.")
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseStatus.newBuilder()
                    .setCode(500)
                    .setDetails(e.getMessage())
                    .build();
        }
    }

    public static ResponseStatus confirmUserEmail(UsernameAndToken request) {
        EmailsServiceClient emailsServiceClient = EmailsServiceClient.getInstance();
        io.example.grpc.emails.ResponseStatus responseStatus = emailsServiceClient.getBlockingStub().confirmEmail(
                EmailConfirmation.newBuilder()
                        .setUsername(request.getUsername())
                        .setToken(request.getToken())
                        .build());

        logger.info("confirmUserEmail GRPC response. CODE: {}. Details: {}.", responseStatus.getCode(), responseStatus.getDetails());

        if(responseStatus.getCode() == HttpResponseStatus.OK.code()){
            try {
                if(Postgresql.getInstance().updateConfirmedUserFlag(
                        request.getUsername(), true)){
                    return ResponseStatus.newBuilder()
                            .setCode(200)
                            .setDetails("OK")
                            .build();
                }
                else{
                    return ResponseStatus.newBuilder()
                            .setCode(500)
                            .setDetails("Email wasn't confirmed. Try again.")
                            .build();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return ResponseStatus.newBuilder()
                        .setCode(500)
                        .setDetails(e.getMessage())
                        .build();
            }
        }

        return ResponseStatus.newBuilder()
                .setCode(responseStatus.getCode())
                .setDetails(responseStatus.getDetails())
                .build();
    }

    public static TokenResponse authenticateUser(UsernameAndPassword request, String jwtSecret, long jwtExpiresMs) {
        UserDTO userDTO = null;
        try {
            userDTO = Postgresql.getInstance().readUser(request.getUsername());
        } catch (SQLException e) {
            return TokenResponse.newBuilder()
                    .setResponseStatus(
                            ResponseStatus.newBuilder()
                                    .setCode(500)
                                    .setDetails(e.getMessage())
                                    .build())
                    .build();
        }

        if(userDTO == null || !new BCryptPasswordEncoder().matches(request.getPassword(),
                userDTO.getPasswordHash())){
            return TokenResponse.newBuilder()
                    .setResponseStatus(
                            ResponseStatus.newBuilder()
                                    .setCode(401)
                                    .setDetails("Invalid username or password.")
                                    .build())
                    .build();
        }

        long now = System.currentTimeMillis();
        String jws = null;
        try {
            jws = Jwts.builder()
                    .setSubject(request.getUsername())
                    .setIssuedAt(new Date(now))
                    .setNotBefore(new Date(now))
                    .setExpiration(new Date(now + jwtExpiresMs))
                    .signWith(SignatureAlgorithm.HS256, jwtSecret.getBytes("UTF-8"))
                    .compact();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return TokenResponse.newBuilder()
                    .setResponseStatus(
                            ResponseStatus.newBuilder()
                                    .setCode(500)
                                    .setDetails("Unsupported encoding to generate token.")
                                    .build())
                    .build();
        }

        return TokenResponse.newBuilder()
                .setToken(jws)
                .setResponseStatus(
                        ResponseStatus.newBuilder()
                                .setCode(200)
                                .build())
                .build();
    }

    public static ResponseStatus createNewPassword(Username request) {
        String email = null;
        try {
            email = Postgresql.getInstance().readEmail(request.getUsername());
        } catch (SQLException e) {
            e.printStackTrace();
            return ResponseStatus.newBuilder()
                    .setCode(500)
                    .setDetails(e.getMessage())
                    .build();
        }
        if(email == null){
            return ResponseStatus.newBuilder()
                    .setCode(404)
                    .setDetails("User not found")
                    .build();
        }

        // produce msg in password-renewal queue
        Producer.getInstance().produceMsg("password-renewal", new ConnectionFactory(),
                request.getUsername(), email);

        return ResponseStatus.newBuilder()
                .setCode(200)
                .setDetails("OK")
                .build();
    }

    public static ResponseStatus updateUserPassword(UsernameTokenAndPassword request) {
        // ask emails service if it contains a username->token equal to the one received

        EmailsServiceClient emailsServiceClient = EmailsServiceClient.getInstance();
        io.example.grpc.emails.ResponseStatus responseStatus = emailsServiceClient.getBlockingStub().confirmPassword(
                PasswordConfirmation.newBuilder()
                        .setUsername(request.getUsername())
                        .setToken(request.getToken())
                        .build());

        logger.info("confirmPassword GRPC response. CODE: {}. Details: {}.", responseStatus.getCode(), responseStatus.getDetails());

        if(responseStatus.getCode() == HttpResponseStatus.OK.code()){
            try {
                if(Postgresql.getInstance().updateUserPassword(
                        request.getUsername(), new BCryptPasswordEncoder().encode(request.getPassword()))){
                    return ResponseStatus.newBuilder()
                            .setCode(200)
                            .setDetails("OK")
                            .build();
                }
                else{
                    return ResponseStatus.newBuilder()
                            .setCode(500)
                            .setDetails("Password wasn't updated. Try again.")
                            .build();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return ResponseStatus.newBuilder()
                        .setCode(500)
                        .setDetails(e.getMessage())
                        .build();
            }
        }

        return ResponseStatus.newBuilder()
                .setCode(404)
                .setDetails("Not found")
                .build();
    }
}
