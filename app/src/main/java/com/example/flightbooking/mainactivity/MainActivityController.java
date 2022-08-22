package com.example.flightbooking.mainactivity;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.flightbooking.views.connection.NoConnectionFragment;

public class MainActivityController {

    private Context ctx;
    private MainActivityModel mam;
    private MainActivityView mav;

    public MainActivityController(MainActivityModel mam, MainActivityView mav){
        this.ctx = ctx;
        this.mam = mam;
        this.mav = mav;
    }

    /***
     * Set the fragment after check the application status
     */
    public void setView(){
        boolean connected = this.mam.getConnectionStatus();
        if(connected){

        }//if(connected){
        else{
            this.mav.setFragment(new NoConnectionFragment());
        }
    }
}
