package com.healthcare.team;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.healthcare.team.csv.objects.Patient;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class ParseCSV {

    public void sendToRabbit() throws IOException {
       /* String outputPath = System.getProperty("user.dir").concat("/output/csv/");
        File dir = new File( outputPath);
        System.out.println(dir.getName());
        File[] directoryListing = dir.listFiles();
        MessageBrokerSender mbs = new MessageBrokerSender();
        for(File f: Objects.requireNonNull(directoryListing)) {
            System.out.println("Sending....."+f.getName());
            String output = Files.readString(Path.of(outputPath.concat(f.getName())));
            System.out.println("------" + output);
            mbs.Sender(output,f.getName());

        }*/
        System.out.println("---------------------------------");
        sss();
    }

    public void sss(){

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(Patient.class);
        schema.withoutHeader().withLineSeparator("\n").withColumnSeparator(',');
        ObjectReader oReader = csvMapper.reader(Patient.class).with(schema);
        String outputPath = System.getProperty("user.dir").concat("/output/csv/patients.csv");
        StringBuilder anonimizedData = new StringBuilder();
        MessageBrokerSender mbs = new MessageBrokerSender();
        try (Reader reader = new FileReader(outputPath)) {
            MappingIterator<Patient> mi = oReader.readValues(reader);
            TreeMap<String, String> map = new TreeMap<>();
            while (mi.hasNext()) {
                Patient current = mi.next();
                System.out.println(current);
                map.put("address", current.getAddress());
                map.put("birthDate", current.getBirthDate());
                anonimizedData.append(Anonimization.mask(map));
            }
        }catch(IOException e){

        }
        mbs.Sender(anonimizedData.toString(), outputPath);
        System.out.println("out: " + anonimizedData.toString());
    }

   /* private <T> TreeMap<String, String> buildAnonimizationMap(T object){
        TreeMap<String, String> map = new TreeMap<>();
        map.put()
        return map;
    }*/

}
