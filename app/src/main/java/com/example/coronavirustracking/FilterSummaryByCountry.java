package com.example.coronavirustracking;

import android.widget.Filter;

import com.example.coronavirustracking.adapter.SummaryByCountryAdapter;
import com.example.coronavirustracking.model.SummaryByCountry;

import java.util.ArrayList;

public class FilterSummaryByCountry extends Filter {

    private SummaryByCountryAdapter summaryByCountryAdapter;
    private ArrayList<SummaryByCountry> summaryByCountries;

    public FilterSummaryByCountry(SummaryByCountryAdapter summaryByCountryAdapter, ArrayList<SummaryByCountry> summaryByCountries) {
        this.summaryByCountryAdapter = summaryByCountryAdapter;
        this.summaryByCountries = summaryByCountries;
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();

            ArrayList<SummaryByCountry> filteredModels = new ArrayList<>();
            for (int i=0; i<summaryByCountries.size(); i++){

                if(summaryByCountries.get(i).getCountry().toUpperCase().contains(constraint)){
                    filteredModels.add(summaryByCountries.get(i));
                }

            }

            results.count = filteredModels.size();
            results.values = filteredModels;
        }
        else {
            results.count = summaryByCountries.size();
            results.values = summaryByCountries;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        summaryByCountryAdapter.summaryByCountries = (ArrayList<SummaryByCountry>) results.values;
        summaryByCountryAdapter.notifyDataSetChanged();
    }
}
