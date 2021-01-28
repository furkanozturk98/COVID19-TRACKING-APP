package com.example.coronavirustracking.service;

import com.example.coronavirustracking.model.DailyCountryData;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ByCountryAndDateAPI {

    @GET("live/country/{countryName}/status/confirmed/date/{date}")
    Call<List<DailyCountryData>> getGenericJSON(@Path("countryName") String countryName, @Path("date") String date);
}
