package com.example.flightbooking.fragments.home.hotel;

import android.content.Context;

import com.example.flightbooking.common.Connection;

public class HotelFragmentModel {

    private Context context;

    public HotelFragmentModel(Context ctx){
        this.context = ctx;
    }

    /***
     *
     *  Check if phone is connected to internet
     * @return boolean
     */
    public boolean getConnectionStatus(){
        return Connection.checkInternet(this.context);
    }
}
