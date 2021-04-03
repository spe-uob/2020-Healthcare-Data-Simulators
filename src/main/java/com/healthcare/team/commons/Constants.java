package com.healthcare.team.commons;

public interface Constants {

    static final int JOB_MIN_INTERVAL_IN_SECONDS = 5;

    static final String ACTION_JOB_START = "START";
    static final String COMPUTE_AS_CTX_PARAMETER_NAME = "computer";
    static final String REGION_CTX_PARAM_NAME = "region";
    static final String PATIENTS_QUEUE_NAME = "Healtcare-Data-Simulators/%s/csv/patients";
    static final String PATIENTS_CSV_FILE_HEADER = "Id,BIRTHDATE,DEATHDATE,SSN,DRIVERS,PASSPORT,PREFIX,FIRST,LAST,SUFFIX,MAIDEN,MARITAL,RACE,ETHNICITY,GENDER,BIRTHPLACE,ADDRESS,CITY,STATE,COUNTY,ZIP,LAT,LON,HEALTHCARE_EXPENSES,HEALTHCARE_COVERAGE ";
    static final String OBJECT_PROPERTY_NPE_MESSAGE = "NPE on object:%s, attribute:%s";
}
