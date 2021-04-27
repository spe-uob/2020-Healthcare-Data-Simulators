package com.healthcare.team;

import com.healthcare.team.commons.ModulesTest;
import com.healthcare.team.commons.StatesTest;
import com.healthcare.team.commons.UtilsTest;
import com.healthcare.team.commons.ValidationsTest;
import com.healthcare.team.scheduler.JobSchedulerTest;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Includes all test for the system
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ComputeTest.class,
        ConvertorTest.class,
        OAuthTest.class,
        ParseJSONTest.class,
        SenderSFTP.class,
        SendTest.class,
        SFTPTest.class,
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
    public static void setupTestsClass() {
        //extract files first before running any test
        InitialSetup initialSetup = new InitialSetup();
        initialSetup.setup();
    }
}
