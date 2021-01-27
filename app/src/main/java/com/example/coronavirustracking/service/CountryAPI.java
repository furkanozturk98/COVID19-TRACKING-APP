package com.example.coronavirustracking.service;

import com.example.coronavirustracking.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CountryAPI {

    @GET("countries")
    Call<List<Country>> getCountries();
}
