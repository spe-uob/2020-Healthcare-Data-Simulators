package com.healthcare.team.csv.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Allergie extends CsvPojo {
    @JsonProperty("START")
    private String start;
    @JsonProperty("STOP")
    private String stop;
    @JsonProperty("PATIENT")
    private String patient;
    @JsonProperty("ENCOUNTER")
    private String encounter;
    @JsonProperty("CODE")
    private String code;
    @JsonProperty("DESCRIPTION")
    private String description;

    public Allergie() {
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getStart() {
        return start;
    }


    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getEncounter() {
        return encounter;
    }

    public void setEncounter(String encounter) {
        this.encounter = encounter;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return Allergie.class.getCanonicalName() + ": " +
                "start='" + start + '\'' +
                "| stop='" + stop + '\'' +
                "| patient='" + patient + '\'' +
                "| encounter='" + encounter + '\'' +
                "| code='" + code + '\'' +
                "| description='" + description + '\'';
    }

    @Override
    public String toCsvString() {
        return null;
    }

    @Override
    public String getHeaderColumnNames() {
        return null;
    }
}
