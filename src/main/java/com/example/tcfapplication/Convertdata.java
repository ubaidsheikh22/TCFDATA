package com.example.tcfapplication;

public class Convertdata {

    private String Location;
    private String CampuseName;

    public String getLocation() {
        return Location;
    }

    public String getCampuseName() {
        return CampuseName;
    }

    public Convertdata(String location, String campuseName){
        this.Location=location;
        this.CampuseName=campuseName;

    }
}
