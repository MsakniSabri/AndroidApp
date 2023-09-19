package com.example.projectfnackiller.drawer;

import com.androidbuts.multispinnerfilter.KeyPairBoolData;

import java.util.List;

public class DataModel {

    public int icon;
    public String name;
    public String facetName;
    public List<KeyPairBoolData> filters;

    // Constructor.
    public DataModel(int icon, String name , List<KeyPairBoolData> filters, String facetName) {

        this.icon = icon;
        this.name = name;
        this.facetName = facetName;
        this.filters = filters;

    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFacetName() {
        return facetName;
    }

    public void setFacetName(String facetName) {
        this.facetName = facetName;
    }

    public List<KeyPairBoolData> getFilters() {
        return filters;
    }

    public void setFilters(List<KeyPairBoolData> filters) {
        this.filters = filters;
    }
}