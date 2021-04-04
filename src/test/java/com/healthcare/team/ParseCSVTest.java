package com.healthcare.team;

import org.junit.Test;

import java.io.FileWriter;
import java.io.IOException;

import static com.healthcare.team.commons.Constants.PATIENTS_CSV_FILE_HEADER;

public class ParseCSVTest {

    /*@Test(expected = RuntimeException.class)
    private void ifCsvFileNotExistsThrowError(String path, String fileContent) {
        updateCsvFile
        try (FileWriter csvWriter = new FileWriter(path)) {
            csvWriter.append(PATIENTS_CSV_FILE_HEADER);
            csvWriter.append("\n");

            csvWriter.append(fileContent);

            csvWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
}
