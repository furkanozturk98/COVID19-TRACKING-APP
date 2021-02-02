package com.example.coronavirustracking.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.coronavirustracking.R;
import com.example.coronavirustracking.SummaryByCountryActivity;
import com.example.coronavirustracking.model.Country;
import com.example.coronavirustracking.model.GlobalSummary;
import com.example.coronavirustracking.model.SummaryByCountry;
import com.example.coronavirustracking.service.CountryAPI;
import com.example.coronavirustracking.service.GlobalSummaryAPI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.json.JSONArray;
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
    SummaryByCountry turkey;
    ArrayList<Country> countries;
    ArrayList<SummaryByCountry> summaryByCountries = new ArrayList<>();
    NotificationManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        LoadSummary loadSummary = new LoadSummary();
        LoadCategories loadCategories = new LoadCategories();

        loadSummary.start();
        loadCategories.start();


        String CHANNEL_ID = "turkey";
        String CHANNEL_NAME = "turkey covid19 summary";
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);

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

                        JSONArray jsonArray = jsonObject.getJSONArray("Countries");
                        GsonBuilder gsonBuilder = new GsonBuilder();
                        gsonBuilder.setDateFormat("dd/MM/yyy hh:mm a");
                        Gson gson = gsonBuilder.create();

                        for (int i = 0; i < jsonArray.length(); i++){
                            String summaryByCountryString = jsonArray.getJSONObject(i).toString();

                            JSONObject summaryByCountryObject = new JSONObject(summaryByCountryString.substring(summaryByCountryString.indexOf("{"), summaryByCountryString.lastIndexOf("}") + 1).replace("\\", ""));

                            SummaryByCountry summaryByCountry = new SummaryByCountry(
                                    summaryByCountryObject.getString("Country"),
                                    summaryByCountryObject.getString("CountryCode"),
                                    summaryByCountryObject.getString("Slug"),
                                    summaryByCountryObject.getString("NewConfirmed"),
                                    summaryByCountryObject.getString("TotalConfirmed"),
                                    summaryByCountryObject.getString("NewDeaths"),
                                    summaryByCountryObject.getString("TotalDeaths"),
                                    summaryByCountryObject.getString("NewRecovered"),
                                    summaryByCountryObject.getString("TotalRecovered"),
                                    summaryByCountryObject.getString("Date")
                            );

                            if(summaryByCountry.getCountryCode().contains("TR")){
                                turkey = summaryByCountry;
                            }
                            summaryByCountries.add(summaryByCountry);

                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                } else {
                    Log.e("TAG", "Error in getGenericJson:" + response.code() + " " + response.message());
                }

                notifySummaryOfTurkey();
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


    public void notifySummaryOfTurkey(){


        String content = "New deaths: "+ turkey.getNewDeaths()+
                System.getProperty("line.separator") +"New Confirmed: "+turkey.getNewConfirmed()+
                System.getProperty("line.separator") +"New Recovered: " +turkey.getNewRecovered();

        Notification notification = new Notification.Builder(this, "turkey")
                .setContentTitle("Turkey " + turkey.getDate().split("T")[0] + " covid19 summary" )
                .setContentText(content)
                .setSmallIcon(R.drawable.shape_circle1)
                .setAutoCancel(true)
                .build();
        manager.notify(52, notification);
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

    public void summaryByCountryClick(View view) {
        Intent intent = new Intent(MainActivity.this, SummaryByCountryActivity.class);
        intent.putExtra("summaryByCountries", (Serializable) summaryByCountries);
        startActivity(intent);
    }

    public void logOutClick(View view) {
        firebaseAuth.signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}