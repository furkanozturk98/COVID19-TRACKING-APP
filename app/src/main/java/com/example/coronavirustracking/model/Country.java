package com.example.coronavirustracking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Country implements Serializable {

    @SerializedName("Country")
    private String name;

    @SerializedName("Slug")
    private String slug;

    @SerializedName("ISO2")
    private String iso2;

    public Country(String name, String slug, String iso2) {
        this.name = name;
        this.slug = slug;
        this.iso2 = iso2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getIso2() {
        return iso2;
    }

    public void setIso2(String iso2) {
        this.iso2 = iso2;
    }

    /*@SerializedName("Confirmed")
    private int confirmed;

    @SerializedName("Deaths")
    private int deaths;

    @SerializedName("Recovered")
    private int recovered;

    @SerializedName("Active")
    private int active;

    @SerializedName("Date")
    private String date;

    public Country(String name, int confirmed, int deaths, int recovered, int active, String date) {
        this.name = name;
        this.confirmed = confirmed;
        this.deaths = deaths;
        this.recovered = recovered;
        this.active = active;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public void setRecovered(int recovered) {
        this.recovered = recovered;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }*/


}
