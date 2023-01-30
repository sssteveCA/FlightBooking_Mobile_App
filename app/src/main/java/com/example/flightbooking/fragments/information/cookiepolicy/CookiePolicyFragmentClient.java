package com.example.flightbooking.fragments.information.cookiepolicy;

import com.example.flightbooking.interfaces.Globals;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class CookiePolicyFragmentClient {

    public interface CookiePolicyFragmentInterface{
        @GET(Globals.API_ROUTES_PREFIX+"/cookie_policy")
        Call<JsonObject> getCookiePolicy();
    }

    private Retrofit retrofit;
    private CookiePolicyFragmentInterface cpfi;

    public CookiePolicyFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.cpfi = this.retrofit.create(CookiePolicyFragmentInterface.class);
    }

    public CookiePolicyFragmentInterface getCpfi(){return this.cpfi;}
}
