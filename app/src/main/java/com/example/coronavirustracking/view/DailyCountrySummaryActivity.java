package com.example.coronavirustracking.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupMenu;

import com.example.coronavirustracking.R;
import com.example.coronavirustracking.adapter.DailyCountrySummaryAdapter;
import com.example.coronavirustracking.model.DailyCountryData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class DailyCountrySummaryActivity extends AppCompatActivity {

    DailyCountrySummaryAdapter dailyCountrySummaryAdapter;
    private EditText searchText;
    private ImageButton sortBtn;
    private RecyclerView statsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_country_summary);

        Intent intent = getIntent();
        final ArrayList<DailyCountryData> dailyCountryDataList = (ArrayList<DailyCountryData>) intent.getExtras().getSerializable("data");

        searchText = findViewById(R.id.searchText);
        sortBtn = findViewById(R.id.sortBtn);
        statsRv = findViewById(R.id.statsRv);

        System.out.println(dailyCountryDataList.size());
        dailyCountrySummaryAdapter = new DailyCountrySummaryAdapter(DailyCountrySummaryActivity.this, dailyCountryDataList);
        statsRv.setAdapter(dailyCountrySummaryAdapter);


        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    dailyCountrySummaryAdapter.getFilter().filter(s);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        final PopupMenu popupMenu = new PopupMenu(DailyCountrySummaryActivity.this, sortBtn);
        popupMenu.getMenu().add(Menu.NONE, 0,0,"Ascending");
        popupMenu.getMenu().add(Menu.NONE, 1,1,"Descending");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id == 0){
                    Collections.sort(dailyCountryDataList, new SortStatCountryAsc());
                    dailyCountrySummaryAdapter.notifyDataSetChanged();
                }
                else if(id == 1){
                    Collections.sort(dailyCountryDataList, new SortStatCountryDesc());
                    dailyCountrySummaryAdapter.notifyDataSetChanged();
                }
                return false;
            }
        });

        sortBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupMenu.show();
            }
        });
    }

    public class SortStatCountryAsc implements Comparator<DailyCountryData>{
        @Override
        public int compare(DailyCountryData o1, DailyCountryData o2) {
            return o1.getCountry().compareTo(o2.getCountry());
        }
    }

    public class SortStatCountryDesc implements Comparator<DailyCountryData>{
        @Override
        public int compare(DailyCountryData o1, DailyCountryData o2) {
            return o2.getCountry().compareTo(o1.getCountry());
        }
    }
}