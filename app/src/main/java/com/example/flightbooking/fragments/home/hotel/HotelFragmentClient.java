package com.example.flightbooking.fragments.home.hotel;

import android.provider.Settings;

import com.example.flightbooking.interfaces.Globals;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class HotelFragmentClient {

    public interface HotelFragmentInterface{
        @GET(Globals.API_ROUTES_PREFIX+"/hotels")
        Call<JsonObject> hotels();

        @GET(Globals.API_ROUTES_PREFIX+"/hotelcountries")
        Call<List<String>> countries();

        @GET(Globals.API_ROUTES_PREFIX+"/hotelcities")
        Call<List<String>> cities(@Query("country")String country);

        @GET(Globals.API_ROUTES_PREFIX+"/hotelsearch")
        Call<List<String>> hotels(@Query("country")String country,@Query("city")String city);
    }

    private Retrofit retrofit;
    private HotelFragmentInterface hfi;

    public HotelFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.hfi = this.retrofit.create(HotelFragmentInterface.class);
    }

    public HotelFragmentInterface getHfi(){return this.hfi;}

}
