package com.healthcare.team.unit;

import com.healthcare.team.Convertor;
import org.junit.*;

public class ConverterTest {
    private Convertor converter;

    @Before
    public void setup() {
        converter = new Convertor();
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
        converter.convertor("F:/iAmAFileIDontExist");
    }

}