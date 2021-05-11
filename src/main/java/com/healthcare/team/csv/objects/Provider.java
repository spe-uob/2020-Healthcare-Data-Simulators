package com.healthcare.team.csv.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthcare.team.Anonymization;

import java.util.TreeMap;

public class Provider extends  CsvPojo{
    @JsonProperty("Id")
    private String id;
    @JsonProperty("ORGANIZATION")
    private String organization;
    @JsonProperty("NAME")
    private String name;
    @JsonProperty("GENDER")
    private String gender;
    @JsonProperty("SPECIALITY")
    private String speciality;
    @JsonProperty("ADDRESS")
    private String address;
    @JsonProperty("CITY")
    private String city;
    @JsonProperty("STATE")
    private String state;
    @JsonProperty("ZIP")
    private String zip;
    @JsonProperty("LAT")
    private String lat;
    @JsonProperty("LON")
    private String lon;
    @JsonProperty("UTILIZATION")
    private String utilization;

    public Provider(){
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getUtilization() {
        return utilization;
    }

    public void setUtilization(String utilization) {
        this.utilization = utilization;
    }

    @Override
    public String toString() {

        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", id);

        return Provider.class.getCanonicalName() + ": " +
                "Id='" + Anonymization.mask(map) + '\'' +
                "| organization='" + organization + '\'' +
                "| name='" + name + '\'' +
                "| gender='" + gender + '\'' +
                "| speciality='" + speciality + '\'' +
                "| address='" + address + '\'' +
                "| city='" + city + '\'' +
                "| state='" + state + '\'' +
                "| zip='" + zip + '\'' +
                "| lat='" + lat + '\'' +
                "| lon='" + lon + '\'' +
                "| utilization='" + utilization + '\'';
    }

    public String toCsvString() {
        return Anonymization.mask(buildAnonymizeMap()) + ',' +
                organization + ',' +
                name + ',' +
                gender + ',' +
                speciality + ',' +
                address + ',' +
                city + ',' +
                state + ',' +
                zip + ',' +
                lat + ',' +
                lon + ',';
    }

    @Override
    public String getHeaderColumnNames() {
        return "Id,ORGANIZATION,NAME,GENDER,SPECIALITY,ADDRESS,CITY,STATE,ZIP,LAT,LON,UTILIZATION";
    }

    private TreeMap<String, String> buildAnonymizeMap() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", id);
        return map;
    }
}
