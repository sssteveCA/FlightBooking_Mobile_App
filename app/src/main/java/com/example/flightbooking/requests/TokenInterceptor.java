package com.example.flightbooking.requests;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private String token;

    public TokenInterceptor(String token){
        this.token = token;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {

        Log.i("TokenInterceptor","intercept token => "+this.token);
        Request request = chain.request().newBuilder()
                .header("Authorization", "Bearer "+this.token)
                .build();

        return chain.proceed(request);
    }

}
