import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Compute {

  /*  public int randomInt() {
        Random rand = new Random();
        int rand_int = rand.nextInt(1000);

        return rand_int;
    }*/

    private String population, minAge, maxAge, sex, disease, state;

    Compute (String population, String minAge, String maxAge, String sex, String disease, String state) {
        this.population = population;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.sex = sex;
        this.disease = disease;
        this.state = state;

    }

    String getCommand() {
        String x = "java -jar synthea-with-dependencies.jar";

        x += " -p " + this.population + " -a " + this.minAge + "-" + this.maxAge + " -m " + "*" + this.disease + "*" + " " + this.state;

        return x;

    }

    public void generatePatient() {

        System.out.println("Starting...");
        //button1.setText("Waiting...");
        ProcessBuilder processBuilder = new ProcessBuilder();

        String command = getCommand();
        //System.out.println(command);
        processBuilder.command("bash","-c", command);
        //processBuilder.command("bash","-c","ls");
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
            System.out.println("Success!");

            JOptionPane.showMessageDialog(null, "1 Pacient has been generated","Success!", JOptionPane.INFORMATION_MESSAGE);


        }
        catch (IOException er) {
            System.out.println(er);
        }
    }
}
