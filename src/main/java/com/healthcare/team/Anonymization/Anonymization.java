package com.healthcare.team.Anonymization;

import OpenPseudonymiser.Crypto;
import static com.healthcare.team.commons.Constants.CRYPTO_SALT;
import java.util.TreeMap;

public class Anonymization {

    private static Crypto crypto;

    private static void init() {
        if (crypto == null) {
            crypto = new Crypto();
            crypto.SetPlainTextSalt(CRYPTO_SALT);
        }
    }

    public static String mask(TreeMap nameValue) {
        init();
        try {
            return crypto.GetDigest(nameValue);
        } catch (Exception e) {
           throw new RuntimeException(e);
        }
    }
}
