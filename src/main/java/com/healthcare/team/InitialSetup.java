package com.healthcare.team;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * This will extract all files required into a lib folder when the compiled
 * jar file is run. It also compares the sha512 hex values of extracted files
 * to original files to check if they are corrupted, if so, a security exception is
 * thrown and program never runs with corrupted files.
 */

public class InitialSetup {

    public void setup() {
        System.out.println("extracting files...");
        FileResourcesUtils fru = new FileResourcesUtils();
        boolean complete = fru.extractResources();

        if (complete) {
            System.out.println("verifying checksum...");
            File dir = new File("lib");
            File[] files = dir.listFiles();
            assert files != null;
            Map<String, String> checksum = readCacheFile();
            if (checksum == null) {
                System.out.println("checksum file not provided, skipping...");
            } else {
                //if the checksum.txt file is provided, loop through all files in lib directory
                //and check if the newly calculated sha512hex is equal to the original, if not
                //throw an exception and if so, run the program
                for (File file : files) {
                    try {
                        String sha512hex = DigestUtils.sha512Hex(Files.readAllBytes(file.toPath()));

                        for (Map.Entry<String, String> check: checksum.entrySet()) {
                            int index = file.toString().lastIndexOf(File.separator);
                            int index2 = check.getKey().lastIndexOf("/");
                            if (index < 0 || index2 < 0) {
                                continue;
                            }
                            String file1 = file.toString().substring(index+1);
                            String file2 = check.getKey().substring(index2+1);

                            if (file1.equals(file2)) {
                                if (!check.getValue().equals(sha512hex)) {
                                    System.err.println("This file is corrupted! "+file.toString());
                                    throw new SecurityException("file corrupted: "+file.toString());
                                }
                                break;
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        System.out.println("opening program...");
    }

    private static Map<String, String> readCacheFile() {
        Map<String, String> checksum = new HashMap<>();
        try {
            File myObj = new File("checksum.txt");
            if (!myObj.exists()) return null;
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] arr = data.split(" {3}:");
                checksum.put(arr[0], arr[1]);
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return checksum;
    }

}
