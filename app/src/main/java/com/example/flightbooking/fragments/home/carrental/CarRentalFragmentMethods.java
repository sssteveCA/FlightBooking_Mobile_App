package com.example.flightbooking.fragments.home.carrental;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.flightbooking.R;

import java.util.AbstractMap;
import java.util.Map;

public class CarRentalFragmentMethods {
    /**
     * Create a Map with CarRentalFragment view references
     * @param v
     * @return
     */
    public static Map<String, View> carRentalItems(View v){
        Map<String, View> items = Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<>("rent_company",(Spinner)v.findViewById(R.id.frag_cr_sp_rent_company)),
                new AbstractMap.SimpleImmutableEntry<>("car",(Spinner)v.findViewById(R.id.frag_cr_sp_car)),
                new AbstractMap.SimpleImmutableEntry<>("pickup_country",(Spinner)v.findViewById(R.id.frag_cr_sp_pickup_country)),
                new AbstractMap.SimpleImmutableEntry<>("pickup_location",(Spinner)v.findViewById(R.id.frag_cr_sp_pickup_location)),
                new AbstractMap.SimpleImmutableEntry<>("delivery_country",(Spinner)v.findViewById(R.id.frag_cr_sp_delivery_country)),
                new AbstractMap.SimpleImmutableEntry<>("delivery_location",(Spinner)v.findViewById(R.id.frag_cr_sp_delivery_location)),
                new AbstractMap.SimpleImmutableEntry<>("rentstart",(EditText)v.findViewById(R.id.frag_cr_et_rentstart)),
                new AbstractMap.SimpleImmutableEntry<>("rentend",(EditText)v.findViewById(R.id.frag_cr_et_rentend)),
                new AbstractMap.SimpleImmutableEntry<>("age_band",(Spinner)v.findViewById(R.id.frag_cr_sp_age_band)),
                new AbstractMap.SimpleImmutableEntry<>("go",(Button)v.findViewById(R.id.frag_cr_bt_go))
        );
        return items;
    }
}
