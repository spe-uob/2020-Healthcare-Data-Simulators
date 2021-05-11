package com.healthcare.team.csv.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Condition extends CsvPojo {
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

    public Condition() {}

    public void setStart(String start) {
        this.start = start;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public void setEncounter(String encounter) {
        this.encounter = encounter;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStart() {
        return start;
    }

    public String getStop() {
        return stop;
    }

    public String getPatient() {
        return patient;
    }

    public String getEncounter() {
        return encounter;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return Condition.class.getCanonicalName() + ": " +
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
