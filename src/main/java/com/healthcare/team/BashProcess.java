package com.healthcare.team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


public abstract class BashProcess {

    public void executeCommand(String errorMessage) {

        ProcessBuilder processBuilder = new ProcessBuilder();
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
                //System.err.println(errorMessage(""));
                alertUser();
                throw new IOException(errorMessage);
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

    protected abstract List<String> processParameters();
}
