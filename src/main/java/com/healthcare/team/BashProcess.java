package com.healthcare.team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public abstract class BashProcess {
    private String generatedToken = "";

    public void checkForNullAndEmptyValues(String[] args) {
        for (String arg : args) {
            if (arg == null) throw new NullPointerException("null value found!");
            if (arg.equals("")) throw new IllegalArgumentException("Empty value found!");
        }
    }

    public String getToken() {
       return generatedToken;
    }

    public void executeCommand(String errorMessage) {

        ProcessBuilder processBuilder = new ProcessBuilder();
        System.out.println(processParameters());
        processBuilder.command(processParameters());
        try {

            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append('\n');
            }

            if (showAlert(output.toString())) {
                alertUser();
                throw new IOException(errorMessage);
            }
            if (errorMessage.equals("No token generated!")) this.generatedToken = output.toString();

            informUser();
            System.out.println("Success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected abstract void alertUser();

    protected abstract void informUser();

    protected abstract boolean showAlert(String output);

    protected abstract List<String> processParameters();
}
