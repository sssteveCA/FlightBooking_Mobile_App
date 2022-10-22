package com.example.flightbooking.requests.logout;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.logout.Logout;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class LogoutClient {

    public interface LogoutInterface{
        @POST(Globals.API_ROUTES_PREFIX+"/logout")
        Call<Logout> logout(@Header("Authorization") String token);
    }

    private Retrofit retrofit;
    public LogoutInterface li;

    public LogoutClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.li = this.retrofit.create(LogoutInterface.class);
    }

    public LogoutInterface getLi(){ return this.li; }
}
