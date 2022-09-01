package com.example.flightbooking.fragments.home;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.flightbooking.exception.MissingValuesException;

import java.util.HashMap;

public class HomeFragmentView {

    private RadioGroup rg_flight_types;
    private Spinner sp_companies, sp_dep_country, sp_dep_airport, sp_arr_country, sp_arr_airport;
    private EditText et_out_date, et_ret_date, et_adults, et_teenagers, et_childrens, et_newborns;
    private Button bt_search;

    private static String[] itemsName = {
            "flight_types","companies","dep_country","dep_airport","arr_country","arr_airport","out_date","ret_date",
            "adults","teenagers","childrens","newborns","search"
    };

    public HomeFragmentView(HashMap<String, View> items) throws MissingValuesException {
        this.assignItems(items);
    }

    /**
     * Assign values from hashmap to properties
     * @param items
     */
    private void assignItems(HashMap<String, View> items) throws MissingValuesException {
        //Check if all views are prensent in hashmap
        for(String item: HomeFragmentView.itemsName){
            if(!items.containsKey(item)){
                throw new MissingValuesException("Impossibile trovare uno o più dati richiesti");
            }
        }
        this.rg_flight_types = (RadioGroup) items.get("flight_types");
        this.sp_companies = (Spinner) items.get("companies");
        this.sp_dep_country = (Spinner) items.get("dep_country");
        this.sp_dep_airport = (Spinner) items.get("dep_airport");
        this.sp_arr_country = (Spinner) items.get("arr_country");
        this.sp_arr_airport = (Spinner) items.get("arr_airport");
        this.et_out_date = (EditText) items.get("out_date");
        this.et_ret_date = (EditText) items.get("ret_date");
        this.et_adults = (EditText) items.get("adults");
        this.et_teenagers = (EditText) items.get("teenagers");
        this.et_childrens = (EditText) items.get("childrens");
        this.et_newborns = (EditText) items.get("newborns");
        this.bt_search = (Button) items.get("search");

    }
}
