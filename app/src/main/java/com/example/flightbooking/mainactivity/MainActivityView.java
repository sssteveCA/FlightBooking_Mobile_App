package com.example.flightbooking.mainactivity;

import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;

public class MainActivityView {

    public static final int FRAGMENT_ADD = 1;
    public static final int FRAGMENT_REPLACE = 2;
    public static final int FRAGMENT_DELETE = 3;


    private MainActivity ma;
    private ProgressBar pb;

    public MainActivityView(MainActivity ma){
        this.ma = ma;

    }

    public void removeFragment(){
        FragmentManager fm = this.ma.getSupportFragmentManager();
        Fragment current_fragment = fm.findFragmentById(R.id.main_activity_fragment_container);
        if(current_fragment != null){
            //Container has already a fragment
            FragmentTransaction ft = fm.beginTransaction();
            ft.setReorderingAllowed(true);
            ft.remove(current_fragment);
            ft.commit();
        }
    }

    public void updateFragment(Fragment fragment){
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

    public void setProgressBar(int visibility){
        if(visibility == View.VISIBLE || visibility == View.INVISIBLE || visibility == View.GONE)
            this.pb.setVisibility(visibility);
    }

    public ProgressBar getProgressBar(){return this.pb;}
}
