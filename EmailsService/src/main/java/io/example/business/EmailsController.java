package io.example.business;

import io.example.common.RedisKeysPrefixes;
import io.example.dao.Redis;
import io.example.grpc.emails.EmailConfirmation;
import io.example.grpc.emails.PasswordConfirmation;
import io.example.grpc.emails.ResponseStatus;
import org.slf4j.LoggerFactory;

public final class EmailsController {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmailsController.class);

    public static ResponseStatus confirmEmail(EmailConfirmation request) {
        if(request.getToken().equals(Redis.getInstance().readValAssociatedWithKey(
                RedisKeysPrefixes.EMAIL_TOKEN + request.getUsername()))){
            logger.info("UUID of email confirmation for username {} read with success.", request.getUsername());
            return ResponseStatus.newBuilder().setCode(200).build();
        }
        logger.info("UUID of email confirmation for username {} read without success.", request.getUsername());
        return ResponseStatus.newBuilder().setCode(403).build();
    }

    public static ResponseStatus confirmPassword(PasswordConfirmation request) {
        if(request.getToken().equals(Redis.getInstance().readValAssociatedWithKey(
                RedisKeysPrefixes.PASSWORD_TOKEN + request.getUsername()))){
            logger.info("UUID of password renewal for username {} read with success.", request.getUsername());
            return ResponseStatus.newBuilder().setCode(200).build();
        }
        logger.info("UUID of password renewal for username {} read without success.", request.getUsername());
        return ResponseStatus.newBuilder().setCode(404).build();
    }
}
