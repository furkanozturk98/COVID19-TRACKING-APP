package com.example.coronavirustracking.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.example.coronavirustracking.R;
import com.example.coronavirustracking.adapter.DailyCountrySummaryAdapter;
import com.example.coronavirustracking.model.DailyCountryData;
import java.util.ArrayList;


public class DailyCountrySummaryActivity extends AppCompatActivity {

    DailyCountrySummaryAdapter dailyCountrySummaryAdapter;

    private RecyclerView statsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_country_summary);

        Intent intent = getIntent();
        final ArrayList<DailyCountryData> dailyCountryDataList = (ArrayList<DailyCountryData>) intent.getExtras().getSerializable("data");

        statsRv = findViewById(R.id.statsRv);

        dailyCountrySummaryAdapter = new DailyCountrySummaryAdapter(DailyCountrySummaryActivity.this, dailyCountryDataList);
        statsRv.setAdapter(dailyCountrySummaryAdapter);


    }


}