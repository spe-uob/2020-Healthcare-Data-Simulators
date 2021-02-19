package com.healthcare.team;

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

    private String population, minAge, maxAge, gender, module, state;

    Compute (String population, String minAge, String maxAge, String gender, String module, String state) {
        this.population = population;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.gender = gender;
        this.module = module;
        this.state = state;

    }

    String getCommand() {
        String x = "java -jar synthea-with-dependencies.jar";

        x += " -p " + this.population + " -g " + (this.gender.equals("male")? "M" : "F") + " -a " + this.minAge + "-" + this.maxAge + " -m " + "*" + this.module + "*" + " " + this.state;

        return x;

    }

    public void generatePatient() {

        System.out.println("Starting...");
        //button1.setText("Waiting...");
        ProcessBuilder processBuilder = new ProcessBuilder();

        String command = getCommand();
        System.out.println(command);
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

            //System.out.println(output);
            System.out.println("Success!");
            if(output.equals("") || output.substring(0,6).equals("Usage:")) JOptionPane.showMessageDialog(null, "Error while generating","Error!", JOptionPane.ERROR_MESSAGE);
            else JOptionPane.showMessageDialog(null, "Generation Successful","Success!", JOptionPane.INFORMATION_MESSAGE);

        }
        catch (IOException er) {
            System.out.println(er);
        }
    }
}
