package io.example.rabbitmq;

import com.rabbitmq.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private final static Producer INSTANCE = new Producer();
    private static volatile Connection rabbitConnection = null;

    private Producer(){}

    public static Producer getInstance(){
        return INSTANCE;
    }

    private synchronized Connection getRabbitConnection(ConnectionFactory connectionFactory) throws IOException, TimeoutException {
        if(rabbitConnection == null){
            rabbitConnection = connectionFactory.newConnection();
        }
        return rabbitConnection;
    }

    public boolean produceMsg(String queueName, ConnectionFactory connectionFactory, String username, String email){
        try (Channel channel = getRabbitConnection(connectionFactory).createChannel()){
            channel.queueDeclare(queueName, true, false, false, null);

            logger.info(" [*] Waiting for messages. To exit press CTRL+C");

            String message = username + " " + email;

            channel.basicPublish("", queueName,
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));
            logger.info(" [x] Sent '" + message + "'");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
