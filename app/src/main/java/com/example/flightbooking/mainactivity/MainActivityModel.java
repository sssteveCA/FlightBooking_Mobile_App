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
     * Get fragment label from full class name
     * @param class_name full class name of the fragment
     * @return
     */
    public String getFragmentLabel(String class_name){
        String label = "";
        if(class_name.equalsIgnoreCase("com.example.flightbooking.fragments.home.HomeFragment"))
            label = "Home";
        else if(class_name.equalsIgnoreCase("com.example.flightbooking.fragments.aboutus.AboutUsFragment"))
            label = "Chi siamo";
        else if(class_name.equalsIgnoreCase("com.example.flightbooking.fragments.contacts.ContactsFragment"))
            label = "Contatti";
        else if(class_name.equalsIgnoreCase("com.example.flightbooking.fragments.login.LoginFragment"))
            label = "Login";
        else if(class_name.equalsIgnoreCase("com.example.flightbooking.fragments.news.NewsFragment"))
            label = "News";
        else if(class_name.equalsIgnoreCase("com.example.flightbooking.fragments.noconnection.NoConnectionFragment"))
            label = "NoConnection";
        else if(class_name.equalsIgnoreCase("com.example.flightbooking.fragments.subscribe.SubscribeFragment"))
            label = "Registrati";
        return label;
    }

    /**
     * Get the menu item fragment package clicked by the user
     * @param label
     * @return
     */
    public String getFragmentPackage(String label){
        String class_name = "";
        if(label.equalsIgnoreCase("Home"))
            class_name = "com.example.flightbooking.fragments.home.HomeFragment";
        else if(label.equalsIgnoreCase("Chi siamo"))
            class_name = "com.example.flightbooking.fragments.aboutus.AboutUsFragment";
        else if(label.equalsIgnoreCase("Contatti"))
            class_name = "com.example.flightbooking.fragments.contacts.ContactsFragment";
        else if(label.equalsIgnoreCase("Login"))
            class_name = "com.example.flightbooking.fragments.login.LoginFragment";
        else if(label.equalsIgnoreCase("News"))
            class_name = "com.example.flightbooking.fragments.news.NewsFragment";
        else if(label.equalsIgnoreCase("NoConnection"))
            class_name = "com.example.flightbooking.fragments.noconnection.NoConnectionFragment";
        else if(label.equalsIgnoreCase("Registrati"))
            class_name = "com.example.flightbooking.fragments.subscribe.SubscribeFragment";
        return class_name;
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
