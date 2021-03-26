package com.healthcare.team.unit;

import com.healthcare.team.Convertor;
import org.junit.*;

public class ConverterTest {

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyValueShouldThrow() {
       Convertor converter = new Convertor("");
        converter.convertor();
    }

    @Test(expected = NullPointerException.class)
    public void testNullValueShouldThrow() {
        Convertor converter = new Convertor(null);
        converter.convertor();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testImpossibleValueShouldThrow() {
        Convertor converter = new Convertor("F:/iAmAFileIDontExist");
        converter.convertor();
    }

}