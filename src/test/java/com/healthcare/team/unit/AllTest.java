package com.healthcare.team.unit;

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
public class AllTest {}
