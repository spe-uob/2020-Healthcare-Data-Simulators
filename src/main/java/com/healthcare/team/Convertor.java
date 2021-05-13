package com.healthcare.team;

import java.io.File;
import java.util.List;
import java.util.Objects;

public class Convertor extends BashProcess {

    private final String path;

    public Convertor(String path) {
        this.path = path;
    }

    private String getCommand(String region) {
        return "java -jar lib/convertor_hl7-with-dependencies.jar ".concat(path);
    }

    private void checkValues() {
        Objects.requireNonNull(path, "null element given!");
        if (path.isBlank()) {
            throw new IllegalArgumentException("empty path provided");
        }
        if (!new File(path).exists()) {
            throw new IllegalArgumentException("file doesn't exist!");
        }
    }

    public void convertor() {
        checkValues();

        System.out.println("Converting...");

        executeCommand(null,"Converting hl7 to fhir failed!");
    }

    @Override
    protected void alertUser() {
    }

    @Override
    protected void informUser() {
    }

    @Override
    protected boolean showAlert(String output) {
        return false;
    }

    @Override
    protected List<String> processParameters(String region) {
        return List.of("bash", "-c", getCommand(region));
    }

    public String getPath() {
        return path;
    }
}
