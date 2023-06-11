package com.example.flightbooking.fragments.home.carrental;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Map;

public class CarRentalFragmentView {

    private Spinner sp_rent_company, sp_pickup_country, sp_pickup_location, sp_delivery_country, sp_delivery_location, sp_age_band;
    private EditText et_rentstart, et_rentend;
    private Button bt_go;

    private static String[] itemsName = {
            "rent_company","pickup_country","pickup_location","delivery_country","delivery_location","rentstart","rentend","age_band"
    };

    public CarRentalFragmentView(Map<String, View> items){

    }
}
