package com.example.coronavirustracking.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coronavirustracking.FilterStat;
import com.example.coronavirustracking.R;
import com.example.coronavirustracking.model.DailyCountryData;

import java.util.ArrayList;

public class DailyCountrySummaryAdapter extends RecyclerView.Adapter<DailyCountrySummaryAdapter.HolderStat> implements Filterable {

    private Context context;
    public ArrayList<DailyCountryData> dailyCountryDataList, filterList;
    private FilterStat filter;

    public DailyCountrySummaryAdapter(Context context, ArrayList<DailyCountryData> dailyCountryDataList) {
        this.context = context;
        this.dailyCountryDataList = dailyCountryDataList;
    }

    @NonNull
    @Override
    public HolderStat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_stat, parent, false);

        return new HolderStat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderStat holder, int position) {
        DailyCountryData dailyCountryData = dailyCountryDataList.get(position);

        String countryName = dailyCountryData.getCountry();
        String countryConfirmedCase = dailyCountryData.getConfirmed();
        String countryDeaths = dailyCountryData.getDeaths();
        String countryRecovered = dailyCountryData.getRecovered();
        String countryActive = dailyCountryData.getActive();
        String countryDate = dailyCountryData.getDate();

        holder.countryName.setText(countryName);
        holder.countryConfirmedCase.setText(countryConfirmedCase);
        holder.countryDeaths.setText(countryDeaths);
        holder.countryRecovered.setText(countryRecovered);
        holder.countryActive.setText(countryActive);
        holder.countryDate.setText(countryDate);

    }

    @Override
    public int getItemCount() {
        return dailyCountryDataList.size();
    }

    @Override
    public Filter getFilter() {
        if(filter != null){
            filter = new FilterStat(this, filterList);
        }
        return null;
    }


    class HolderStat extends RecyclerView.ViewHolder{

        TextView countryName, countryConfirmedCase, countryDeaths, countryRecovered, countryActive, countryDate;

        public HolderStat(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.countryName);
            countryConfirmedCase = itemView.findViewById(R.id.countryConfirmedCase);
            countryDeaths = itemView.findViewById(R.id.countryDeaths);
            countryRecovered = itemView.findViewById(R.id.countryRecovered);
            countryActive = itemView.findViewById(R.id.countryActive);
            countryDate = itemView.findViewById(R.id.countryDate);
        }
    }
}
