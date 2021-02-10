import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

public class Producer {
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            channel.queueDeclare("hello-world", false, false, false, null);
            int count = 0;

            while (count < 500) {
                System.out.print("\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b\b");
                String content = Files.readString(Paths.get(new File("data.json").toURI()))
                        .concat(String.valueOf(LocalDateTime.now()));

                channel.basicPublish("", "hello-world", false, null, content.getBytes());
                count++;
                System.out.print(count+"/500 messages sent...");
                Thread.sleep(100);
            }
            System.out.println();
            System.out.println("Sending complete");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
