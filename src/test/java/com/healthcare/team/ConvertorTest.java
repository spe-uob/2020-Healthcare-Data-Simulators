package com.healthcare.team;

import com.healthcare.team.Convertor;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.*;
import static org.junit.Assert.assertFalse;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;

public class ConvertorTest {

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

    @Test(timeout = 10000)
    public void checkProcessParameters() {
        Convertor conv = new Convertor("a_path");
        String command = "java -jar lib/convertor_hl7-with-dependencies.jar ".concat(conv.getPath());
        List<String> actual = conv.processParameters(null);
        assertThat(actual, hasItems("bash"));
        assertThat(actual, hasSize(3));
        assertThat(actual, contains("bash", "-c", command));
        assertThat(actual, not(IsEmptyCollection.empty()));
    }

    @Test
    public void alwaysNotShowingAlert(){
        Convertor conv = new Convertor("some_path");
        assertFalse(conv.showAlert(""));
    }
}