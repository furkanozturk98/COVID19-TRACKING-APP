package com.example.coronavirustracking;

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

import com.example.coronavirustracking.adapter.DailyCountrySummaryAdapter;
import com.example.coronavirustracking.adapter.SummaryByCountryAdapter;
import com.example.coronavirustracking.model.DailyCountryData;
import com.example.coronavirustracking.model.SummaryByCountry;
import com.example.coronavirustracking.view.DailyCountrySummaryActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SummaryByCountryActivity extends AppCompatActivity {

    SummaryByCountryAdapter summaryByCountryAdapter;

    private EditText searchText;
    private ImageButton sortBtn;
    private RecyclerView statsRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary_by_country);

        Intent intent = getIntent();
        final ArrayList<SummaryByCountry> summaryByCountries = (ArrayList<SummaryByCountry>) intent.getExtras().getSerializable("summaryByCountries");

        searchText = findViewById(R.id.searchText);
        sortBtn = findViewById(R.id.sortBtn);
        statsRv = findViewById(R.id.statsRv);

        summaryByCountryAdapter = new SummaryByCountryAdapter(SummaryByCountryActivity.this, summaryByCountries);
        statsRv.setAdapter(summaryByCountryAdapter);


        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    summaryByCountryAdapter.getFilter().filter(s);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        final PopupMenu popupMenu = new PopupMenu(SummaryByCountryActivity.this, sortBtn);
        popupMenu.getMenu().add(Menu.NONE, 0,0,"Ascending");
        popupMenu.getMenu().add(Menu.NONE, 1,1,"Descending");
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                if(id == 0){
                    Collections.sort(summaryByCountries, new SortStatCountryAsc());
                    summaryByCountryAdapter.notifyDataSetChanged();
                }
                else if(id == 1){
                    Collections.sort(summaryByCountries, new SortStatCountryDesc());
                    summaryByCountryAdapter.notifyDataSetChanged();
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

    public class SortStatCountryAsc implements Comparator<SummaryByCountry> {
        @Override
        public int compare(SummaryByCountry o1, SummaryByCountry o2) {
            return o1.getCountry().compareTo(o2.getCountry());
        }
    }

    public class SortStatCountryDesc implements Comparator<SummaryByCountry>{
        @Override
        public int compare(SummaryByCountry o1, SummaryByCountry o2) {
            return o2.getCountry().compareTo(o1.getCountry());
        }
    }
}