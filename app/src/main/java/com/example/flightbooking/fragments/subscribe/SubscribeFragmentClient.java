package com.example.flightbooking.fragments.subscribe;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.subscribe.SubscribeFormInputs;
import com.example.flightbooking.models.subscribe.SubscribeFormResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class SubscribeFragmentClient {

    public interface SubscribeFragmentInterface{
        @POST(Globals.API_ROUTES_PREFIX+"/register")
        Call<SubscribeFormResponse> subscribe(@Body SubscribeFormInputs sfi);
    }

    private Retrofit retrofit;
    public SubscribeFragmentInterface sfi;

    public SubscribeFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public SubscribeFragmentInterface getSfi(){return this.sfi;}
}
