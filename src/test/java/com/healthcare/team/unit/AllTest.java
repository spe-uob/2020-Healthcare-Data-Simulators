package com.healthcare.team.unit;

import com.healthcare.team.InitialSetup;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Includes all test for the system
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ComputeTest.class,
        ConverterTest.class,
        MessageBrokerTest.class,
        OAuthTest.class,
        ParseJSONTest.class,
        SenderSFTP.class,
        SendTest.class,
        SFTPTest.class
})

public class AllTest {
    @BeforeClass
    public static void setupTestsClass() {
        //extract files first before running any test
        InitialSetup initialSetup = new InitialSetup();
        initialSetup.setup();
    }
}
