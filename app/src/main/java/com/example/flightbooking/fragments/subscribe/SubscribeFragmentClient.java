package com.example.flightbooking.fragments.subscribe;

import com.example.flightbooking.interfaces.Globals;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubscribeFragmentClient {



    private Retrofit retrofit;

    public SubscribeFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
