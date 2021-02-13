import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.apache.log4j.BasicConfigurator;

public class MessageBrokerReceiver {
    String username, password, connectionString, port;

    MessageBrokerReceiver(String username, String password, String connectionString, String port) {
        this.username = username;
        this.password = password;
        this.connectionString = connectionString;
        this.port = port;
    }


    public static void main(String[] argv) throws Exception {
        BasicConfigurator.configure();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUri("amqps://healthcaredatasim:philipisthebestclient@b-2b8a65ac-59e8-4888-b0ed-093a848d3775.mq.us-east-1.amazonaws.com:5671");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("hello-world", false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume("hello-world", true, deliverCallback, consumerTag -> { });

    }
    


}

