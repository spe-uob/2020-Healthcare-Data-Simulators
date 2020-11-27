import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Compute {

  /*  public int randomInt() {
        Random rand = new Random();
        int rand_int = rand.nextInt(1000);

        return rand_int;
    }*/

    public void generatePatient(JButton button1) {
        System.out.println("Starting...");
        //button1.setText("Waiting...");
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash","-c","java -jar synthea_JAR/synthea-with-dependencies.jar");

        try{

            button1.setEnabled(false);

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
            System.out.println("Success!");

            JOptionPane.showMessageDialog(null, "1 Pacient has been generated","Success!", JOptionPane.INFORMATION_MESSAGE);
            button1.setEnabled(true);
            button1.setText("Press to generate pacients");
        }
        catch (IOException er) {
            System.out.println(er);
        }

        //Runtime.getRuntime().exec("/bin/bash -c java -jar synthea_JAR/synthea-with-dependencies.jar");
    }
}
