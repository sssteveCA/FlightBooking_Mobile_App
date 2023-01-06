package com.example.flightbooking.fragments.news;

import com.example.flightbooking.interfaces.Globals;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class NewsFragmentClient {

    public interface NewsFragmentInterface{

    }

    private Retrofit retrofit;
    public NewsFragmentInterface nfi;

    public NewsFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.nfi = this.retrofit.create(NewsFragmentInterface.class);
    }

    public NewsFragmentInterface getNfi(){return this.nfi;}

}
