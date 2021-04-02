package com.healthcare.team;

import javax.swing.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Compute extends BashProcess {

    private static final List<String> validGenders = Arrays.asList("female", "male", "both");
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

        choicesModules.stream().filter(module.toLowerCase(Locale.ROOT)::equals)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Illegal Entry!, module"));
    }

    private String getCommand() {
        return "java -jar ./lib/synthea-with-dependencies.jar" +
                " -p " + this.population + (this.gender.equals("both") ? "" : (" -g " +
                (this.gender.equals("male") ? "M" : "F"))) + " -a " +
                this.minAge + "-" + this.maxAge + " -m " +
                this.module + " " + this.state;
    }

    public void generatePatient() {
        System.out.println("Starting...");
        executeCommand("Generating failed");
    }

    @Override
    protected void alertUser() {
            JOptionPane.showMessageDialog(null,
                    "Error while generating", "Error!", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    protected void informUser() {
        JOptionPane.showMessageDialog(null,
                "Generation Successful", "Success!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    protected boolean showAlert(String output) {
        return output.isBlank() || output.startsWith("Usage:");
    }

    @Override
    protected List<String> processParameters() {
        return List.of("bash", "-c", getCommand());
    }

    public static List<String> getValidGenders() {
        return validGenders;
    }

    public String getPopulation() {
        return population;
    }

    public String getMinAge() {
        return minAge;
    }

    public String getMaxAge() {
        return maxAge;
    }

    public String getGender() {
        return gender;
    }

    public String getModule() {
        return module;
    }

    public String getState() {
        return state;
    }
}
