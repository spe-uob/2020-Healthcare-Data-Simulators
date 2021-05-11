package com.healthcare.team.csv.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthcare.team.Anonymization;

import java.util.TreeMap;

public class PayerTranzition extends CsvPojo {

    @JsonProperty("PATIENT")
    private String patient;
    @JsonProperty("START_YEAR")
    private String startYear;
    @JsonProperty("END_YEAR")
    private String endYear;
    @JsonProperty("PAYER")
    private String payer;
    @JsonProperty("OWNERSHIP")
    private String ownership;

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    @Override
    public String toString() {
        return PayerTranzition.class.getCanonicalName() + ": " +
                "patient='" + patient + '\'' +
                "| startYear='" + startYear + '\'' +
                "| endYear='" + endYear + '\'' +
                "| payer='" + payer + '\'' +
                "| ownership='" + ownership + '\'';
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
