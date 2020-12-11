import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Send {

    public void SendPacient() {
        System.out.println("Starting...");
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c","curl -X POST -H \"Content-Type: application/json\" -d @output/fhir/data.json https://httpbin.org/post");

        try{

            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while((line = reader.readLine()) != null) {
                output.append((line + '\n'));
            }
            
            System.out.println(output);
            System.out.println("POST method succeded!");

            JOptionPane.showMessageDialog(null, "The message was send using POST method","Success!", JOptionPane.INFORMATION_MESSAGE);

        }
        catch (IOException er) {
            System.out.println(er);
        }

        //Runtime.getRuntime().exec("/bin/bash -c java -jar synthea_JAR/synthea-with-dependencies.jar");
    }
}
