package com.example.flightbooking.fragments.home.carrental;

import com.example.flightbooking.interfaces.Globals;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class CarRentalFragmentClient {

    public interface CarRentalFragmentInterface{
        @GET("/carrentalsearch")
        Call<JsonObject> getCarRentalInfo();
    }

    private Retrofit retrofit;
    private CarRentalFragmentInterface crfi;

    public CarRentalFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public CarRentalFragmentInterface getCrfi(){ return this.crfi; }


}
