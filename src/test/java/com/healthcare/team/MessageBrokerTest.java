package com.healthcare.team;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.*;

public class MessageBrokerTest {
    private MessageBroker messageBroker;

    @Before
    public void setup() {
        Logger.getRootLogger().setLevel(Level.OFF);
    }

    @Test(expected = NullPointerException.class)
    public void testNullConnectionStringShouldThrow() {
        messageBroker = new MessageBroker(
                null,
                "123",
                "abcd",
                "abc123"
        );
    }

    @Test(expected = NullPointerException.class)
    public void testNullPortShouldThrow() {
        messageBroker = new MessageBroker(
                "",
                null,
                "abcd",
                "abc123"
        );
    }

    @Test(expected = NullPointerException.class)
    public void testNullUsernameShouldThrow() {
        messageBroker = new MessageBroker(
                "abcde",
                "123",
                null,
                "abc123"
        );
    }

    @Test(expected = NullPointerException.class)
    public void testNullPasswordShouldThrow() {
        messageBroker = new MessageBroker(
                "abcde",
                "123",
                "abcd",
                null
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyConnectionStringShouldThrow() {
        messageBroker = new MessageBroker(
                "",
                "123",
                "abcd",
                "abc123"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPortShouldThrow() {
        messageBroker = new MessageBroker(
                "",
                "",
                "abcd",
                "abc123"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyUsernameShouldThrow() {
        messageBroker = new MessageBroker(
                "abcde",
                "123",
                "",
                "abc123"
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyPasswordShouldThrow() {
        messageBroker = new MessageBroker(
                "abcde",
                "123",
                "abcd",
                ""
        );
    }

    @Test
    public void testCorrectValuesShouldPass() {
        messageBroker = new MessageBroker(
                "b-2b8a65ac-59e8-4888-b0ed-093a848d3775.mq.us-east-1.amazonaws.com",
                "5671",
                "healthcaredatasim",
                "philipisthebestclient"
        );
        messageBroker.Connect();
    }
}
