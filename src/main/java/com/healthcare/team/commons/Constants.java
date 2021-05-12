package com.healthcare.team.commons;

import static java.util.Map.entry;

import com.healthcare.team.csv.objects.*;

import java.util.Map;

public interface Constants {

    static final int JOB_MIN_INTERVAL_IN_SECONDS = 15;
    static final String JOB_NAME = "generateCsvAndSendDataJob";
    static final String ACTION_JOB_START = "START";
    static final String ACTION_PAUSE_JOB = "PAUSE";
    static final String ACTION_RESUME_JOB = "RESUME";
    static final String COMPUTE_AS_CTX_PARAMETER_NAME = "computer";
    static final String REGION_CTX_PARAM_NAME = "region";
    static final String OBJECT_PROPERTY_NPE_MESSAGE = "NPE on object:%s, attribute:%s";
    String CRYPTO_SALT = "fantasy";

    Map<String, Class> csvFiles = Map.ofEntries(entry("allergies", Allergie.class),
            entry("careplans", CarePlan.class),
            entry("conditions", Condition.class),
            entry("devices", Device.class),
            entry("encounters", Encounter.class),
           // entry("imagingstudies", ImagingStudy.class),
            entry("immunizations", Immunization.class),
            entry("medications", Medication.class),
            entry("observations", Observation.class),
            entry("organizations", Organization.class),
            entry("patients", Patient.class),
            //entry("payertranzitions", PayerTranzition.class),
            entry("payers", Payer.class),
            entry("procedures", Procedure.class),
            entry("providers", Provider.class),
            entry("supplies", Supply.class));
}
