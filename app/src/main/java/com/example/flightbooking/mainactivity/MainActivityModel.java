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
     * Get the menu item fragment package clicked by the user
     * @param label
     * @return
     */
    public String getMenuItemFragmentPackage(String label){
        String pkg = "";
        if(label.equalsIgnoreCase("Home"))
            pkg = "com.example.flightbooking.fragments.home.HomeFragment";
        else if(label.equalsIgnoreCase("Chi siamo"))
            pkg = "com.example.flightbooking.fragments.aboutus.AboutUsFragment";
        else if(label.equalsIgnoreCase("Contatti"))
            pkg = "com.example.flightbooking.fragments.contacts.ContactsFragment";
        else if(label.equalsIgnoreCase("Login"))
            pkg = "com.example.flightbooking.fragments.login.LoginFragment";
        else if(label.equalsIgnoreCase("Registrati"))
            pkg = "com.example.flightbooking.fragments.subscribe.SubscribeFragment";
        return pkg;
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
