package com.example.flightbooking.requests;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private String token;

    public TokenInterceptor(String token){
        this.token = token;
    }

    public String getToken(){return this.token;}

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        return null;
    }

}
