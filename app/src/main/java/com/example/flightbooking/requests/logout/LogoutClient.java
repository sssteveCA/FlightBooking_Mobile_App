package com.example.flightbooking.requests.logout;

import android.util.Log;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.logout.Logout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class LogoutClient {

    public interface LogoutInterface{
        @POST(Globals.API_ROUTES_PREFIX+"/logout")
        Call<Logout> logout(@Header("Authorization") String authorization);
    }

    private Retrofit retrofit;
    private String token;
    public LogoutInterface li;

    public LogoutClient(String token){
        this.token = token;
        Log.i("LogoutClient","constructor token => "+this.token);
        Gson gson = new GsonBuilder().setLenient().create();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        this.li = this.retrofit.create(LogoutInterface.class);
    }

    public String getToken(){return this.token;}
    public LogoutInterface getLi(){ return this.li; }
}
