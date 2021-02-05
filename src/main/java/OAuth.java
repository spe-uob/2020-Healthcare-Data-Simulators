import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

class OAuth {
    String token;
    String client_id, region, username, password;
    OAuth(String client_id, String region, String username, String password) {
       this.token = "";
       this.client_id = client_id;
       this.region = region;
       this.username = username;
       this.password = password;
    }

    private String getFullCommand(){
        String fullCommand = "python3 src/main/java/cognito_auth.py ";
        fullCommand = fullCommand + this.client_id + " "+ this.region + " " + this.username + " " + this.password;
        return fullCommand;
    }
    public void generateToken() {
        System.out.println("Getting token...");
        ProcessBuilder processBuilder = new ProcessBuilder();
        String fullCommand = getFullCommand();
        System.out.println(fullCommand);
        processBuilder.command("bash","-c",fullCommand);

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