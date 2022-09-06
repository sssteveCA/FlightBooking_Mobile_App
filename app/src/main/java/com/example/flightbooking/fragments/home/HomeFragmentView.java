package com.example.flightbooking.fragments.home;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.exception.MissingValuesException;
import com.google.android.material.chip.ChipGroup;

import java.util.HashMap;
import java.util.Map;

public class HomeFragmentView {

    private ChipGroup cg_type;
    private MainActivity ma;

    public HomeFragmentView(MainActivity ma, ChipGroup cg_type) {
        this.ma = ma;
        this.cg_type = cg_type;
    }

    public ChipGroup getCgType(){return this.cg_type;}

    /**
     * Add or replace the fragment in Home Fragment container view
     * @param container
     * @param fragment
     */
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

    /**
     * Remove the current fragment inside the fragment container in HomeFragment
     * @param container
     */
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

}
