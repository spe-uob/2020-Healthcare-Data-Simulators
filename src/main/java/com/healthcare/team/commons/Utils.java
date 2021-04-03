package com.healthcare.team.commons;

public class Utils {

    public static boolean isNumeric(String stringAsNumber) {
        if (stringAsNumber == null || stringAsNumber.isBlank()) {
            return false;
        }
        try {
            double d = Integer.parseInt(stringAsNumber);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isStringInvalid(String toBeValidated) {
        return toBeValidated == null && toBeValidated.isBlank();
    }

    public static boolean isValidString(String toBeValidated) {
        return !isStringInvalid(toBeValidated);
    }
}
