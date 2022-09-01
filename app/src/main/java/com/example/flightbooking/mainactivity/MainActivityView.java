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

    public MainActivityView(MainActivity ma, ProgressBar pb){
        this.ma = ma;
        this.pb = pb;

    }

    public ProgressBar getPb(){return this.pb;}
    public void setPb(ProgressBar pb){this.pb = pb;}

    public void removeFragment(int container){
        FragmentManager fm = this.ma.getSupportFragmentManager();
        Fragment current_fragment = fm.findFragmentById(container);
        if(current_fragment != null){
            //Container has already a fragment
            FragmentTransaction ft = fm.beginTransaction();
            ft.setReorderingAllowed(true);
            ft.remove(current_fragment);
            ft.commit();
        }
    }

    public void updateFragment(int container, Fragment fragment){
        FragmentManager fm = this.ma.getSupportFragmentManager();
        Fragment current_fragment = fm.findFragmentById(container);
        FragmentTransaction ft = fm.beginTransaction();
        ft.setReorderingAllowed(true);
        if(current_fragment != null){
            //Container has already a fragment
            ft.replace(container,fragment,null);
        }//if(current_fragment != null){
        else{
            ft.add(container,fragment,null);
        }
        ft.commit();
    }

}
