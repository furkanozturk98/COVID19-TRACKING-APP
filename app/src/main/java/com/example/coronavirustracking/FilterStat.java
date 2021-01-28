package com.example.coronavirustracking;

import android.widget.Filter;

import com.example.coronavirustracking.adapter.DailyCountrySummaryAdapter;
import com.example.coronavirustracking.model.DailyCountryData;

import java.util.ArrayList;

public class FilterStat extends Filter {

    private DailyCountrySummaryAdapter dailyCountrySummaryAdapter;
    private ArrayList<DailyCountryData> dailyCountryDataList;

    public FilterStat(DailyCountrySummaryAdapter dailyCountrySummaryAdapter, ArrayList<DailyCountryData> dailyCountryDataList) {
        this.dailyCountrySummaryAdapter = dailyCountrySummaryAdapter;
        this.dailyCountryDataList = dailyCountryDataList;
    }


    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results = new FilterResults();

        if (constraint != null && constraint.length() > 0) {
            constraint = constraint.toString().toUpperCase();

            ArrayList<DailyCountryData> filteredModels = new ArrayList<>();
            for (int i=0; i<dailyCountryDataList.size(); i++){

                if(dailyCountryDataList.get(i).getCountry().toUpperCase().contains(constraint)){
                    filteredModels.add(dailyCountryDataList.get(i));
                }

            }

            results.count = filteredModels.size();
            results.values = filteredModels;
        }
        else {
            results.count = dailyCountryDataList.size();
            results.values = dailyCountryDataList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        dailyCountrySummaryAdapter.dailyCountryDataList = (ArrayList<DailyCountryData>) results.values;
        dailyCountrySummaryAdapter.notifyDataSetChanged();
    }
}
