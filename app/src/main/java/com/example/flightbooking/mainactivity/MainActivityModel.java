package com.example.flightbooking.mainactivity;

import android.content.Context;

import com.example.flightbooking.common.Connection;

public class MainActivityModel {

    public MainActivityModel(){

    }

    /***
     *
     *  Check if phone is connected to internet
     * @param ctx
     * @return boolean
     */
    public boolean getConnectionStatus(Context ctx){
        //return Connection.checkInternet();
        return Connection.checkInternet(ctx);
    }
}
