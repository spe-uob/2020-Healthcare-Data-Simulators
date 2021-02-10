import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import static org.apache.commons.codec.Charsets.UTF_8;

public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("hello-world", false, false, false, null);

        while (true) {
            channel.basicConsume("hello-world", true, (consumerTag, message) -> {
                String m = new String(message.getBody(), UTF_8);
                System.out.println("Message received: " + m);

            }, consumerTag -> {
            });
        }
    }
}
