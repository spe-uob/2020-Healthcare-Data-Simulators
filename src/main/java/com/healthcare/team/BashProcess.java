package com.healthcare.team;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public abstract class BashProcess {

    public void executeCommand(String command, String errorMessage) {

        ProcessBuilder processBuilder = new ProcessBuilder();
        System.out.println(command);
        processBuilder.command("bash", "-c", command);
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
            if (output.toString().isBlank()) {
                //System.err.println(errorMessage(""));
                throw new IOException(errorMessage);
            }

            System.out.println("Success!");

            alertUser(output.toString());

        } catch (IOException e) {
            System.out.println(e);
            e.printStackTrace();
        }
    }

    protected abstract void alertUser(String output);

   // protected abstract String[] fullCommand();
}
