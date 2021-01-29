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

import com.example.coronavirustracking.FilterSummaryByCountry;
import com.example.coronavirustracking.R;
import com.example.coronavirustracking.model.SummaryByCountry;

import java.util.ArrayList;

public class SummaryByCountryAdapter extends RecyclerView.Adapter<SummaryByCountryAdapter.HolderStat> implements Filterable {

    private Context context;
    public ArrayList<SummaryByCountry> summaryByCountries, filterList;
    private FilterSummaryByCountry filter;

    public SummaryByCountryAdapter(Context context, ArrayList<SummaryByCountry> summaryByCountries) {
        this.context = context;
        this.summaryByCountries = summaryByCountries;
        this.filterList = summaryByCountries;
    }

    @NonNull
    @Override
    public HolderStat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_summary_by_country, parent, false);

        return new SummaryByCountryAdapter.HolderStat(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderStat holder, int position) {
        SummaryByCountry summaryByCountry = summaryByCountries.get(position);

        String countryName = summaryByCountry.getCountry();
        String countryConfirmedCase = summaryByCountry.getTotalConfirmed();
        String countryTodayCases = summaryByCountry.getTotalConfirmed();
        String countryDeaths = summaryByCountry.getTotalDeaths();
        String countryTodayDeaths = summaryByCountry.getNewDeaths();
        String countryRecovered = summaryByCountry.getTotalRecovered();
        String countryTotalRecovered = summaryByCountry.getNewRecovered();

        holder.countryName.setText(countryName);
        holder.countryConfirmedCase.setText(countryConfirmedCase);
        holder.countryTodayCases.setText(countryTodayCases);
        holder.countryDeaths.setText(countryDeaths);
        holder.countryTodayDeaths.setText(countryTodayDeaths);
        holder.countryRecovered.setText(countryRecovered);
        holder.countryTotalRecovered.setText(countryTotalRecovered);

    }

    @Override
    public int getItemCount() {
        return summaryByCountries.size();
    }

    @Override
    public Filter getFilter() {
        if(filter == null){
            filter = new FilterSummaryByCountry(SummaryByCountryAdapter.this, filterList);
        }
        return filter;
    }


    class HolderStat extends RecyclerView.ViewHolder{

        TextView countryName, countryConfirmedCase, countryTodayCases, countryDeaths, countryTodayDeaths,countryRecovered, countryTotalRecovered;

        public HolderStat(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.countryName);
            countryConfirmedCase = itemView.findViewById(R.id.countryConfirmedCase);
            countryTodayCases = itemView.findViewById(R.id.countryTodayCases);
            countryDeaths = itemView.findViewById(R.id.countryDeaths);
            countryTodayDeaths = itemView.findViewById(R.id.countryTodayDeaths);
            countryTotalRecovered = itemView.findViewById(R.id.countryTotalRecovered);
            countryRecovered = itemView.findViewById(R.id.countryRecovered);
        }
    }
}
