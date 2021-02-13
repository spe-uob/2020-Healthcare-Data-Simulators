import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MessageBrokerSender {
    String username, password, connectionString, port;

    MessageBrokerSender(String username, String password, String connectionString, String port) {
        this.username = username;
        this.password = password;
        this.connectionString = connectionString;
        this.port = port;
    }


    public void Send()  {
        try {
            ConnectionFactory factory = new ConnectionFactory();

            //settings for AWS
            factory.setUri("amqps://" + username + ":" + password + "@" + connectionString + ":" + port);

            // Create a connection
            Connection conn = factory.newConnection();

            // Create a Channel
            Channel chh = conn.createChannel();
            chh.queueDeclare("hello-world", false, false, false, null);

            byte[] content = Files.readString(Paths.get(new File("data.json").toURI())).getBytes();

            chh.basicPublish("", "hello-world", null, content);

            conn.close();


        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
