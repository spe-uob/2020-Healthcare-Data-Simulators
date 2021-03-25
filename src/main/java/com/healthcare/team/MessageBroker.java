/* https://aws.amazon.com/amazon-mq/?amazon-mq.sort-by=item.additionalFields.postDateTime&amazon-mq.sort-order=desc */
package com.healthcare.team;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
import java.util.concurrent.TimeoutException;

public class MessageBroker {
    private final String username, password, connectionString, port;
    private Connection conn;

    public MessageBroker(String connectionString, String port, String username, String password) {
        this.username = Objects.requireNonNull(username, "Non null field: username");
        this.password = Objects.requireNonNull(password, "Non null field: password");
        this.connectionString = Objects.requireNonNull(connectionString, "Non null field: connectionString");
        this.port = Objects.requireNonNull(port, "Non null field: port");
        valueChecks();
    }

    private void valueChecks() {

        if (username.isBlank() || password.isBlank() || connectionString.isBlank() || port.isBlank()) {
            throw new IllegalArgumentException("MessageBroker values cannot be empty!");
        }
    }

    public void Connect() {

        ConnectionFactory factory = new ConnectionFactory();

        StringBuilder uri = new StringBuilder();
        uri.append("amqps://").append(username).append(":").append(password).append("@").append(connectionString).append(":").append(port);

        //settings for AWS
        try {
            factory.setUri(uri.toString());
        } catch (URISyntaxException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }

        // Create a connection
        try {
            conn = factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }

    public void Send(GUI.DATA d, File file) {
        try {

            // Create a Channel and a queue
            Channel chh = conn.createChannel();
            chh.queueDeclare("test-queue", false, false, false, null);

            switch (d) {
                case SYNTHEA:
                    String outputPath = System.getProperty("user.dir").concat("/output/fhir/");
                    File dir = new File(outputPath);
                    System.out.println(dir.getName());
                    for (File f : Objects.requireNonNull(dir.listFiles())) {
                        if (!f.getName().contains("json")) {
                            continue;
                        }
                        chh.basicPublish("", "test-queue", null, Files.readString(Paths.get(f.toURI())).getBytes());
                    }
                    break;

                case BINARY:
                    if (file == null) {
                        JOptionPane.showMessageDialog(null, "No File to upload", "Error!", JOptionPane.ERROR_MESSAGE);
                    } else {
                        System.out.println(file.getAbsolutePath());
                        chh.basicPublish("", "test-queue", null, Files.readString(Paths.get(file.toURI())).getBytes());
                    }
                    break;
            }

            JOptionPane.showMessageDialog(null, "Transfer successful!", "Success!", JOptionPane.INFORMATION_MESSAGE);
            conn.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
