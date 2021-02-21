package com.healthcare.team;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.apache.log4j.BasicConfigurator;

public class MessageBrokerReceiver {
    private final String username, password, connectionString, port;
    private static final String BROKER_URI = "amqps://healthcaredatasim:philipisthebestclient@b-2b8a65ac-59e8-4888-b0ed-093a848d3775.mq.us-east-1.amazonaws.com:5671";
    private static final String queue = "test-queue";
    private static final String STANDARD_ENCODING = "UTF-8";

    MessageBrokerReceiver(String username, String password, String connectionString, String port) {
        this.username = username;
        this.password = password;
        this.connectionString = connectionString;
        this.port = port;
    }

    public static void main(String[] argv) throws Exception {
        BasicConfigurator.configure();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri(BROKER_URI);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queue, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), STANDARD_ENCODING);
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queue, true, deliverCallback, consumerTag -> { });
    }
}

