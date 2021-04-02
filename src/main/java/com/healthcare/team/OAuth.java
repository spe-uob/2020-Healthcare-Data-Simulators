package com.healthcare.team;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class OAuth extends BashProcess {
    public String token;
    private final String client_id, region, username, password;

    public OAuth(String client_id, String region, String username, String password) {
        this.token = "";
        this.client_id = client_id;
        this.region = region;
        this.username = username;
        this.password = password;
        String[] allUserInputs = {
                this.client_id,
                this.region,
                this.username,
                this.password
        };
        checkForNullAndEmptyValues(allUserInputs);
    }

    public void generateToken() {
        executeCommand("No token generated!");
        this.token = getToken();
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
        return output.isBlank();
    }

    @Override
    protected List<String> processParameters() {
        return List.of("python3", "lib" + File.separator + "cognito_auth.py", this.client_id, this.region, this.username, this.password);
    }

    public void sendToRabbitMQ() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare("TokenQueue", false, false, false, null);
            String message = this.token;
            channel.basicPublish("", "TokenQueue", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
}