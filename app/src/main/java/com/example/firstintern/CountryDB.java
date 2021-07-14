package com.example.firstintern;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


@Database(entities = {Country.class} , version = 1)
public abstract class CountryDB extends RoomDatabase {

    private static CountryDB instance;

    public  static List<Country> countries;

    private static Context activity;

    public abstract CountryDao countryDao();

    public static synchronized CountryDB getInstance(Context context){

        activity =context.getApplicationContext();
        if(instance==null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), CountryDB.class,
            "country_db").fallbackToDestructiveMigration().addCallback(roomCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback= new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };




    public static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private CountryDao countryDao;
        private PopulateDbAsyncTask(CountryDB db){
            countryDao= db.countryDao();
        }



        @Override
        protected Void doInBackground(Void... voids) {
            try {
                fillWithStartingData(activity);
            }catch (Exception e) {

            }
            return null;
        }
    }



    private static void fillWithStartingData(Context context){
        CountryDao dao=getInstance(context).countryDao();
        try {
            for(Country a:countries){
/*                JSONObject country= countries.getJSONObject(i);
                String name=country.getString("name");
                String capital=country.getString("capital");
                String region=country.getString("region");
                String subregion=country.getString("subregion");
                String population=country.getString("population");
                String borders="borders";
                String languages="languages";

                dao.insert(new Country(name,capital,region,subregion,population,borders,languages));*/
                dao.insert(a);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }



    ;


}



