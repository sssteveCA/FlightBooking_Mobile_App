package com.example.flightbooking.fragments.home.carrental;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.flightbooking.R;
import com.example.flightbooking.common.Generic;
import com.google.gson.JsonObject;

import java.util.AbstractMap;
import java.util.LinkedList;
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

    public static void setCarsList(Context context, CarRentalFragmentModel crfm, Spinner src, Spinner dest){
        String company = (String) src.getSelectedItem();
        LinkedList<String> cars = crfm.getCompanyCars(company);
        if(cars != null){
            ArrayAdapter<String> carsA = Generic.arrayAdapterFromList(context,cars);
            dest.setAdapter(carsA);
        }
    }

    /**
     * Set the list location spinner on country spinner item change
     * @param context
     * @param crfm
     * @param src
     * @param dest
     */
    public static void setLocationsList(Context context, CarRentalFragmentModel crfm, Spinner src, Spinner dest){
        String country = (String) src.getSelectedItem();
        LinkedList<String> locations = crfm.getCountryLocations(country);
        if(locations != null){
            ArrayAdapter<String> destA = Generic.arrayAdapterFromList(context,locations);
            dest.setAdapter(destA);
        }
    }

    /**
     * Add the items to the spinners
     * @param context
     * @param crfm
     * @param crfv
     */
    public static void setSpinnerAdapters(Context context, CarRentalFragmentModel crfm, CarRentalFragmentView crfv){
        LinkedList<String> ageRanges = crfm.getAgeRanges();
        if(ageRanges != null) {
            ArrayAdapter<String> ageRangesA = Generic.arrayAdapterFromList(context, ageRanges);
            crfv.getSpAgeBand().setAdapter(ageRangesA);
        }
        LinkedList<String> countries = crfm.getCountries();
        if(countries != null) {
            ArrayAdapter<String> countriesA = Generic.arrayAdapterFromList(context, countries);
            crfv.getSpPickupCountry().setAdapter(countriesA);
            crfv.getSpDeliveryCountry().setAdapter(countriesA);
            String firstCountry = countries.getFirst();
            if (firstCountry != null) {
                LinkedList<String> locations = crfm.getCountryLocations(firstCountry);
                ArrayAdapter<String> locationsA = Generic.arrayAdapterFromList(context, locations);
                crfv.getSpPickupLocation().setAdapter(locationsA);
                crfv.getSpDeliveryLocation().setAdapter(locationsA);
            }
        }//if(countries != null) {
        LinkedList<String> companies = crfm.getCompanies();
        if(companies != null){
            ArrayAdapter<String> companiesA = Generic.arrayAdapterFromList(context, companies);
            crfv.getSpRentCompany().setAdapter(companiesA);
            String firstCompany = companies.getFirst();
            if(firstCompany != null){
                LinkedList<String> cars = crfm.getCompanyCars(firstCompany);
                if(cars != null){
                    ArrayAdapter<String> carsA = Generic.arrayAdapterFromList(context, cars);
                    crfv.getSpCar().setAdapter(carsA);
                    String firstCar = cars.getFirst();
                    if(firstCar != null) {
                        JsonObject car = crfm.getCarDetails("Alamo","Alfa Romeo Stelvio");
                    }
                }//if(cars != null){
            }//if(firstCompany != null){
        }//if(companies != null){
    }

    /**
     * Set the listeners to the CarRentalFragment views
     * @param crf
     * @param crfv
     */
    public static void setViewListeners(CarRentalFragment crf, CarRentalFragmentView crfv){
        crfv.getSpRentCompany().setOnItemSelectedListener(crf);
        crfv.getSpCar().setOnItemSelectedListener(crf);
        crfv.getSpPickupCountry().setOnItemSelectedListener(crf);
        crfv.getSpPickupLocation().setOnItemSelectedListener(crf);
        crfv.getSpDeliveryCountry().setOnItemSelectedListener(crf);
        crfv.getSpDeliveryLocation().setOnItemSelectedListener(crf);
        crfv.getSpAgeBand().setOnItemSelectedListener(crf);
        crfv.getBtGo().setOnClickListener(crf);
    }
}
