package com.healthcare.team.csv.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthcare.team.Anonymization.Anonymization;

import java.util.TreeMap;

public class CarePlan extends CsvPojo {
    @JsonProperty("Id")
    private String id;
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
    @JsonProperty("REASONCODE")
    private String reasoncode;
    @JsonProperty("REASONDESCRIPTION")
    private String reasondescription;

    CarePlan(){}

    public void setId(String id) {
        this.id = id;
    }

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

    public void setReasoncode(String reasoncode) {
        this.reasoncode = reasoncode;
    }

    public void setReasondescription(String reasondescription) {
        this.reasondescription = reasondescription;
    }

    public String getId() {
        return id;
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

    public String getReasoncode() {
        return reasoncode;
    }

    public String getReasondescription() {
        return reasondescription;
    }

    @Override
    public String toString() {

        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", id);

        return CarePlan.class.getCanonicalName() + ": " +
                "Id='" + Anonymization.mask(map) + '\'' +
                "start='" + start + '\'' +
                "| stop='" + stop + '\'' +
                "| patient='" + patient + '\'' +
                "| encounter='" + encounter + '\'' +
                "| code='" + code + '\'' +
                "| description='" + description + '\'' +
                "| reasoncode='" + reasoncode + '\'' +
                "| reasondescription='" + reasondescription + '\'';
    }

    @Override
    public String toCsvString() {
        return Anonymization.mask(buildAnonymizeMap()) + ',' +
                start + ',' +
                stop + ',' +
                patient + ',' +
                encounter + ',' +
                code + ',' +
                description + ',' +
                reasoncode + ',' +
                reasondescription + ',';
    }

    @Override
    public String getHeaderColumnNames() {
        return "Id,START,STOP,PATIENT,ENCOUNTER,CODE,DESCRIPTION,REASONCODE,REASONDESCRIPTION";
    }

    private TreeMap<String, String> buildAnonymizeMap() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", id);
        return map;
    }
}
