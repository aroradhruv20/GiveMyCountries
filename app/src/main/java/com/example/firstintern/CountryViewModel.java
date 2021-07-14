package com.example.firstintern;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class CountryViewModel extends AndroidViewModel {
    private CountryRepo repositry;
    private LiveData<List<Country>> allcountries;

    public CountryViewModel(@NonNull Application application) {
        super(application);
        repositry = new CountryRepo(application);
        allcountries=repositry.getAllcountries();
    }

    public void insert(Country country){
        repositry.insert(country);
    }

    public void  delete(Country country){
        repositry.delete(country);
    }

    public LiveData<List<Country>> getAllcountries(){
        return allcountries;
    }
}
