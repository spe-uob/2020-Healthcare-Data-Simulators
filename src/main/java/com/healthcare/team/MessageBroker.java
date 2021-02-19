package com.healthcare.team;/* https://aws.amazon.com/amazon-mq/?amazon-mq.sort-by=item.additionalFields.postDateTime&amazon-mq.sort-order=desc */

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
import java.util.concurrent.TimeoutException;

public class MessageBroker {
    String username, password, connectionString, port;
    Connection conn;
    boolean connected = false;

    MessageBroker(String connectionString, String port, String username, String password) {
        this.username = username;
        this.password = password;
        this.connectionString = connectionString;
        this.port = port;
    }

    public Connection Connect() throws NoSuchAlgorithmException, KeyManagementException, URISyntaxException, IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();

        //settings for AWS
        factory.setUri("amqps://" + username + ":" + password + "@" + connectionString + ":" + port);

        // Create a connection
        conn = factory.newConnection();
        connected = true;

        return conn;
    }

    public void Send(GUI.DATA d, File file)  {
        try {

            // Create a Channel and a queue
            Channel chh = conn.createChannel();
            chh.queueDeclare("test-queue", false, false, false, null);

            //byte[] content = Files.readString(Paths.get(new File("data.json").toURI())).getBytes();

            switch (d) {
                case SYNTHEA:
                    String outputPath = System.getProperty("user.dir").concat("/output/fhir/");
                    File dir = new File(outputPath);
                    System.out.println(dir.getName());
                    File[] directoryListing = dir.listFiles();
                    for (File f : directoryListing) {
                        if (!f.getName().contains("json")) continue;
                        chh.basicPublish("", "test-queue", null, Files.readString(Paths.get(f.toURI())).getBytes());
                    }

                    break;

                case BINARY:
                    if (file == null) JOptionPane.showMessageDialog(null, "No File to upload","Error!", JOptionPane.ERROR_MESSAGE);
                    else {
                        System.out.println(file.getAbsolutePath());
                        chh.basicPublish("", "test-queue", null, Files.readString(Paths.get(file.toURI())).getBytes());

                    }

                    break;
            }

            JOptionPane.showMessageDialog(null, "Transfer successful!","Success!", JOptionPane.INFORMATION_MESSAGE);

            //chh.basicPublish("", "hello-world", null, content);

            conn.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
