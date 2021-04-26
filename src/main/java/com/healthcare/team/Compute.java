package com.healthcare.team;

import com.healthcare.team.commons.Utils;
import com.healthcare.team.commons.Validations;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class Compute extends BashProcess {

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

        Validations.isValidGender(gender);
        Validations.isValidModule(module);
        Validations.isValidState(state);
    }

    private String getParametersSynthea() {
        return new StringBuilder().append(" -p ").append(population).append(" -g ")
                .append(gender.equals("male") ? "M" : "F").append(" -a ")
                .append(minAge).append("-").append(maxAge).append(" -m ")
                .append(module).append(" ").append(state)
                .toString();
    }

    private String getCommand(String region) {
        return new StringBuilder("java -jar ./lib/synthea-with-dependencies.jar")
                .append(getParametersSynthea())
                .append(" --exporter.baseDirectory ./")
                .append(region)
                .append(" --exporter.csv.export true")
                .toString();
    }

    public void generatePatient() {
        System.out.println("Starting...");
        executeCommand(state, "Generating failed");
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
        return Utils.isStringInvalid(output) || output.startsWith("Usage:");
    }

    @Override
    public List<String> processParameters(String region) {
        return List.of("bash", "-c", getCommand(region));
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

    public String getStateSynthea() {
        return state;
    }
}
