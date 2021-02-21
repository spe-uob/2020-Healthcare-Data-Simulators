package com.healthcare.team;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Compute {

    private final String population;
    private final String minAge;
    private final String maxAge;
    private final String gender;
    private final String module;
    private final String state;

    public Compute (String population, String minAge, String maxAge, String gender, String module, String state) {
        this.population = population;
        this.minAge = minAge;
        this.maxAge = maxAge;
        this.gender = gender;
        this.module = module;
        this.state = state;
        checkValuesEntered();
    }

    private void checkValuesEntered() {
        if (population == null || minAge == null || maxAge == null || gender == null || module == null || state == null) {
            throw new NullPointerException("value is null!");
        }

        boolean isNumber = StringUtils.isNumeric(population) && StringUtils.isNumeric(minAge)
                && StringUtils.isNumeric(maxAge);

        if (isNumber) {
            if (Integer.parseInt(maxAge) > 200) {
                throw new IllegalArgumentException("Illegal maximum age!");
            }
            if (Integer.parseInt(minAge) < 0) {
                throw new IllegalArgumentException("Illegal minimum age!");
            }
            if (Integer.parseInt(population) > 9999) {
                throw new IllegalArgumentException("Population given is too big!");
            }
        }

        boolean isState = false;
        boolean isModule = false;
        boolean validGender = gender.equals("Male") || gender.equals("Female");
        ParserCustomSettings pcs = new ParserCustomSettings();
        ArrayList<String> choicesStates = pcs.parse(System.getProperty("user.dir").concat("/regions.txt"));
        ArrayList<String> choicesModules = pcs.parse(System.getProperty("user.dir").concat("/modules.txt"));

        for (String _state : choicesStates) {
            if (_state.equals(state)) {
                isState = true;
                break;
            }
        }
        for (String _module : choicesModules) {
            if (_module.equals(module)) {
                isModule = true;
                break;
            }
        }

        if (!isNumber || !isState || !isModule || !validGender) {
            throw new IllegalArgumentException("Illegal Entry!");
        }
    }

    private String getCommand() {
        String x = "java -jar synthea-with-dependencies.jar";

        x += " -p " + this.population + " -g " + (this.gender.equals("Male")? "M" : "F") + " -a "
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
            alertUser(output);

        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void alertUser(StringBuilder output) {
        if(output.toString().equals("") || output.toString().startsWith("Usage:")) {
            JOptionPane.showMessageDialog(null,
                    "Error while generating", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Generation Successful", "Success!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
