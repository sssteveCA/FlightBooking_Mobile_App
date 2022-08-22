package com.example.flightbooking.mainactivity;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;

public class MainActivityView {

    private MainActivity ma;

    public MainActivityView(MainActivity ma){
        this.ma = ma;
    }

    public void setFragment(Fragment fragment){
        FragmentManager fm = this.ma.getSupportFragmentManager();
        Fragment current_fragment = fm.findFragmentById(R.id.main_activity_fragment_container);
        FragmentTransaction ft = fm.beginTransaction();
        ft.setReorderingAllowed(true);
        if(current_fragment != null){
            //Container has already a fragment
            ft.add(R.id.main_activity_fragment_container,fragment,null);
        }//if(current_fragment != null){
        else{
            ft.replace(R.id.main_activity_fragment_container,fragment,null);
        }
        ft.commit();
    }
}
