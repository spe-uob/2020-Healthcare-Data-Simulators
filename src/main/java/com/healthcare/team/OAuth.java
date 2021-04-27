package com.healthcare.team;

import static com.healthcare.team.commons.Constants.OBJECT_PROPERTY_NPE_MESSAGE;

import com.healthcare.team.commons.Utils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

public class OAuth extends BashProcess {
    public String token;
    private final String client_id, awsRegion, username, password;

    public OAuth(String client_id, String region, String username, String password) {
        this.token = "";
        this.client_id = Objects.requireNonNull(client_id, String.format(OBJECT_PROPERTY_NPE_MESSAGE, OAuth.class.getCanonicalName(), "client_id"));
        this.awsRegion = Objects.requireNonNull(region, String.format(OBJECT_PROPERTY_NPE_MESSAGE, OAuth.class.getCanonicalName(), "region"));
        this.username = Objects.requireNonNull(username, String.format(OBJECT_PROPERTY_NPE_MESSAGE, OAuth.class.getCanonicalName(), "username"));
        this.password = Objects.requireNonNull(password, String.format(OBJECT_PROPERTY_NPE_MESSAGE, OAuth.class.getCanonicalName(), "password"));
        if (client_id.isBlank() || region.isBlank() || username.isBlank() || password.isBlank()) {
            throw new IllegalArgumentException(OAuth.class.getCanonicalName().concat(" fields are empty!"));
        }
    }

    public void generateToken() {
        executeCommand(null,"No token generated!");
        this.token = getGeneratedToken();
    }

    @Override
    protected void alertUser() {
        JOptionPane.showMessageDialog(null,
                "Error getting token", "Error!", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    protected void informUser() {
    }

    @Override
    protected boolean showAlert(String output) {
        return Utils.isStringInvalid(output);
    }

    @Override
    protected List<String> processParameters(String region) {
        return List.of("python3", "lib" + File.separator + "cognito_auth.py", client_id, awsRegion, username, password);
    }


    public void sendToRabbitMQ() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("TokenQueue", false, false, false, null);
            String message = this.token;
            System.out.println(token);
            channel.basicPublish("", "TokenQueue", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClientId() {
        return client_id;
    }

    public String getAwsRegion() {
        return awsRegion;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}