package io.example.rabbitmq.msghandlers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Delivery;


import java.io.IOException;

/**
 * Different strategies to handle messages received from rabbit mq should implement this interface
 */
public interface MessageHandler{
    public void handleMsg(String message) throws IOException;
}