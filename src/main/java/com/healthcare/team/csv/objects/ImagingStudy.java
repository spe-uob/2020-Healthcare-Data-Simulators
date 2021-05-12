package com.healthcare.team.csv.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthcare.team.Anonymization.Anonymization;

import java.util.TreeMap;

public class ImagingStudy extends CsvPojo{
    @JsonProperty("Id")
    private String id;
    @JsonProperty("DATE")
    private String date;
    @JsonProperty("PATIENT")
    private String patient;
    @JsonProperty("ENCOUNTER")
    private String encounter;
    @JsonProperty("SERIES_UID")
    private String seriesUid;
    @JsonProperty("BODYSITE_CODE")
    private String bodysiteCode;
    @JsonProperty("BODYSITE_DESCRIPTION")
    private String bodysiteDescription;
    @JsonProperty("MODALITY_CODE")
    private String modalityCode;
    @JsonProperty("MODALITY_DESCRIPTION")
    private String modalityDescription;
    @JsonProperty("INSTANCE_UID")
    private String instanceUid;
    @JsonProperty("SOP_CODE")
    private String sopCode;
    @JsonProperty("SOP_DESCRIPTION")
    private String sopDescription;
    @JsonProperty("PROCEDURE_CODE")
    private String procedureCode;

    public ImagingStudy() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSeriesUid() {
        return seriesUid;
    }

    public void setSeriesUid(String seriesUid) {
        this.seriesUid = seriesUid;
    }

    public String getBodysiteCode() {
        return bodysiteCode;
    }

    public void setBodysiteCode(String bodysiteCode) {
        this.bodysiteCode = bodysiteCode;
    }

    public String getBodysiteDescription() {
        return bodysiteDescription;
    }

    public void setBodysiteDescription(String bodysiteDescription) {
        this.bodysiteDescription = bodysiteDescription;
    }

    public String getModalityCode() {
        return modalityCode;
    }

    public void setModalityCode(String modalityCode) {
        this.modalityCode = modalityCode;
    }

    public String getModalityDescription() {
        return modalityDescription;
    }

    public void setModalityDescription(String modalityDescription) {
        this.modalityDescription = modalityDescription;
    }

    public String getInstanceUid() {
        return instanceUid;
    }

    public void setInstanceUid(String instanceUid) {
        this.instanceUid = instanceUid;
    }

    public String getSopCode() {
        return sopCode;
    }

    public void setSopCode(String sopCode) {
        this.sopCode = sopCode;
    }

    public String getSopDescription() {
        return sopDescription;
    }

    public void setSopDescription(String sopDescription) {
        this.sopDescription = sopDescription;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    @Override
    public String toString() {

        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", id);

        return ImagingStudy.class.getCanonicalName() + ": " +
                "Id='" + Anonymization.mask(map) + '\'' +
                "| date='" + date + '\'' +
                "| patient='" + patient + '\'' +
                "| encounter='" + encounter + '\'' +
                "| series_uid='" + seriesUid + '\'' +
                "| bodysite_description='" + bodysiteDescription + '\'' +
                "| modality_code='" + modalityCode + '\'' +
                "| modality_description='" + modalityDescription + '\'' +
                "| instance_uid='" + instanceUid + '\'' +
                "| sop_code='" + sopCode + '\'' +
                "| sop_description='" + sopDescription + '\'' +
                "| procedure_code='" + procedureCode + '\'';
    }

    public String toCsvString() {
        return Anonymization.mask(buildAnonymizeMap()) + ',' +
                date + ',' +
                patient + ',' +
                encounter + ',' +
                seriesUid + ',' +
                bodysiteDescription + ',' +
                modalityCode + ',' +
                modalityDescription + ',' +
                instanceUid + ',' +
                sopCode + ',' +
                sopDescription + ',' +
                procedureCode + ',';
    }

    @Override
    public String getHeaderColumnNames() {
        return "Id,DATE,PATIENT,ENCOUNTER,SERIES_UID,BODYSITE_CODE,BODYSITE_DESCRIPTION,MODALITY_CODE,MODALITY_DESCRIPTION,INSTANCE_UID,SOP_CODE,SOP_DESCRIPTION,PROCEDURE_CODE";
    }

    private TreeMap<String, String> buildAnonymizeMap() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", id);
        return map;
    }
}
