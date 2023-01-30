package com.example.flightbooking.fragments.information.privacypolicy;

import com.example.flightbooking.interfaces.Globals;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class PrivacyPolicyFragmentClient {

    public interface PrivacyPolicyFragmentInterface{
        @GET(Globals.API_ROUTES_PREFIX+"/privacy_policy")
        Call<JsonObject> getPrivacyPolicy();
    }

    private Retrofit retrofit;
    private PrivacyPolicyFragmentInterface ppfi;

    public PrivacyPolicyFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.ppfi = this.retrofit.create(PrivacyPolicyFragmentInterface.class);
    }

    public PrivacyPolicyFragmentInterface getPpfi(){return this.ppfi;}
}
