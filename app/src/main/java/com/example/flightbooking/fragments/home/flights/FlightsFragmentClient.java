package com.example.flightbooking.fragments.home.flights;

import com.example.flightbooking.common.Globals;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class FlightsFragmentClient
{
    public interface HomeFragmentInterface{
        @GET("/airportsearch")
        Call<JsonObject> airports_search(@Query("country") String country);

        @GET("/flightsearch")
        Call<List<String>> countries();

        @GET("/companieslist")
        Call<List<String>> companies();
    }

    private Retrofit retrofit;
    private HomeFragmentInterface hfi;
    public FlightsFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.hfi = this.retrofit.create(HomeFragmentInterface.class);
    }

    public HomeFragmentInterface getHfi(){return this.hfi;}

}
