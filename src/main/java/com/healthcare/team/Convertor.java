package com.healthcare.team;

import java.io.File;
import java.util.Objects;

public class Convertor extends BashProcess {

    private String getCommand(String path) {
        return "java -jar lib/convertor_hl7-with-dependencies.jar ".concat(path);
    }

    private void checkValues(String path) {
        Objects.requireNonNull(path, "null element given!");
        if (path.isBlank()) {
            throw new IllegalArgumentException("empty path provided");
        }
        File fileExists = new File(path);
        if (!fileExists.exists()) {
            throw new IllegalArgumentException("file doesn't exist!");
        }
    }

    public void convertor(String path) {
        checkValues(path);
        System.out.println("Converting...");

        executeCommand(getCommand(path), "Converting hl7 to fhir failed!");
    }

    @Override
    protected void alertUser(String output) {
    }
}
