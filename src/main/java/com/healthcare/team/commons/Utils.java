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
}
