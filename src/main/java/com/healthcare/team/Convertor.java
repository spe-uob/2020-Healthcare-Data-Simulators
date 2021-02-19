package com.healthcare.team;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Convertor {
    private String getCommand(String path) {
        String x = "java -jar convertor_hl7-with-dependencies.jar ";
        x += path;
        return x;

    }
    public void Convertor(String path){
        System.out.println("Converting...");
        ProcessBuilder p = new ProcessBuilder();
        String command = getCommand(path);
        System.out.println(command);
        p.command("bash","-c", command);
        //processBuilder.command("bash","-c","ls");
        try{

            Process process = p.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream())
            );

            String line;
            while((line = reader.readLine()) != null) {
                output.append((line + '\n'));
            }

            //System.out.println(output);
            System.out.println("Success!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
