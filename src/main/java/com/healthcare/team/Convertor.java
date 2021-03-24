package com.healthcare.team;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Convertor {

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
        ProcessBuilder p = new ProcessBuilder();
        String command = getCommand(path);
        System.out.println(command);
        p.command("bash", "-c", command);

        try {
            Process process = p.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append('\n');
            }

            if (output.toString().isBlank()) {
                throw new IOException("Converting hl7 to fhir failed!");
            }
            System.out.println("Success!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
