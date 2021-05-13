package com.healthcare.team;

import com.healthcare.team.Anonymization.AnonymizationTest;
import com.healthcare.team.BashProcess.BashProcessTest;
import com.healthcare.team.BashProcess.ComputeTest;
import com.healthcare.team.BashProcess.ConvertorTest;
import com.healthcare.team.BashProcess.OAuthTest;
import com.healthcare.team.Rabbit.ParseCSVTest;
import com.healthcare.team.Setup.InitialSetupTest;
import com.healthcare.team.commons.ModulesTest;
import com.healthcare.team.commons.StatesTest;
import com.healthcare.team.commons.UtilsTest;
import com.healthcare.team.commons.ValidationsTest;
import com.healthcare.team.scheduler.JobSchedulerTest;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.File;
import java.io.IOException;

/**
 * Includes all test for the system
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        InitialSetupTest.class,
        ComputeTest.class,
        ConvertorTest.class,
        OAuthTest.class,
        UtilsTest.class,
        ModulesTest.class,
        StatesTest.class,
        ValidationsTest.class,
        JobSchedulerTest.class,
        AnonymizationTest.class,
        ParseCSVTest.class,
        BashProcessTest.class
})

public class AllTest {
    @BeforeClass
    public static void beforeClass() {
        //extract files first before running any test
        InitialSetup initialSetup = new InitialSetup();
        initialSetup.setup();
    }

    @AfterClass
    public static void afterClass() throws IOException {
        File file = new File("lib");
        if (file.exists()) {
            Assert.assertTrue(file.isDirectory());
            FileUtils.deleteDirectory(file);
            Assert.assertFalse(new File("lib").exists());
        }
    }
}
