package com.healthcare.team;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.healthcare.team.csv.objects.Patient;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class ParseCSV {

    //Send patients with data produced by Synthea to Rabbit queue
    public void sendToRabbit() throws IOException {
        String outputPath = System.getProperty("user.dir").concat("/output/csv/");
        File dir = new File( outputPath);
        System.out.println(dir.getName());
        File[] directoryListing = dir.listFiles();
        MessageBrokerSender mbs = new MessageBrokerSender();
        for(File f: Objects.requireNonNull(directoryListing)) {
            System.out.println("Sending....."+f.getName());
            String output = Files.readString(Path.of(outputPath.concat(f.getName())));
            System.out.println("------" + output);
            mbs.Sender(output,f.getName());

        }
        System.out.println("---------------------------------");
    }

    //We read line by line from CSVs
    public String readPatientsFile(){

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(Patient.class);
        schema.withoutHeader().withLineSeparator("\n").withColumnSeparator(',');
        ObjectReader oReader = csvMapper.reader(Patient.class).with(schema);
        String outputPath = System.getProperty("user.dir").concat("/output/csv/patients.csv");
        StringBuilder anonimizedData = new StringBuilder();
        try (Reader reader = new FileReader(outputPath)) {
            MappingIterator<Patient> mi = oReader.readValues(reader);
            while (mi.hasNext()) {
                Patient current = mi.next();
                anonimizedData.append(current.toString());
            }
        }catch(IOException e) {
            throw new RuntimeException(e);
        }

        return anonimizedData.toString();
    }

    //Send patients with NHSNumber encrypted to Rabbit queue
    public void sendPatientsToRabbit() {
        MessageBrokerSender mbs = new MessageBrokerSender();
        String outputPath = System.getProperty("user.dir").concat("/output/csv/patients.csv");
        String anonimized = readPatientsFile();
        mbs.Sender(anonimized, outputPath);
        System.out.println("out: " + anonimized);
    }
}
