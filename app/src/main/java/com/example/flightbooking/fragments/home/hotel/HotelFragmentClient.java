package com.example.flightbooking.fragments.home.hotel;

import com.example.flightbooking.interfaces.Globals;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HotelFragmentClient {

    public interface HotelFragmentInterface{

    }

    private Retrofit retrofit;
    private HotelFragmentInterface hfi;

    public HotelFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public HotelFragmentInterface getHfi(){return this.hfi;}

}
