package com.example.firstintern;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CountryRepo {
    private CountryDao countryDao;
    private LiveData<List<Country>> allcountries;

    public CountryRepo(Application application){
        CountryDB database= CountryDB.getInstance(application);
        countryDao=database.countryDao();
        allcountries=countryDao.getAllCountries();
    }

    public void insert(Country country){
        new InsertCountryAsyncTask(countryDao).execute(country);
    }

    public void delete(Country country){
        new DeleteCountryAsyncTask(countryDao).execute(country);
    }

    public LiveData<List<Country>> getAllcountries(){
        return allcountries;
    }

    public static class InsertCountryAsyncTask extends AsyncTask<Country,Void, Void>{
        private CountryDao countryDao;

        private InsertCountryAsyncTask(CountryDao countryDao){
            this.countryDao=countryDao;
        }

        @Override
        protected Void doInBackground(Country... countries){
            countryDao.insert(countries[0]);
            return null;
        }
    }

    public static class DeleteCountryAsyncTask extends AsyncTask<Country,Void, Void>{
        private CountryDao countryDao;

        private DeleteCountryAsyncTask(CountryDao countryDao){
            this.countryDao=countryDao;
        }

        @Override
        protected Void doInBackground(Country... countries){
            countryDao.delete(countries[0]);
            return null;
        }
    }
}
