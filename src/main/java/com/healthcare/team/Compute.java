package com.healthcare.team;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Compute {

    private final String population;
    private final String minAge;
    private final String maxAge;
    private final String gender;
    private final String module;
    private final String state;

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

        x += " -p " + this.population + " -g " + (this.gender.equals("male")? "M" : "F") + " -a "
                + this.minAge + "-" + this.maxAge + " -m " + "*" + this.module + "*" + " " + this.state;

        return x;

    }

    public void generatePatient() {

        System.out.println("Starting...");
        ProcessBuilder processBuilder = new ProcessBuilder();

        String command = getCommand();
        System.out.println(command);
        processBuilder.command("bash","-c", command);
        try{

            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while((line = reader.readLine()) != null) {
                output.append(line).append('\n');
            }

            System.out.println("Success!");
            if(output.toString().equals("") || output.toString().startsWith("Usage:"))
                JOptionPane.showMessageDialog(null,
                        "Error while generating","Error!", JOptionPane.ERROR_MESSAGE);
            else JOptionPane.showMessageDialog(null,
                    "Generation Successful","Success!", JOptionPane.INFORMATION_MESSAGE);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
