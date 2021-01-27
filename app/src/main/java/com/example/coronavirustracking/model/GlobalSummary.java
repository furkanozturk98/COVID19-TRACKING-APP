package com.example.coronavirustracking.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class GlobalSummary implements Serializable {

    @SerializedName("NewConfirmed")
    private int newConfirmed;

    @SerializedName("TotalConfirmed")
    private int totalConfirmed;

    @SerializedName("NewDeaths")
    private int newDeaths;

    @SerializedName("TotalDeaths")
    private int totalDeaths;

    @SerializedName("NewRecovered")
    private int newRecovered;

    @SerializedName("TotalRecovered")
    private int totalRecovered;

    public GlobalSummary(int newConfirmed, int totalConfirmed, int newDeaths, int totalDeaths, int newRecovered, int totalRecovered) {
        this.newConfirmed = newConfirmed;
        this.totalConfirmed = totalConfirmed;
        this.newDeaths = newDeaths;
        this.totalDeaths = totalDeaths;
        this.newRecovered = newRecovered;
        this.totalRecovered = totalRecovered;
    }

    public int getNewConfirmed() {
        return newConfirmed;
    }

    public void setNewConfirmed(int newConfirmed) {
        this.newConfirmed = newConfirmed;
    }

    public int getTotalConfirmed() {
        return totalConfirmed;
    }

    public void setTotalConfirmed(int totalConfirmed) {
        this.totalConfirmed = totalConfirmed;
    }

    public int getNewDeaths() {
        return newDeaths;
    }

    public void setNewDeaths(int newDeaths) {
        this.newDeaths = newDeaths;
    }

    public int getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(int totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public int getNewRecovered() {
        return newRecovered;
    }

    public void setNewRecovered(int newRecovered) {
        this.newRecovered = newRecovered;
    }

    public int getTotalRecovered() {
        return totalRecovered;
    }

    public void setTotalRecovered(int totalRecovered) {
        this.totalRecovered = totalRecovered;
    }
}
