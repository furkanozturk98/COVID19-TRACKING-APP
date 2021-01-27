package com.example.coronavirustracking;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.coronavirustracking.model.GlobalSummary;

public class FullSummaryActivity extends AppCompatActivity {

    GlobalSummary globalSummary;
    TextView newConfirmed, totalConfirmed, newDeaths, totalDeaths, newRecovered, totalRecovered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_summary);

        setTitle("Global Summary");

        Intent intent = getIntent();
        globalSummary = (GlobalSummary) intent.getExtras().getSerializable("globalSummaryObject");

        System.out.println(globalSummary.getTotalDeaths());

        newConfirmed = findViewById(R.id.newConfirmed);
        totalConfirmed = findViewById(R.id.totalConfirmed);
        newDeaths = findViewById(R.id.newDeaths);
        totalDeaths = findViewById(R.id.totalDeaths);
        newRecovered = findViewById(R.id.newRecovered);
        totalRecovered = findViewById(R.id.totalRecovered);

        newConfirmed.setText(String.valueOf(globalSummary.getNewConfirmed()));
        totalConfirmed.setText(String.valueOf(globalSummary.getTotalConfirmed()));
        newDeaths.setText(String.valueOf(globalSummary.getNewDeaths()));
        totalDeaths.setText(String.valueOf(globalSummary.getTotalDeaths()));
        newRecovered.setText(String.valueOf(globalSummary.getNewRecovered()));
        totalRecovered.setText(String.valueOf(globalSummary.getTotalRecovered()));


    }

    public void backClick(View view){
        Intent intent = new Intent(FullSummaryActivity.this,MainActivity.class);
        startActivity(intent);
    }
}