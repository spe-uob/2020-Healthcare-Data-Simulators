package com.healthcare.team.Rabbit;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.healthcare.team.commons.Constants;
import com.healthcare.team.commons.Utils;
import com.healthcare.team.csv.objects.CsvPojo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
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
    public String readPatientsFile(String filePath, Class pojoClass) {
        System.out.println(filePath);
        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = CsvSchema.emptySchema().withHeader().withLineSeparator("\n").withColumnSeparator(',');
        ObjectReader oReader = csvMapper.reader(pojoClass).with(schema);

        StringBuilder rabbitAnonymizeData = new StringBuilder();
        StringBuilder anonymizeDataForCsvUpdate = new StringBuilder();
        CsvPojo current = null;
        try (Reader reader = new FileReader(filePath)) {
            MappingIterator<CsvPojo> mi = oReader.readValues(reader);
            while (mi.hasNext()) {
                current = mi.next();

                rabbitAnonymizeData.append(current.toString());
                anonymizeDataForCsvUpdate.append(current.toCsvString()).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(current != null && Utils.isValidString(current.getHeaderColumnNames())){
            updateCsvFile(filePath, current.getHeaderColumnNames(), anonymizeDataForCsvUpdate.toString());
        }

        return rabbitAnonymizeData.toString();
    }

    //Send patients with NHSNumber encrypted to Rabbit queues with respect to the region they come from
    public void sendPatientsToRabbit(String region) throws IOException {

        for(Map.Entry<String, Class> fileToClass : Constants.csvFiles.entrySet()){
            String filePathToPatients = buildFilePathToPatients(region, fileToClass.getKey());
            String anonymize = readPatientsFile(filePathToPatients, fileToClass.getValue());

            String queueName = region.toLowerCase().concat("_").concat(fileToClass.getKey());
            File dir = new File(filePathToPatients);
            String output = Files.readString(Path.of(String.valueOf(dir)));
            System.out.println(queueName);
            System.out.println(output);
            //new MessageBrokerSender().Sender(anonymize, queueName);
            new MessageBrokerSender().Sender(output, queueName);
        }
    }

    private String buildCommonFilepath(String region) {
        return System.getProperty("user.dir").concat(File.separator)
                .concat("Regions")
                .concat(File.separator)
                .concat(region)
                .concat("/csv");
    }

    private String buildFilepath(String region) {
        return buildCommonFilepath(region);
    }

    private String buildFilePathToPatients(String region, String fileIdentifier) {
        return buildCommonFilepath(region).concat(File.separator).concat(fileIdentifier).concat(".csv");
    }

    private void updateCsvFile(String path, String headerColumnNames, String fileContent) {
        try (FileWriter csvWriter = new FileWriter(path)) {
            csvWriter.append(headerColumnNames);
            csvWriter.append("\n");

            csvWriter.append(fileContent);

            csvWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
