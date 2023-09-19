package com.example.projectfnackiller.API;

import java.io.Serializable;
import java.util.HashMap;

public class Library implements Serializable {
    String name;
    String address;
    String facet_name;
    double latitude;
    double longitude;
    //HashMap<Book, Integer> availableBooks  = new HashMap<Book, Integer>();


    public Library(String name, String address, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        String finalString1 = name.replaceAll(" ", "_").toLowerCase();
        String finalString2 = finalString1.replaceAll("é", "e");
        String finalString3 = finalString2.replaceAll("-", "_");
        this.facet_name = finalString3.replaceAll("è", "e");

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getFacetName() {
        return facet_name;
    }

    public void setFacetName(String facet_name) {
        this.facet_name = facet_name;
    }
}