package io.example.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserServiceClientConfiguration {
    @Bean
    public UsersServiceClient usersServiceClient(){
        // TODO: Set host and port dynamically...
        return new UsersServiceClient("localhost", 8888);
    }
}
