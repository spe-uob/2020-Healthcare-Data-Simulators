package com.healthcare.team.csv.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Procedure extends CsvPojo {
    @JsonProperty("DATE")
    private String date;
    @JsonProperty("PATIENT")
    private String patient;
    @JsonProperty("ENCOUNTER")
    private String encounter;
    @JsonProperty("CODE")
    private String code;
    @JsonProperty("DESCRIPTION")
    private String description;
    @JsonProperty("BASE_COST")
    private String base_cost;
    @JsonProperty("REASONCODE")
    private String reasoncode;
    @JsonProperty("REASONDESCRIPTION")
    private String reasondescription;

    public Procedure() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getBase_cost() {
        return base_cost;
    }

    public void setBase_cost(String base_cost) {
        this.base_cost = base_cost;
    }

    public String getReasoncode() {
        return reasoncode;
    }

    public void setReasoncode(String reasoncode) {
        this.reasoncode = reasoncode;
    }

    public String getReasondescription() {
        return reasondescription;
    }

    public void setReasondescription(String reasondescription) {
        this.reasondescription = reasondescription;
    }

    @Override
    public String toString() {
        return Procedure.class.getCanonicalName() + ": " +
                "date='" + date + '\'' +
                "| patient='" + patient + '\'' +
                "| encounter='" + encounter + '\'' +
                "| code='" + code + '\'' +
                "| description='" + description + '\'' +
                "| base_cost='" + base_cost + '\'' +
                "| reasoncode='" + reasoncode + '\'' +
                "| reasondescription='" + reasondescription + '\'';
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
