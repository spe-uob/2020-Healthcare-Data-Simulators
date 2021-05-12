package com.healthcare.team.csv.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthcare.team.Anonymization.Anonymization;

import java.util.TreeMap;

public class Payer extends CsvPojo{
    @JsonProperty("Id")
    private String id;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("ADDRESS")
    private String address;
    @JsonProperty("CITY")
    private String city;
    @JsonProperty("STATE_HEADQUARTERED")
    private String stateHeadquarters;
    @JsonProperty("ZIP")
    private String zip;
    @JsonProperty("PHONE")
    private String phone;
    @JsonProperty("AMOUNT_COVERED")
    private String amountCovered;
    @JsonProperty("AMOUNT_UNCOVERED")
    private String amountUncovered;
    @JsonProperty("REVENUE")
    private String revenue;
    @JsonProperty("COVERED_ENCOUNTERS")
    private String coveredEncounters;
    @JsonProperty("UNCOVERED_ENCOUNTERS")
    private String uncoveredEncounters;
    @JsonProperty("COVERED_MEDICATIONS")
    private String coveredMedications;
    @JsonProperty("UNCOVERED_MEDICATIONS")
    private String uncoveredMedications;
    @JsonProperty("COVERED_PROCEDURES")
    private String coveredProcedures;
    @JsonProperty("UNCOVERED_PROCEDURES")
    private String uncoveredProcedures;
    @JsonProperty("COVERED_IMMUNIZATIONS")
    private String coveredImmunizations;
    @JsonProperty("UNCOVERED_IMMUNIZATIONS")
    private String uncoveredImmunizations;
    @JsonProperty("UNIQUE_CUSTOMERS")
    private String uniqueCustomers;
    @JsonProperty("QOLS_AVG")
    private String qolsAvg;
    @JsonProperty("MEMBER_MONTHS")
    private String memberMonths;

    public Payer() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStateHeadquarters() {
        return stateHeadquarters;
    }

    public void setStateHeadquarters(String stateHeadquarters) {
        this.stateHeadquarters = stateHeadquarters;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAmountCovered() {
        return amountCovered;
    }

    public void setAmountCovered(String amountCovered) {
        this.amountCovered = amountCovered;
    }

    public String getAmountUncovered() {
        return amountUncovered;
    }

    public void setAmountUncovered(String amountUncovered) {
        this.amountUncovered = amountUncovered;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public String getCoveredEncounters() {
        return coveredEncounters;
    }

    public void setCoveredEncounters(String coveredEncounters) {
        this.coveredEncounters = coveredEncounters;
    }

    public String getUncoveredEncounters() {
        return uncoveredEncounters;
    }

    public void setUncoveredEncounters(String uncoveredEncounters) {
        this.uncoveredEncounters = uncoveredEncounters;
    }

    public String getCoveredMedications() {
        return coveredMedications;
    }

    public void setCoveredMedications(String coveredMedications) {
        this.coveredMedications = coveredMedications;
    }

    public String getUncoveredMedications() {
        return uncoveredMedications;
    }

    public void setUncoveredMedications(String uncoveredMedications) {
        this.uncoveredMedications = uncoveredMedications;
    }

    public String getCoveredProcedures() {
        return coveredProcedures;
    }

    public void setCoveredProcedures(String coveredProcedures) {
        this.coveredProcedures = coveredProcedures;
    }

    public String getUncoveredProcedures() {
        return uncoveredProcedures;
    }

    public void setUncoveredProcedures(String uncoveredProcedures) {
        this.uncoveredProcedures = uncoveredProcedures;
    }

    public String getCoveredImmunizations() {
        return coveredImmunizations;
    }

    public void setCoveredImmunizations(String coveredImmunizations) {
        this.coveredImmunizations = coveredImmunizations;
    }

    public String getUncoveredImmunizations() {
        return uncoveredImmunizations;
    }

    public void setUncoveredImmunizations(String uncoveredImmunizations) {
        this.uncoveredImmunizations = uncoveredImmunizations;
    }

    public String getUniqueCustomers() {
        return uniqueCustomers;
    }

    public void setUniqueCustomers(String uniqueCustomers) {
        this.uniqueCustomers = uniqueCustomers;
    }

    public String getQolsAvg() {
        return qolsAvg;
    }

    public void setQolsAvg(String qolsAvg) {
        this.qolsAvg = qolsAvg;
    }

    public String getMemberMonths() {
        return memberMonths;
    }

    public void setMemberMonths(String memberMonths) {
        this.memberMonths = memberMonths;
    }

    @Override
    public String toString() {

        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", id);

        return Payer.class.getCanonicalName() + ": " +
                "Id='" + Anonymization.mask(map) + '\'' +
                "| name='" + name  + '\'' +
                "| address='" + address + '\'' +
                "| city='" + city + '\'' +
                "| state_headquarters='" + stateHeadquarters + '\'' +
                "| zip='" + zip + '\'' +
                "| phone='" + phone + '\'' +
                "| amount_covered='" + amountCovered + '\'' +
                "| amount_uncovered='" + amountUncovered + '\'' +
                "| revenue='" + revenue + '\'' +
                "| covered_encounters='" + coveredEncounters + '\'' +
                "| uncovered_encounters='" + uncoveredEncounters + '\'' +
                "| covered_procedures='" + coveredProcedures + '\'' +
                "| uncovered_procedures='" + uncoveredProcedures + '\'' +
                "| covered_immunizations='" + coveredImmunizations + '\'' +
                "| uncovered_immunizations='" + uncoveredImmunizations + '\'' +
                "| unique_customers='" + uniqueCustomers + '\'' +
                "| qols_avg='" + qolsAvg + '\'' +
                "| member_months='" + memberMonths + '\'';
    }

    public String toCsvString() {
        return Anonymization.mask(buildAnonymizeMap()) + ',' +
                name + ',' +
                address + ',' +
                city + ',' +
                stateHeadquarters + ',' +
                zip + ',' +
                phone + ',' +
                amountCovered + ',' +
                amountUncovered + ',' +
                revenue + ',' +
                coveredEncounters + ',' +
                uncoveredEncounters + ',' +
                coveredProcedures + ',' +
                uncoveredProcedures + ',' +
                coveredImmunizations + ',' +
                uncoveredImmunizations + ',' +
                uniqueCustomers + ',' +
                qolsAvg + ',' +
                memberMonths + ',';
    }

    @Override
    public String getHeaderColumnNames() {
        return "Id,NAME,ADDRESS,CITY,STATE_HEADQUARTERED,ZIP,PHONE,AMOUNT_COVERED,AMOUNT_UNCOVERED,REVENUE,COVERED_ENCOUNTERS,UNCOVERED_ENCOUNTERS,COVERED_MEDICATIONS,UNCOVERED_MEDICATIONS,COVERED_PROCEDURES,UNCOVERED_PROCEDURES,COVERED_IMMUNIZATIONS,UNCOVERED_IMMUNIZATIONS,UNIQUE_CUSTOMERS,QOLS_AVG,MEMBER_MONTHS";
    }

    private TreeMap<String, String> buildAnonymizeMap() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", id);
        return map;
    }
}
