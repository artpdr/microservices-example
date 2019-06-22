package io.example.apigateway.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.example.apigateway.model.Password;
import io.example.apigateway.model.PasswordAndToken;
import io.example.apigateway.model.Token;
import io.example.apigateway.model.User;
import io.example.client.UsersServiceClient;
import io.example.grpc.users.*;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.thymeleaf.TemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2019-06-12T23:18:22.947+01:00[Europe/Lisbon]")
@Controller
public class UsersApiController implements UsersApi {

    private static final Logger logger = LoggerFactory.getLogger(UsersApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    private final UsersServiceClient usersServiceClient;

    private final TemplateEngine htmlTemplateEngine;

    @org.springframework.beans.factory.annotation.Autowired
    public UsersApiController(ObjectMapper objectMapper, HttpServletRequest request,
                              UsersServiceClient usersServiceClient, TemplateEngine htmlTemplateEngine) {
        this.objectMapper = objectMapper;
        this.request = request;
        this.usersServiceClient = usersServiceClient;
        this.htmlTemplateEngine = htmlTemplateEngine;
    }

    public ResponseEntity<Token> authenticateUser(@ApiParam(value = "Password" ,required=true )  @Valid @RequestBody Password body,@ApiParam(value = "username associated with the user that requires authentication",required=true) @PathVariable("username") String username) {
        io.example.grpc.users.TokenResponse token = usersServiceClient.getBlockingStub().auth(
            UsernameAndPassword.newBuilder()
                    .setUsername(username)
                    .setPassword(body.getPassword())
                    .build());
        if (token.getResponseStatus().getCode() != 200){
            return new ResponseEntity<Token>(new Token(), HttpStatus.valueOf(token.getResponseStatus().getCode()));
        }
        return new ResponseEntity<Token>(new Token().token(token.getToken()), HttpStatus.OK);
    }

    public ResponseEntity<Void> confirmUser(@ApiParam(value = "username associated with the user that needs to be confirmed",required=true) @PathVariable("username") String username,@ApiParam(value = "token sent to email",required=true) @PathVariable("token") String token) {
        ResponseStatus responseStatus = usersServiceClient.getBlockingStub().confirmUserEmail(
                UsernameAndToken.newBuilder()
                        .setUsername(username)
                        .setToken(token)
                        .build()
        );

        return new ResponseEntity<Void>(HttpStatus.valueOf(responseStatus.getCode()));
    }

    public ResponseEntity<Void> createNewPassword(@ApiParam(value = "username associated with the user that required the creation of a new password",required=true) @PathVariable("username") String username) {
        ResponseStatus responseStatus = usersServiceClient.getBlockingStub().createNewPassword(
                Username.newBuilder()
                        .setUsername(username)
                        .build());

        logger.info("GRPC response. CODE: {}. Details: {}.", responseStatus.getCode(), responseStatus.getDetails());

        return new ResponseEntity<Void>(HttpStatus.valueOf(responseStatus.getCode()));
    }

    public ResponseEntity<Void> createUser(@ApiParam(value = "Created user object" ,required=true )  @Valid @RequestBody User body) {
        ResponseStatus responseStatus = usersServiceClient.getBlockingStub().createUser(
                io.example.grpc.users.User.newBuilder()
                        .setUsername(body.getUsername())
                        .setFirstName(body.getFirstName())
                        .setLastName(body.getLastName())
                        .setEmail(body.getEmail())
                        .setPassword(body.getPassword()).build()
        );

        logger.info("GRPC response. CODE: {}. Details: {}.", responseStatus.getCode(), responseStatus.getDetails());

        return new ResponseEntity<Void>(HttpStatus.valueOf(responseStatus.getCode()));
    }

    public ResponseEntity<Void> deleteUser(@ApiParam(value = "username associated with the user that needs to be deleted",required=true) @PathVariable("username") String username) {
        ResponseStatus responseStatus = usersServiceClient.getBlockingStub().deleteUser(
                Username.newBuilder()
                        .setUsername(username)
                        .build()
        );

        return new ResponseEntity<Void>(HttpStatus.valueOf(responseStatus.getCode()));
    }

    public ResponseEntity<String> getPasswordRenewalForm(@ApiParam(value = "username associated with the user that wants to renew password",required=true) @PathVariable("username") String username,@ApiParam(value = "token sent to email",required=true) @PathVariable("token") String token) {
        org.thymeleaf.context.Context ctx = new org.thymeleaf.context.Context();

        ctx.setVariable("username", username);
        ctx.setVariable("token", token);

        // Rendered template in String, You can now return in a JSON property
        String htmlContent = this.htmlTemplateEngine.process(new ClassPathResource("email-renewal").getPath(), ctx);

        return new ResponseEntity<>(htmlContent, HttpStatus.OK);
    }

    public ResponseEntity<User> readUser(@ApiParam(value = "The username that needs to be fetched",required=true) @PathVariable("username") String username) {
        UserResponse userResponse = usersServiceClient.getBlockingStub().readUser(
                Username.newBuilder()
                        .setUsername(username)
                        .build()
        );

        User userToReturn = null;
        if (userResponse.getResponseStatus().getCode() == 200){
            userToReturn = new User()
                    .username(userResponse.getUser().getUsername())
                    .firstName(userResponse.getUser().getFirstName())
                    .lastName(userResponse.getUser().getLastName())
                    .email(userResponse.getUser().getEmail());
        }

        return new ResponseEntity<User>(userToReturn, HttpStatus.valueOf(userResponse.getResponseStatus().getCode()));
    }

    public ResponseEntity<Void> updateUser(@ApiParam(value = "Updated user object" ,required=true )  @Valid @RequestBody User body, @ApiParam(value = "username associated with the user that needs to be updated",required=true) @PathVariable("username") String username) {
        logger.info("UpdateUser called from username: {}.", username);
        io.example.grpc.users.User user = io.example.grpc.users.User.newBuilder()
                .setUsername(username)
                .setFirstName(body.getFirstName())
                .setLastName(body.getLastName())
                .setEmail(body.getEmail())
                .setPassword(body.getPassword()).build();

        ResponseStatus responseStatus = usersServiceClient.getBlockingStub().updateUser(user);

        return new ResponseEntity<Void>(HttpStatus.valueOf(responseStatus.getCode()));
    }

    public ResponseEntity<Void> updateUserPassword(@ApiParam(value = "Password and token" ,required=true )  @Valid @RequestBody PasswordAndToken body, @ApiParam(value = "username associated with the user that required the password change",required=true) @PathVariable("username") String username) {
        ResponseStatus responseStatus = usersServiceClient.getBlockingStub().updateUserPassword(
                UsernameTokenAndPassword.newBuilder()
                        .setUsername(username)
                        .setToken(body.getToken())
                        .setPassword(body.getPassword())
                        .build());

        return new ResponseEntity<Void>(HttpStatus.valueOf(responseStatus.getCode()));
    }

}
