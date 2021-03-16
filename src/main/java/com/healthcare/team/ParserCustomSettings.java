package com.healthcare.team;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ParserCustomSettings {

    public ArrayList<String> parse(String fileName) {
        ArrayList<String> strarr = new ArrayList<>();

        try {

            File myFile = new File(fileName);
            Scanner myReader = new Scanner(myFile);

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (fileName.contains("modules.txt")) strarr.add(data.toLowerCase());
                    else strarr.add(data);
            }

            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return strarr;
    }

}
