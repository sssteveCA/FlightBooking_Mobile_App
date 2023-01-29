package com.example.flightbooking;

import android.content.Context;
import android.util.Log;

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

    public void setUserLogged(boolean userLogged){ this.userLogged = userLogged; }

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
        if(class_name.equalsIgnoreCase(FragmentPackages.HOME.getPackageName()))
            return FragmentLabels.HOME.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.ABOUT_US.getPackageName()))
            return FragmentLabels.ABOUT_US.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.CONTACTS.getPackageName()))
            return FragmentLabels.CONTACTS.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.COOKIE_POLICY.getPackageName()))
            return FragmentLabels.COOKIE_POLICY.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.FLIGHTS.getPackageName()))
            return FragmentLabels.FLIGHTS.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.HOME.getPackageName()))
            return FragmentLabels.HOME.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.HOTELINFO_PREVIEW.getPackageName()))
            return FragmentLabels.HOTELINFO_PREVIEW.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.HOTELS.getPackageName()))
            return FragmentLabels.HOTELS.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.LOGIN.getPackageName()))
            return FragmentLabels.LOGIN.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.NEWS.getPackageName()))
            return FragmentLabels.NEWS.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.NO_CONNECTION.getPackageName()))
            return FragmentLabels.NO_CONNECTION.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.POST.getPackageName()))
            return FragmentLabels.POST.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.PRIVACY_POLICY.getPackageName()))
            return FragmentLabels.PRIVACY_POLICY.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.SUBSCRIBE.getPackageName()))
            return FragmentLabels.SUBSCRIBE.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.TERMS.getPackageName()))
            return FragmentLabels.TERMS.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.TICKET_PREVIEW.getPackageName()))
            return FragmentLabels.TICKET_PREVIEW.getLabelName();
        else if(class_name.equalsIgnoreCase(FragmentPackages.VERIFY.getPackageName()))
            return FragmentLabels.VERIFY.getLabelName();
        return "";
    }

    /**
     * Get the menu item fragment package clicked by the user
     * @param label
     * @return
     */
    public String getFragmentPackage(String label){
        if(label.equalsIgnoreCase(FragmentLabels.HOME.getLabelName()))
            return FragmentPackages.HOME.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.ABOUT_US.getLabelName()))
            return FragmentPackages.ABOUT_US.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.CONTACTS.getLabelName()))
            return FragmentPackages.CONTACTS.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.COOKIE_POLICY.getLabelName()))
            return FragmentPackages.COOKIE_POLICY.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.FLIGHTS.getLabelName()))
            return FragmentPackages.FLIGHTS.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.HOME.getLabelName()))
            return FragmentPackages.HOME.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.HOTELINFO_PREVIEW.getLabelName()))
            return FragmentPackages.HOTELINFO_PREVIEW.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.HOTELS.getLabelName()))
            return FragmentPackages.HOTELS.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.LOGIN.getLabelName()))
            return FragmentPackages.LOGIN.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.NEWS.getLabelName()))
            return FragmentPackages.NEWS.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.NO_CONNECTION.getLabelName()))
            return FragmentPackages.NO_CONNECTION.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.POST.getLabelName()))
            return FragmentPackages.POST.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.PRIVACY_POLICY.getLabelName()))
            return FragmentPackages.PRIVACY_POLICY.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.SUBSCRIBE.getLabelName()))
            return FragmentPackages.SUBSCRIBE.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.TERMS.getLabelName()))
            return FragmentPackages.TERMS.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.TICKET_PREVIEW.getLabelName()))
            return FragmentPackages.TICKET_PREVIEW.getPackageName();
        else if(label.equalsIgnoreCase(FragmentLabels.VERIFY.getLabelName()))
            return FragmentPackages.VERIFY.getPackageName();
        return "";
    }

    /**
     * Check if particular menu item by label require internet connection
     */
    public boolean isConnectionRequired(String label){
        if(label != null){
            List<String> internetItems = Arrays.asList(
                    FragmentLabels.FLIGHTS.getLabelName(),
                    FragmentLabels.HOME.getLabelName(),FragmentLabels.NEWS.getLabelName(),FragmentLabels.TICKET_PREVIEW.getLabelName(), FragmentLabels.VERIFY.getLabelName()); //Label items that require internet
            //Log.d("MainActivityModel","isConnectionRequired label => "+label);
            //Log.d("MainActivityModel","isConnectionRequired internetItems => "+internetItems);
            String lcLabel = label.substring(0,1).toUpperCase()+label.substring(1);
            return internetItems.contains(lcLabel);
        }//if(label != null){
        else
            return true;
    }


}
