package com.healthcare.team;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class OAuth {
    public String token;
    private final String client_id, region, username, password;

    public OAuth(String client_id, String region, String username, String password) {
       this.token = "";
       this.client_id = client_id;
       this.region = region;
       this.username = username;
       this.password = password;
    }

    private String[] getFullCommand(){
        String fullCommand = "python3 src/main/java/com/healthcare/team/cognito_auth.py ";
        fullCommand = fullCommand + this.client_id + " "+ this.region + " " + this.username + " " + this.password;
        return fullCommand.split(" ");
    }
    public void generateToken() {
        System.out.println("Getting token...");
        ProcessBuilder processBuilder = new ProcessBuilder();
        String[] fullCommand = getFullCommand();
        processBuilder.command(fullCommand[0], fullCommand[1], fullCommand[2], fullCommand[3], fullCommand[4], fullCommand[5]);

        try {

            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while((line = reader.readLine()) != null) {
                output.append((line));
                System.out.println(output);
            }

            this.token = output.toString();
            if (this.token.equals("")) {
                alertUserAuth();
                throw new IllegalArgumentException("No token generated!");
            }
            System.out.println("Token received!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void alertUserAuth() {
        JOptionPane.showMessageDialog(null,
                "Error getting token","Error!", JOptionPane.ERROR_MESSAGE);
    }
}