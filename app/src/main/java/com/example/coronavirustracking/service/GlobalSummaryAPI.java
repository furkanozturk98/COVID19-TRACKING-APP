package com.example.coronavirustracking.service;

import com.google.gson.JsonObject;


import retrofit2.Call;
import retrofit2.http.GET;

public interface GlobalSummaryAPI {

    @GET("summary")
    Call<JsonObject> getGenericJSON();
}
