package com.example.firstintern;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface  CountryDao {
    @Insert
    void insert(Country country);

    @Delete
    void delete(Country country);

    //For Recycler View
    @Query("SELECT * FROM COUNTRY_TABLE")
    LiveData<List<Country> > getAllCountries();

}
