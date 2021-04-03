package com.healthcare.team;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public abstract class BashProcess extends  RegionSelection {
    private String generatedToken = "";

    public void executeCommand(String errorMessage) {

        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command(processParameters(null));
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
            if (errorMessage.equals("No token generated!")) {
                this.generatedToken = output.toString();
            }

            informUser();
            System.out.println("Success!");
        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    protected abstract void alertUser();

    protected abstract void informUser();

    protected abstract boolean showAlert(String output);

    protected abstract List<String> processParameters(String region);
}
