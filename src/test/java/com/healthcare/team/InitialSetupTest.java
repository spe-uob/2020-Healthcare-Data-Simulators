package com.healthcare.team;

import org.junit.After;
import org.junit.Test;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;

public class InitialSetupTest {
    public static <T> int getLength(T[] arr){
        int count = 0;
        for(T el : arr)
            if (el != null)
                ++count;
        return count;
    }

    @After
    public void after() throws IOException {
        File file = new File("lib");
        Assert.assertTrue(file.exists() && file.isDirectory());
        FileUtils.deleteDirectory(file);
        Assert.assertFalse(new File("lib").exists());
    }

    @Test
    public void assertExtractionCompleteAndCorrect() {
        InitialSetup initialSetup = new InitialSetup();
        initialSetup.setup();
        File file = new File("lib");
        Assert.assertTrue(file.exists() && file.isDirectory());
        File[] files = file.listFiles();
        Assert.assertNotNull(files);
        Object[] allFiles = Arrays.stream(files).toArray();
        String[] required = {
                "lib"+File.separator+"cognito_auth.py",
                "lib"+File.separator+"convertor_hl7-with-dependencies.jar",
                "lib"+File.separator+"OpenPseudonymiserCryptoLib.jar",
                "lib"+File.separator+"synthea-with-dependencies.jar"
        };
        Boolean[] allExist  = new Boolean[4];
        int i = 0;
        for (String s : required) {
            for (Object obj : allFiles) {
                if (obj.toString().equals(s)) {
                    allExist[i] = true;
                }
            }
            i++;
        }
        int extracted =  getLength(allExist);
        Assert.assertEquals(required.length, extracted);
    }
}
