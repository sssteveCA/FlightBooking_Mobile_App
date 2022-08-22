package com.example.flightbooking.mainactivity;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.flightbooking.MainActivity;

public class MainActivityView {

    private Context ctx;
    private MainActivity ma;

    public MainActivityView(Context ctx, MainActivity ma){
        this.ctx = ctx;
        this.ma = ma;
    }

    public void setFragment(Fragment fragment){
        FragmentManager fm = this.ma.getSupportFragmentManager();

    }
}
