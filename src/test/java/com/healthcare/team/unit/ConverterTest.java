package com.healthcare.team.unit;

import com.healthcare.team.Convertor;
import org.junit.*;

import java.io.File;
import java.io.IOException;

public class ConverterTest {
    private Convertor converter;
    private File testFile;

    @Before
    public void setup() {
        converter = new Convertor();
        testFile = new File("testFile.json");
        try {
            testFile = new File("testFile.json");
            boolean created = testFile.createNewFile();
            if (created) System.out.println("created");
            testFile.deleteOnExit();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyValueShouldThrow() {
        converter.convertor("");
    }

    @Test(expected = NullPointerException.class)
    public void testNullValueShouldThrow() {
        converter.convertor(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testImpossibleValueShouldThrow() {
        converter.convertor("F:\\iAmAFileIDontExist");
    }

    @Test
    public void testCorrectValueShouldPass() {
        converter.convertor(testFile.getAbsolutePath());
    }
}