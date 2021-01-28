package com.example.coronavirustracking.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.coronavirustracking.R;
import com.example.coronavirustracking.model.Country;
import com.example.coronavirustracking.model.GlobalSummary;
import com.example.coronavirustracking.service.CountryAPI;
import com.example.coronavirustracking.service.GlobalSummaryAPI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    GlobalSummary globalSummary;
    ArrayList<Country> countries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();


        LoadSummary loadSummary = new LoadSummary();
        LoadCategories loadCategories = new LoadCategories();

        loadSummary.start();
        loadCategories.start();

    }

    public void loadSummary() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        GlobalSummaryAPI globalSummaryAPI = retrofit.create(GlobalSummaryAPI.class);
        Call<JsonObject> call = globalSummaryAPI.getGenericJSON();

        call.enqueue(new Callback<JsonObject>() {

            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e("TAG", "response 33: " + new Gson().toJson(response.body()));

                    try {
                        JSONObject jsonObject = new JSONObject(new Gson().toJson(response.body()));

                        String globalString = new Gson().toJson(jsonObject.getString("Global"));
                        JSONObject global = new JSONObject(globalString.substring(globalString.indexOf("{"), globalString.lastIndexOf("}") + 1).replace("\\", ""));

                        int NewConfirmed = Integer.parseInt(global.getString("NewConfirmed"));
                        int TotalConfirmed = Integer.parseInt(global.getString("TotalConfirmed"));
                        int NewDeaths = Integer.parseInt(global.getString("NewDeaths"));
                        int TotalDeaths = Integer.parseInt(global.getString("TotalDeaths"));
                        int NewRecovered = Integer.parseInt(global.getString("NewRecovered"));
                        int TotalRecovered = Integer.parseInt(global.getString("TotalRecovered"));

                        globalSummary = new GlobalSummary(NewConfirmed,TotalConfirmed,NewDeaths,TotalDeaths,NewRecovered,TotalRecovered);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e("TAG", "Error in getGenericJson:" + response.code() + " " + response.message());
                }

            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("TAG", "Response Error");
            }
        });
    }

    public void loadCategories(){
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        CountryAPI countryAPI = retrofit.create(CountryAPI.class);
        Call<List<Country>> call = countryAPI.getCountries();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if(response.isSuccessful()){
                    List<Country> responseList = response.body();
                    countries = new ArrayList<>(responseList);

                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {

            }
        });
    }

    class LoadSummary extends Thread {
        @Override
        public void run() {
            loadSummary();
        }
    }

    class LoadCategories extends Thread {
        @Override
        public void run() {
            loadCategories();
        }
    }


    public void getFullSummary(View view) {
        Intent intent = new Intent(MainActivity.this, FullSummaryActivity.class);
        intent.putExtra("globalSummaryObject", (Serializable) globalSummary);
        startActivity(intent);
    }

    public void byCountryAndDateClick(View view) {
        Intent intent = new Intent(MainActivity.this, SelectCountryAndDateActivity.class);
        intent.putExtra("countries", (Serializable) countries);
        startActivity(intent);
    }

    public void fromDayOneClick(View view) {

    }

    public void logOutClick(View view) {
        firebaseAuth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}