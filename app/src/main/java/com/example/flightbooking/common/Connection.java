package com.example.flightbooking.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

//Common function for connection operation
public class Connection {

    /***
     * Check if internet connection is enabled
     * @param ctx
     * @return boolean
     */
    public static boolean checkInternet(Context ctx){
        ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        boolean connected = ni != null && ni.isConnected();
        return connected;
    }

    public static OkHttpClient clientTimeOptions(){
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1,TimeUnit.MINUTES)
                .build();
    }

}
