package com.example.flightbooking.common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

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

}
