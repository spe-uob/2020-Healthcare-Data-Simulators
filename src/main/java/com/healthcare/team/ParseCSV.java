package com.healthcare.team;

import static com.healthcare.team.commons.Constants.PATIENTS_QUEUE_NAME;
import static com.healthcare.team.commons.Constants.PATIENTS_CSV_FILE_HEADER;

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
        File dir = new File(outputPath);
        System.out.println(dir.getName());
        File[] directoryListing = dir.listFiles();
        MessageBrokerSender mbs = new MessageBrokerSender();
        for (File f : Objects.requireNonNull(directoryListing)) {
            System.out.println("Sending....." + f.getName());
            String output = Files.readString(Path.of(outputPath.concat(f.getName())));
            System.out.println("------" + output);
            mbs.Sender(output, f.getName());
        }
    }

    //We read line by line from CSVs
    public String readPatientsFile(String region) {

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(Patient.class);
        schema.withoutHeader().withLineSeparator("\n").withColumnSeparator(',');
        ObjectReader oReader = csvMapper.reader(Patient.class).with(schema);
        String filePath = buildFilePath(region);
        StringBuilder rabbitAnonymizeData = new StringBuilder();
        StringBuilder anonymizeDataForCsvUpdate = new StringBuilder();
        try (Reader reader = new FileReader(filePath)) {
            MappingIterator<Patient> mi = oReader.readValues(reader);
            while (mi.hasNext()) {
                Patient current = mi.next();

                rabbitAnonymizeData.append(current.toString());
                anonymizeDataForCsvUpdate.append(current.toCsvString()).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        updateCsvFile(filePath, anonymizeDataForCsvUpdate.toString());
        return rabbitAnonymizeData.toString();
    }

    //Send patients with NHSNumber encrypted to Rabbit queue
    public void sendPatientsToRabbit(String region) {
        String anonymize = readPatientsFile(region);
        String queueName = String.format(PATIENTS_QUEUE_NAME, region);

        new MessageBrokerSender().Sender(anonymize, queueName);
    }

    private String buildFilePath(String region) {
        return System.getProperty("user.dir").concat("/").concat(region).concat("/csv/patients.csv");
    }

    private void updateCsvFile(String path, String fileContent) {
        try (FileWriter csvWriter = new FileWriter(path)) {
            csvWriter.append(PATIENTS_CSV_FILE_HEADER);
            csvWriter.append("\n");

            csvWriter.append(fileContent);

            csvWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
