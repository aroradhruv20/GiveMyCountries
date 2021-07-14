package com.example.firstintern;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder> {

    private List<Country> countries=new ArrayList<>();

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent , false);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryHolder holder, int position) {
        Country currCountry=countries.get(position);
        holder.name.setText(currCountry.getName());
        holder.capital.setText(currCountry.getCapital());
        holder.region.setText(currCountry.getRegion());
        holder.subregion.setText(currCountry.getSubregion());
        holder.population.setText(currCountry.getPopulation());
        holder.borders.setText(currCountry.getBorders());
        holder.languages.setText(currCountry.getLanguages());

    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public void setCountries(List<Country> countries){
        this.countries=countries;
        notifyDataSetChanged();

    }



    class CountryHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView capital;
        private TextView region;
        private TextView subregion;
        private TextView population;
        private TextView borders;
        private  TextView languages;

        public CountryHolder(@NonNull View view){
            super(view);
            name=view.findViewById(R.id.name);
            capital=view.findViewById(R.id.capital);
            region=view.findViewById(R.id.region);
            subregion=view.findViewById(R.id.subregion);
            population=view.findViewById(R.id.population);
            borders=view.findViewById(R.id.borders);
            languages=view.findViewById(R.id.languages);
        }
    }

}
