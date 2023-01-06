package com.example.flightbooking.fragments.login;

import com.example.flightbooking.common.Connection;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.response.login.Auth;
import com.example.flightbooking.models.requests.login.LoginFormInputs;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class LoginFragmentClient {

    public interface LoginFragmentInterface{
        @POST(Globals.API_ROUTES_PREFIX+"/login")
        Call<Auth> login(@Body LoginFormInputs lfi);
    }

    private OkHttpClient client;
    private Retrofit retrofit;
    private LoginFragmentInterface lfi;

    public LoginFragmentClient(){
        //this.client = Connection.clientTimeOptions();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                //.client(this.client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.lfi = this.retrofit.create(LoginFragmentInterface.class);
    }

    public LoginFragmentInterface getLfi(){return this.lfi;}
}
