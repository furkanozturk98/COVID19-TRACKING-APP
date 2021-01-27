package com.example.coronavirustracking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.coronavirustracking.model.Country;

import java.util.ArrayList;
import java.util.Calendar;

public class ByCountryAndDate extends AppCompatActivity {

    ArrayList<Country> countries;
    ArrayList<String> countryNames = new ArrayList<>();
    Spinner spinner;

    DatePickerDialog datePickerDialog;
    TextView selectedCountry, selectedStartDateText, selectedEndDateText;
    Calendar calendar;
    int year, month, dayOfMonth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_by_country_and_date);

        setTitle("Select Country And Date");

        Intent intent = getIntent();
        countries = (ArrayList<Country>) intent.getExtras().getSerializable("countries");

        selectedCountry = findViewById(R.id.selectedCountry);
        selectedStartDateText = findViewById(R.id.selectedStartDateText);
        selectedEndDateText = findViewById(R.id.selectedEndDateText);

        if (countries != null) {
            for (Country country : countries) {
                countryNames.add(country.getName());
            }
        }

        spinner = findViewById(R.id.spinner);

        spinner.setAdapter(new ArrayAdapter<>(ByCountryAndDate.this,
                android.R.layout.simple_spinner_dropdown_item, countryNames));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position != 0) {
                    selectedCountry.setText("Selected Country: "+countryNames.get(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void startDateClick(View view) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(ByCountryAndDate.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        selectedStartDateText.setText("Start date: " + day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    public void endDateClick(View view) {
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(ByCountryAndDate.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        selectedEndDateText.setText("End date: " +day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();
    }

    public void fetch(View view) {
        // hepsinin seçili olduğunuı kontrol et.
        //end date start date den önce olmamalı.
    }
}