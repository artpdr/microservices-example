package io.example.main;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.example.rabbitmq.Consumer;
import io.example.rabbitmq.msghandlers.EmailConfirmationMessageHandler;
import io.example.rabbitmq.msghandlers.PasswordRenewalMessageHandler;
import io.example.service.EmailsServiceImpl;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        String email = System.getenv("EMAIL"); //"exampleapp@yahoo.com";
        String emailPassword = System.getenv("EMAIL_PASSWORD"); //"ebtbHeUg3qJgxtN";
        if(email == null || email.isEmpty() || emailPassword == null || emailPassword.isEmpty()){
            throw new AssertionError("EMAIL or EMAIL_PASSWORD missing as environment variables");
        }

        // start consuming messages from rabbitmq
        Connection rabbitConnection = new ConnectionFactory().newConnection(); // uses default host and port
        Consumer emailConfirmationConsumer = new Consumer("emails-confirmation",
                rabbitConnection, new EmailConfirmationMessageHandler("email.properties",
                email, emailPassword, 3600));
        Consumer passwordRenewalConsumer = new Consumer("password-renewal",
                rabbitConnection, new PasswordRenewalMessageHandler("email.properties",
                email, emailPassword, 3600));
        emailConfirmationConsumer.consumeMsgs();
        passwordRenewalConsumer.consumeMsgs();

        // Port should be set dynamically, for example read from env or properties file.
        io.grpc.Server server = ServerBuilder.forPort(9999)
                .addService(new EmailsServiceImpl())
                .build();

        // Start the main
        server.start();

        // Server threads are running in the background.
        System.out.println("Server started. Listening on port 9999");
        // Don't exit the main thread. Wait until main is terminated.
        server.awaitTermination();
    }
}
