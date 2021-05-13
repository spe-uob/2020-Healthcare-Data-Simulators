package com.healthcare.team.Anonymization;

import java.util.TreeMap;

import com.healthcare.team.Anonymization.Anonymization;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;

public class AnonymizationTest {

    @Test(expected = RuntimeException.class)
    public void cryptoShouldFail(){
        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", null);
        Anonymization.mask(map);
    }

    @Test
    public void cryptoWorksAsExpected(){
        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", "1234");
        assertEquals("D0B440FECF55E7B55464EB9FEE461B57E1BBF1DC1B0891E5CE457B9C0B414839", Anonymization.mask(map));
    }

    @Test
    public void failExpected(){
        TreeMap<String, String> map = new TreeMap<>();
        map.put("key", "value");
        assertThat("keyInSHA256", not(Anonymization.mask(map)));
    }
}
