package com.example.firstintern;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import android.os.Bundle;
import android.util.Log;

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

public class MainActivity extends AppCompatActivity {

    private CountryViewModel countryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET, "https://restcountries.eu/rest/v2/region/asia", null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                try {
                    Log.d("myapp", "The response is" + response.getJSONObject(0).getString("name"));
                    for(int i=0;i<response.length();i++){
                        JSONObject country= response.getJSONObject(i);
                        String name=country.getString("name");
                        String capital=country.getString("capital");
                        String region=country.getString("region");
                        String subregion=country.getString("subregion");
                        String population=country.getString("population");
                        String borders="borders";
                        String languages="languages";

                        CountryDB.countries.add(new Country(name,capital,region,subregion,population,borders,languages));
                    }

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("myapp","Something went wrong");
            }
        });

        requestQueue.add(jsonArrayRequest);


        RecyclerView recyclerView=findViewById(R.id.recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        CountryAdapter adapter=new CountryAdapter();
        recyclerView.setAdapter(adapter);



        countryViewModel=ViewModelProviders.of(this).get(CountryViewModel.class);
        countryViewModel.getAllcountries().observe(this, new Observer<List<Country>>() {

            @Override
            public void onChanged(List<Country> countries) {
                adapter.setCountries(countries);
            }
        });



    }
}