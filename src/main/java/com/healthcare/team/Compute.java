package com.healthcare.team;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

public class Compute {

    private static final List<String> validGenders = Arrays.asList("female", "male");
    private final String population;
    private final String minAge;
    private final String maxAge;
    private final String gender;
    private final String module;
    private final String state;

    public Compute(String population, String minAge, String maxAge, String gender, String module, String state) {

        this.population = Objects.requireNonNull(population, "Non null field: population");
        this.minAge = Objects.requireNonNull(minAge, "Non null field: minAge.");
        this.maxAge = Objects.requireNonNull(maxAge, "Non null field: maxAge");
        this.gender = Objects.requireNonNull(gender, "Non null field: gender");
        this.module = Objects.requireNonNull(module, "Non null field: module");
        this.state = Objects.requireNonNull(state, "Non null field: state");
        checkValuesEntered();
    }

    /*public static Compute of(String population, String minAge, String maxAge, String gender, String module, String state) {
        return new Compute(population, minAge, maxAge, gender, module, state);
    }*/

    private void checkValuesEntered() {

        if (Integer.parseInt(maxAge) > 200) {
            throw new IllegalArgumentException("Illegal maximum age!");
        }
        if (Integer.parseInt(minAge) < 0) {
            throw new IllegalArgumentException("Illegal minimum age!");
        }
        if (Integer.parseInt(minAge) > Integer.parseInt(maxAge)) {
            throw new IllegalArgumentException("Minimum age is bigger than maximum age!");
        }
        if (Integer.parseInt(population) > 9999) {
            throw new IllegalArgumentException("Population given is too big!");
        }

        if (!validGenders.contains(gender)) {
            throw new IllegalArgumentException("Illegal Entry!, gender");
        }

        ParserCustomSettings pcs = new ParserCustomSettings();
        List<String> choicesStates = pcs.parse(System.getProperty("user.dir").concat(File.separator + "lib" + File.separator + "regions.txt"));
        List<String> choicesModules = pcs.parse(System.getProperty("user.dir").concat(File.separator + "lib" + File.separator + "modules.txt"));

        choicesStates.stream().filter(state::equals)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Illegal Entry!, state"));

        choicesStates.stream().filter(module::equals)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Illegal Entry!, module"));
    }

    private String getCommand() {

        return new StringBuilder("java -jar ./lib/synthea-with-dependencies.jar")
                .append(" -p ").append(this.population).append(" -g ")
                .append(this.gender.equals("male") ? "M" : "F").append(" -a ")
                .append(this.minAge).append("-").append(this.maxAge).append(" -m ")
                .append(this.module).append(" ").append(this.state)
                .toString();
    }

    public void generatePatient() {

        System.out.println("Starting...");
        ProcessBuilder processBuilder = new ProcessBuilder();

        String command = getCommand();
        System.out.println(command);
        processBuilder.command("bash", "-c", command);
        try {

            Process process = processBuilder.start();
            System.out.println("edward");
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );
            System.out.println("DA");
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                output.append(line).append('\n');
            }
            if (output.toString().equals("")) {
                System.err.println("Generating failed");
            } else {
                System.out.println("Success!");
            }

            alertUser(output.toString());

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    protected void alertUser(String output) {
        if (output.isBlank() || output.startsWith("Usage:")) {
            JOptionPane.showMessageDialog(null,
                    "Error while generating", "Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                    "Generation Successful", "Success!", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
