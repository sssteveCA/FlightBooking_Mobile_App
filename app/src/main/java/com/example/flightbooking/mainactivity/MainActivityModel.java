package com.example.flightbooking.mainactivity;

import android.content.Context;

import com.example.flightbooking.common.Connection;

import java.util.Arrays;
import java.util.List;

public class MainActivityModel {

    private Context ctx;
    private boolean connected;
    private boolean userLogged; //Check if user is logged

    public MainActivityModel(Context ctx){

        this.ctx = ctx;
        this.connected = false;
        this.userLogged = false;
    }

    public boolean isUserLogged(){return this.userLogged;}

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

    /**
     * Check if particular menu item by label require internet connection
     */
    public boolean isConnectionRequired(String label){
        if(label != null){
            List<String> internetItems = Arrays.asList("home","news"); //Label items that require internet
            String lcLabel = label.toLowerCase(); //Convert all character to lower case for insensitive comparison
            return internetItems.contains(lcLabel);
        }//if(label != null){
        else
            return true;
    }


}
