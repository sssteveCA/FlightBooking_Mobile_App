package com.example.flightbooking.fragments.home;

import com.example.flightbooking.common.Globals;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class HomeFragmentClient
{
    public interface HomeFragmentInterface{
        @GET("/airportsearch")
        Call<List<String>> airports_search(@Query("country") String country);

        @GET("/flightsearch")
        Call<List<String>> countries();

        @GET("/companieslist")
        Call<List<String>> companies();
    }

    private Retrofit retrofit;
    public HomeFragmentInterface hfi;
    public HomeFragmentClient(){
        this.retrofit = new Retrofit.Builder().baseUrl(Globals.BASE_URL).build();
        this.hfi = this.retrofit.create(HomeFragmentInterface.class);
    }
}
