package com.example.flightbooking.fragments.information.terms;

import com.example.flightbooking.interfaces.Globals;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class TermsFragmentClient {

    public interface TermsFragmentInterface{
        @GET(Globals.API_ROUTES_PREFIX+"/terms")
        Call<JsonObject> terms();
    }

    private Retrofit retrofit;
    private TermsFragmentInterface tfi;

    public TermsFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.tfi = this.retrofit.create(TermsFragmentInterface.class);
    }

    public TermsFragmentInterface getTfi(){return this.tfi;}
}
