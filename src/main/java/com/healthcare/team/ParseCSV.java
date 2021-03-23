package com.healthcare.team;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ParseCSV {

    public void sendToRabbit() throws IOException {
        String outputPath = System.getProperty("user.dir").concat("/output/csv/");
        File dir = new File( outputPath);
        System.out.println(dir.getName());
        File[] directoryListing = dir.listFiles();
        MessageBrokerSender mbs = new MessageBrokerSender();
        for(File f: Objects.requireNonNull(directoryListing)) {
            System.out.println("Sending....."+f.getName());
            String output = Files.readString(Path.of(outputPath.concat(f.getName())));
            mbs.Sender(output,f.getName());

        }
    }
}
