package com.healthcare.team;

import OpenPseudonymiser.Crypto;

import java.util.TreeMap;

public class Anonimization {

    private static Crypto crypto;

    private static void init() {
        if (crypto == null) {
            crypto = new Crypto();
            crypto.SetPlainTextSalt("fantasy");
        }
    }

    public static String mask(TreeMap nameValue) {


        init();
// The input: a name/value pair
        //TreeMap nameValue = new TreeMap();

// any spaces in the special case field called 'NHSNumber' will be stripped out
        // nameValue.put("NHSNumber", "9434765919");

// even though we add DOB after we add NHS, it will come before NHSNumber in the input, since the SortedList will always order by alphabetical key
        //nameValue.put("DOB", "29.11.1973");

// Call the GetDigest method and receive the digest..
        try {
            return crypto.GetDigest(nameValue);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }
}
