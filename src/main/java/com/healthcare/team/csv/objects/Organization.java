package com.healthcare.team.csv.objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.healthcare.team.Anonymization;
import java.util.TreeMap;

public class Organization extends CsvPojo{
    @JsonProperty("Id")
    private String id;
    @JsonProperty("NAME")
    private String name;
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
    @JsonProperty("PHONE")
    private String phone;
    @JsonProperty("REVENUE")
    private String revenue;
    @JsonProperty("UTILIZATION")
    private String utilization;

    public Organization() {
    }

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRevenue() {
        return revenue;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
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

        return Organization.class.getCanonicalName() + ": " +
                "Id='" + Anonymization.mask(map) + '\'' +
                "| name='" + name + '\'' +
                "| address='" + address + '\'' +
                "| city='" + city + '\'' +
                "| state='" + state + '\'' +
                "| zip='" + zip + '\'' +
                "| lat='" + lat + '\'' +
                "| lon='" + lon + '\'' +
                "| phone='" + phone + '\'' +
                "| revenue='" + revenue + '\'' +
                "| utilization='" + utilization + '\'';
    }

    @Override
    public String toCsvString() {
        return Anonymization.mask(buildAnonymizeMap()) + ',' +
                name + ',' +
                address + ',' +
                city + ',' +
                state + ',' +
                zip + ',' +
                lat + ',' +
                lon + ',' +
                phone + ',' +
                revenue + ',' +
                utilization + ',';
    }

    @Override
    public String getHeaderColumnNames() {
        return null;
    }

    private TreeMap<String, String> buildAnonymizeMap() {
        TreeMap<String, String> map = new TreeMap<>();
        map.put("NHSNumber", id);
        return map;
    }
}
