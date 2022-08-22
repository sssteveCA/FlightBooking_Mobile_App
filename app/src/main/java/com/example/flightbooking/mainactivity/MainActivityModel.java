package com.example.flightbooking.mainactivity;

import android.content.Context;

import com.example.flightbooking.common.Connection;

public class MainActivityModel {

    private Context ctx;
    private boolean connected;

    public MainActivityModel(Context ctx){
        this.ctx = ctx;
    }

    /***
     *
     *  Check if phone is connected to internet
     * @return boolean
     */
    public boolean getConnectionStatus(){
        //return Connection.checkInternet();
        this.connected = Connection.checkInternet(this.ctx);
        return this.connected;
    }
}
