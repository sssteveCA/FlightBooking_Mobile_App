package com.example.flightbooking.mainactivity;

import android.content.Context;

import com.example.flightbooking.common.Connection;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.enums.FragmentPackages;

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
        if(class_name.equalsIgnoreCase(FragmentPackages.HOME.getPackageName()))
            label = FragmentLabels.HOME.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.ABOUT_US.getPackageName()))
            label = FragmentLabels.ABOUT_US.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.CONTACTS.getPackageName()))
            label = FragmentLabels.CONTACTS.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.LOGIN.getPackageName()))
            label = FragmentLabels.LOGIN.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.NEWS.getPackageName()))
            label = FragmentLabels.NEWS.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.NO_CONNECTION.getPackageName()))
            label = FragmentLabels.NO_CONNECTION.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.SUBSCRIBE.getPackageName()))
            label = FragmentLabels.SUBSCRIBE.getLabelName();
        return label;
    }

    /**
     * Get the menu item fragment package clicked by the user
     * @param label
     * @return
     */
    public String getFragmentPackage(String label){
        String class_name = "";
        if(label.equalsIgnoreCase(FragmentLabels.HOME.getLabelName()))
            class_name = FragmentPackages.HOME.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.ABOUT_US.getLabelName()))
            class_name = FragmentPackages.ABOUT_US.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.CONTACTS.getLabelName()))
            class_name = FragmentPackages.CONTACTS.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.LOGIN.getLabelName()))
            class_name = FragmentPackages.LOGIN.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.NEWS.getLabelName()))
            class_name = FragmentPackages.NEWS.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.NO_CONNECTION.getLabelName()))
            class_name = FragmentPackages.NO_CONNECTION.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.SUBSCRIBE.getLabelName()))
            class_name = FragmentPackages.SUBSCRIBE.getPackageName();
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
