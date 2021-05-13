package com.healthcare.team.csv.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthcare.team.Anonymization.Anonymization;

import java.util.TreeMap;

public class Encounter extends CsvPojo {
    @JsonProperty("Id")
    private String id;
    @JsonProperty("START")
    private String start;
    @JsonProperty("STOP")
    private String stop;
    @JsonProperty("PATIENT")
    private String patient;
    @JsonProperty("ORGANIZATION")
    private String organization;
    @JsonProperty("PROVIDER")
    private String provider;
    @JsonProperty("PAYER")
    private String payer;
    @JsonProperty("ENCOUNTERCLASS")
    private String encounterclass;
    @JsonProperty("CODE")
    private String code;
    @JsonProperty("DESCRIPTION")
    private String description;
    @JsonProperty("BASE_ENCOUNTER_COST")
    private String base_encounter_cost;
    @JsonProperty("TOTAL_CLAIM_COST")
    private String total_claim_cost;
    @JsonProperty("PAYER_COVERAGE")
    private String payer_coverage;
    @JsonProperty("REASONCODE")
    private String reasoncode;
    @JsonProperty("REASONDESCRIPTION")
    private String reasondescription;

    public Encounter() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPayer() {
        return payer;
    }

    public void setPayer(String payer) {
        this.payer = payer;
    }

    public String getEncounterclass() {
        return encounterclass;
    }

    public void setEncounterclass(String encounterclass) {
        this.encounterclass = encounterclass;
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

    public String getBase_encounter_cost() {
        return base_encounter_cost;
    }

    public void setBase_encounter_cost(String base_encounter_cost) {
        this.base_encounter_cost = base_encounter_cost;
    }

    public String getTotal_claim_cost() {
        return total_claim_cost;
    }

    public void setTotal_claim_cost(String total_claim_cost) {
        this.total_claim_cost = total_claim_cost;
    }

    public String getPayer_coverage() {
        return payer_coverage;
    }

    public void setPayer_coverage(String payer_coverage) {
        this.payer_coverage = payer_coverage;
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

        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", id);

        return Encounter.class.getCanonicalName() + ": " +
                "Id='" + Anonymization.mask(map) + '\'' +
                "| start='" + start + '\'' +
                "| stop='" + stop + '\'' +
                "| patient='" + patient + '\'' +
                "| organization='" + organization + '\'' +
                "| provider='" + provider + '\'' +
                "| payer='" + payer + '\'' +
                "| encounterclass='" + encounterclass + '\'' +
                "| code='" + code + '\'' +
                "| description='" + description + '\'' +
                "| base_encounter_cost='" + base_encounter_cost + '\'' +
                "| total_claim_cost='" + total_claim_cost + '\'' +
                "| payer_coverage='" + payer_coverage + '\'' +
                "| reasoncode='" + reasoncode + '\'' +
                "| reasondescription='" + reasondescription + '\'';
    }

    public String toCsvString() {
        return Anonymization.mask(buildAnonymizeMap()) + ',' +
                start + ',' +
                stop + ',' +
                patient + ',' +
                organization + ',' +
                provider + ',' +
                payer + ',' +
                encounterclass + ',' +
                code + ',' +
                description + ',' +
                base_encounter_cost + ',' +
                total_claim_cost + ',' +
                payer_coverage + ',' +
                reasoncode + ',' +
                reasondescription + ',';
    }

    @Override
    public String getHeaderColumnNames() {
        return "Id,START,STOP,PATIENT,ORGANIZATION,PROVIDER,PAYER,ENCOUNTERCLASS,CODE,DESCRIPTION,BASE_ENCOUNTER_COST,TOTAL_CLAIM_COST,PAYER_COVERAGE,REASONCODE,REASONDESCRIPTION";
    }

    private TreeMap<String, String> buildAnonymizeMap() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", id);
        return map;
    }
}
