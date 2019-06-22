package io.example.main;

import io.example.config.EnvironmentVarsLoader;
import io.example.dao.Postgresql;
import io.example.service.UsersServiceImpl;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, InterruptedException, NoSuchFieldException {
        // load necessary environment vars to memory
        final EnvironmentVarsLoader envVars = new EnvironmentVarsLoader();

        // create database users and table users
        Postgresql.DBSetup dbSetup = new Postgresql.DBSetup();
        dbSetup.createUsersDB();
        dbSetup.createUsersTable();

        // Create a new main to listen on port 8888
        io.grpc.Server server = ServerBuilder.forPort(8888)
                .addService(new UsersServiceImpl(envVars))
                .build();

        // Start the main
        server.start();

        // Server threads are running in the background.
        System.out.println("Server started. Listening on port 8888");
        // Don't exit the main thread. Wait until main is terminated.
        server.awaitTermination();
    }
}
