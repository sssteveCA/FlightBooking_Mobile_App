package com.example.flightbooking.requests.logout;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.logout.Logout;
import com.example.flightbooking.requests.TokenInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Header;
import retrofit2.http.POST;

public class LogoutClient {

    public interface LogoutInterface{
        @POST(Globals.API_ROUTES_PREFIX+"/logout")
        Call<Logout> logout();
    }

    private Retrofit retrofit;
    private String token;
    public LogoutInterface li;

    public LogoutClient(String token){
        this.token = token;
        TokenInterceptor ti = new TokenInterceptor(this.token);
        OkHttpClient ohc = new OkHttpClient.Builder()
                .addInterceptor(ti)
                .build();
        this.retrofit = new Retrofit.Builder()
                .client(ohc)
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.li = this.retrofit.create(LogoutInterface.class);
    }

    public LogoutInterface getLi(){ return this.li; }
}
