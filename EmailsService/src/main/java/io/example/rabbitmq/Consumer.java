package io.example.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import io.example.common.RedisKeysPrefixes;
import io.example.dao.Redis;
import io.example.rabbitmq.msghandlers.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

public final class Consumer {
    private final static Logger logger = LoggerFactory.getLogger(Consumer.class);
    private final Connection rabbitConnection;
    private final MessageHandler messageHandler;
    private final String queueName;

    public Consumer(String queueName, Connection rabbitConnection, MessageHandler messageHandler) throws IOException, TimeoutException {
        this.rabbitConnection = rabbitConnection;
        this.messageHandler = messageHandler;
        this.queueName = queueName;
    }

    public void consumeMsgs(){
        try {
            Channel channel = rabbitConnection.createChannel();
            channel.queueDeclare(queueName, true, false, false, null);

            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

//            channel.basicQos(1);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");

                System.out.println(" [x] Received '" + message + "'");

                messageHandler.handleMsg(message);

                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                System.out.println(" [x] Done");
            };

            channel.basicConsume(queueName, false, deliverCallback, consumerTag -> { });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
