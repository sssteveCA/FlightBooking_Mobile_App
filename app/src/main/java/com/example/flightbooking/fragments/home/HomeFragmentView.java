package com.example.flightbooking.fragments.home;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import java.util.HashMap;

public class HomeFragmentView {

    private RadioGroup rg_flight_types;
    private Spinner sp_companies, sp_dep_country, sp_dep_airport, sp_ar_country, sp_arr_airport;
    private EditText et_out_date, et_ret_date, et_adults, et_teenagers, et_childrens, et_newborns;
    private Button bt_search;

    public HomeFragmentView(HashMap<String, View> items){
        this.assignItems(items);
    }

    /**
     * Assign values from hashmap to properties
     * @param items
     */
    private void assignItems(HashMap<String, View> items){

    }
}
