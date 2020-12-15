import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

class OAuth {
    String token;

    OAuth() {
       this.token = "";
    }

    void generateToken() {
        System.out.println("Getting token...");
        ProcessBuilder processBuilder = new ProcessBuilder();

        processBuilder.command("bash","-c","python3 src/main/java/cognito-auth.py 7n4vr35t6o5153456ervok1vm9 eu-west-2");

        try {

            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while((line = reader.readLine()) != null) {
                output.append((line));
            }
            
            //System.out.println(output);
            this.token = output.toString();
            System.out.println("Token received!");

            //JOptionPane.showMessageDialog(null, "1 Pacient has been generated","Success!", JOptionPane.INFORMATION_MESSAGE);


        }
        catch (IOException er) {
            System.out.println(er);
        }
    }
}