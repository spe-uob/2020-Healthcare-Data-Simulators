package com.healthcare.team.commons;

import com.healthcare.team.commons.Utils;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
public class UtilsTest {

    @Test(timeout = 15000)
    public void checkInvalidString() {
        assertTrue(Utils.isStringInvalid("    "));
        assertTrue(Utils.isStringInvalid(null));
        assertFalse(Utils.isStringInvalid("test string"));
    }

    @Test(timeout = 15000)
    public void checkValidString() {
        assertTrue(Utils.isValidString("not an empty string"));
        assertFalse(Utils.isValidString(""));
        assertFalse(Utils.isValidString(null));
    }

    @Test(timeout = 15000)
    public void checkIfStringIsNumericShouldFall() {
        assertFalse(Utils.isNumeric(""));
        assertFalse(Utils.isNumeric(null));
        assertFalse(Utils.isNumeric("not a number string"));
    }

    @Test(timeout = 15000)
    public void ifStringNumericShouldPass() {
        assertTrue(Utils.isNumeric("23"));
    }
}
