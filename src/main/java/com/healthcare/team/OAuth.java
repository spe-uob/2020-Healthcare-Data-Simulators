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
import java.util.Arrays;
import java.util.concurrent.TimeoutException;

public class OAuth {
    public String token;
    private final String client_id, region, username, password;

    public OAuth(String client_id, String region, String username, String password) {
       this.token = "";
       this.client_id = client_id;
       this.region = region;
       this.username = username;
       this.password = password;
    }

    private String[] getFullCommand() {
        return new String[]{"python3 lib" + File.separator + "cognito_auth.py", this.client_id, this.region, this.username, this.password};
    }
    public void generateToken() {
        System.out.println("Getting token...");
        ProcessBuilder processBuilder = new ProcessBuilder();
        String[] fullCommand = getFullCommand();
        processBuilder.command(fullCommand[0], fullCommand[1], fullCommand[2], fullCommand[3], fullCommand[4], fullCommand[5]);

        try {

            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while((line = reader.readLine()) != null) {
                output.append((line));
                System.out.println(output);
            }

            this.token = output.toString();
            if (this.token.equals("")) {
                alertUserAuth();
                throw new IllegalArgumentException("No token generated!");
            }
            System.out.println("Token received!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void alertUserAuth() {
        JOptionPane.showMessageDialog(null,
                "Error getting token","Error!", JOptionPane.ERROR_MESSAGE);
    }
    public void sendToRabbitMQ(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
                channel.queueDeclare("TokenQueue", false, false, false, null);
            String message = this.token;
            channel.basicPublish("", "TokenQueue", null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        } catch (TimeoutException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}