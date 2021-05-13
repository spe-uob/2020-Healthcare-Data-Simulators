package com.healthcare.team.csv.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Medication extends CsvPojo {
    @JsonProperty("START")
    private String start;
    @JsonProperty("STOP")
    private String stop;
    @JsonProperty("PATIENT")
    private String patient;
    @JsonProperty("PAYER")
    private String payer;
    @JsonProperty("ENCOUNTER")
    private String encounter;
    @JsonProperty("CODE")
    private String code;
    @JsonProperty("DESCRIPTION")
    private String description;
    @JsonProperty("BASE_COST")
    private String base_cost;
    @JsonProperty("PAYER_COVERAGE")
    private String payer_coverage;
    @JsonProperty("DISPENSES")
    private String dispenses;
    @JsonProperty("TOTALCOST")
    private String totalcost;
    @JsonProperty("REASONCODE")
    private String reasoncode;
    @JsonProperty("REASONDESCRIPTION")
    private String reasondescription;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
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

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
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

    public String getPayer_coverage() {
        return payer_coverage;
    }

    public void setPayer_coverage(String payer_coverage) {
        this.payer_coverage = payer_coverage;
    }

    public String getDispenses() {
        return dispenses;
    }

    public void setDispenses(String dispenses) {
        this.dispenses = dispenses;
    }

    public String getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(String totalcost) {
        this.totalcost = totalcost;
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

    public Medication() {}

    @Override
    public String toString() {
        return Medication.class.getCanonicalName() + ": " +
                "start='" + start + '\'' +
                "| stop='" + stop + '\'' +
                "| patient='" + patient + '\'' +
                "| payer='" + payer + '\'' +
                "| encounter='" + encounter + '\'' +
                "| code='" + code + '\'' +
                "| description='" + description + '\'' +
                "| base_cost='" + base_cost + '\'' +
                "| payer_coverage='" + payer_coverage + '\'' +
                "| dispenses='" + dispenses + '\'' +
                "| totalcost='" + totalcost + '\'' +
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
