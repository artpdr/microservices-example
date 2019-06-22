package io.example.rabbitmq.msghandlers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Delivery;
import io.example.common.RedisKeysPrefixes;
import io.example.dao.Redis;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.UUID;

public final class PasswordRenewalMessageHandler implements MessageHandler {
    private final String EMAIL_SUBJECT = "Change your password for example.io service.";
    private final Properties emailProperties;
    private final String email;
    private final String emailPassword;
    private final Integer emailExpireSeconds;

    public PasswordRenewalMessageHandler(String emailPropertiesFile, String email,
                                           String emailPassword, int emailExpireSeconds) throws IOException {
        emailProperties = new Properties();
        try (InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(emailPropertiesFile)){
            emailProperties.load(in);
        }
        this.email = email;
        this.emailPassword = emailPassword;
        this.emailExpireSeconds = emailExpireSeconds;
    }

    @Override
    public void handleMsg(String message) throws IOException {
        try {
            String[] usernameAndEmail = message.split(" ");
            if(usernameAndEmail.length != 2){
                // TODO: Set error...
            }
            String username = usernameAndEmail[0];
            String email = usernameAndEmail[1];
            String uuid = UUID.randomUUID().toString();
            String emailContent = createEmailContent(username, uuid);
            storeValueAssociatedWithKey(RedisKeysPrefixes.PASSWORD_TOKEN + username, uuid);
            sendEmail(email, emailContent);
        } catch (MessagingException e) {
            e.printStackTrace();
            System.out.println("Couldn't send email.");
        }
    }

    private void storeValueAssociatedWithKey(String key, String val) {
        if(!Redis.getInstance().createKeyValEntry(key, val, emailExpireSeconds)){
            // TODO: Do something if the uuid can't be stored in db
        }
    }

    private void sendEmail(String email, String content) throws MessagingException {
        Session session = Session.getInstance(emailProperties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(PasswordRenewalMessageHandler.this.email, emailPassword);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(this.email));
        message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(email));
        message.setSubject(EMAIL_SUBJECT);

        MimeBodyPart mimeBodyPart = new MimeBodyPart();
        mimeBodyPart.setContent(content, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(mimeBodyPart);

        message.setContent(multipart);

        Transport.send(message);
    }

    private String createEmailContent(String username, String uuid){
        return  "<h3>Hello " + username + ". </h3>" +
                "<p>Please access the following " +
                "<a href=\"http://localhost:8080/users/"
                + username + "/password/" + uuid + "\">link</a> " +
                "to change your password.</p>" +
                "<p>This link will be available in the next " + emailExpireSeconds/60.0/60.0 + " hour(s).</p>";
    }
}
