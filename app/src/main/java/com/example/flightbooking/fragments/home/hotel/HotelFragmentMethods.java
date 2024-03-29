package com.example.flightbooking.fragments.home.hotel;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.common.Generic;
import com.example.flightbooking.dialogs.DatePickerHotel;
import com.example.flightbooking.models.requests.hotel.HotelSearch;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HotelFragmentMethods {

    /**
     * Create a Map with HotelFragment view references
     * @param v
     * @return
     */
    public static Map<String, View> hotelItems(View v){
        Map<String, View> items = Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<>("countries", (Spinner)v.findViewById(R.id.frag_hotel_sp_countries)),
                new AbstractMap.SimpleImmutableEntry<>("cities", (Spinner)v.findViewById(R.id.frag_hotel_sp_cities)),
                new AbstractMap.SimpleImmutableEntry<>("hotels", (Spinner)v.findViewById(R.id.frag_hotel_sp_hotels)),
                new AbstractMap.SimpleImmutableEntry<>("check_in", (EditText)v.findViewById(R.id.frag_hotel_et_check_in)),
                new AbstractMap.SimpleImmutableEntry<>("check_out", (EditText)v.findViewById(R.id.frag_hotel_et_check_out)),
                new AbstractMap.SimpleImmutableEntry<>("rooms", (EditText)v.findViewById(R.id.frag_hotel_et_rooms)),
                new AbstractMap.SimpleImmutableEntry<>("people", (EditText)v.findViewById(R.id.frag_hotel_et_people)),
                new AbstractMap.SimpleImmutableEntry<>("ll_table",(LinearLayout)v.findViewById(R.id.frag_hotel_ll_table)),
                new AbstractMap.SimpleImmutableEntry<>("bt_search", (Button)v.findViewById(R.id.frag_hotel_bt_search)),
                new AbstractMap.SimpleImmutableEntry<>("pb_search", (ProgressBar)v.findViewById(R.id.frag_hotel_pb_search)),
                new AbstractMap.SimpleImmutableEntry<>("bt_images", (Button)v.findViewById(R.id.frag_hotel_bt_hotel_images)),
                new AbstractMap.SimpleImmutableEntry<>("pb_images", (ProgressBar)v.findViewById(R.id.frag_hotel_pb_hotel_images))
        );
        return items;
    }

    /**
     * Reassign the saved values to the HotelFragment views
     * @param hfv
     * @param savedInstanceState
     */
    public static void reassignViewValues(HotelFragmentView hfv, Bundle savedInstanceState){
        hfv.getSpCountries().setSelection(savedInstanceState.getInt("sp_coutries"));
        hfv.getSpCities().setSelection(savedInstanceState.getInt("sp_cities"));
        hfv.getSpHotels().setSelection(savedInstanceState.getInt("sp_hotels"));
        hfv.getEtCkeckIn().setSelection(savedInstanceState.getInt("et_check_in"));
        hfv.getEtCkeckOut().setSelection(savedInstanceState.getInt("et_check_out"));
        hfv.getEtRooms().setSelection(savedInstanceState.getInt("et_rooms"));
        hfv.getEtPeople().setSelection(savedInstanceState.getInt("et_people"));
    }

    /**
     * Set a list of city in the proper dropdown widget
     * @param context
     * @param country the country of the cities to display
     * @param hfm
     * @param hfv
     */
    public static void setCitiesList(Context context, String country, HotelFragmentModel hfm, HotelFragmentView hfv){
        LinkedList<String> cities = hfm.getHotelCitiesOfCountry(country);
        ArrayAdapter<String> citiesAdapter = Generic.arrayAdapterFromList(context,cities);
        hfv.getSpCities().setAdapter(citiesAdapter);
        String firstCity = cities.getFirst();
        HotelFragmentMethods.setHotelsList(context,country,firstCity,hfm,hfv);
    }

    /**
     * Set the table in the layout that display the information about a hotel
     * @param context
     * @param country
     * @param city
     * @param hotel
     * @param hfm
     * @param hfv
     */
    public static void setHotelInfoTable(Context context,String country, String city, String hotel,HotelFragmentModel hfm,HotelFragmentView hfv){
        if(hfv.getTlInfo() != null){
            hfv.getLlTable().removeView(hfv.getTlInfo());
            hfv.setTlInfo(null);
        }//if(hfv.getTlInfo() != null){
        HashMap<String, Object> info = hfm.getHotelInfo(country,city,hotel);
        hfv.createHotelInfoTable(context,info);
        hfv.getLlTable().addView(hfv.getTlInfo());
        //this.setHotelInfoImagesButton(cs);
    }

    /**
     * Set a list of hotels in the proper dropdown widget
     * @param context
     * @param country the country to search the hotels
     * @param city the city of the hotels to display
     * @param hfm
     * @param hfv
     */
    public static void setHotelsList(Context context, String country, String city,HotelFragmentModel hfm, HotelFragmentView hfv ){
        LinkedList<String> hotels = hfm.getHotelsOfCity(country,city);
        ArrayAdapter hotelsAdapter = Generic.arrayAdapterFromList(context,hotels);
        hfv.getSpHotels().setAdapter(hotelsAdapter);
        String firstHotel = hotels.getFirst();
        HotelFragmentMethods.setHotelInfoTable(context,country,city,firstHotel,hfm,hfv);
    }

    /**
     * Set the request body to execute the hotel info preview request
     * @param hfv the class that cointains the views references
     * @return a class that represent the payload
     */
    public static HotelSearch setHotelSearchBody(HotelFragmentView hfv){
        HotelSearch hs = new HotelSearch();
        hs.country = (String) hfv.getSpCountries().getSelectedItem();
        hs.city = (String) hfv.getSpCities().getSelectedItem();
        hs.hotel = (String) hfv.getSpHotels().getSelectedItem();
        hs.checkin = hfv.getEtCkeckIn().getText().toString();
        hs.checkout = hfv.getEtCkeckOut().getText().toString();
        hs.rooms = Integer.valueOf(hfv.getEtRooms().getText().toString());
        hs.people = Integer.valueOf(hfv.getEtPeople().getText().toString());
        return hs;
    }

    /**
     * Set the listeners to HotelFragment views
     * @param hfv
     * @param hf
     */
    public static void setListeners(HotelFragmentView hfv, HotelFragment hf){
        hfv.getSpCountries().setOnItemSelectedListener(hf);
        hfv.getSpCities().setOnItemSelectedListener(hf);
        hfv.getSpHotels().setOnItemSelectedListener(hf);
        hfv.getEtCkeckIn().setOnClickListener(hf);
        hfv.getEtCkeckOut().setOnClickListener(hf);
        hfv.getBtSearch().setOnClickListener(hf);
        hfv.getBtShowImages().setOnClickListener(hf);
    }

    /**
     * Add the view values to the bundle to keep them
     * @param outState
     */
    public static void setSaveStateBundle(@NonNull Bundle outState, HotelFragmentView hfv){
        outState.putInt("sp_countries",hfv.getSpCountries().getSelectedItemPosition());
        outState.putInt("sp_cities",hfv.getSpCities().getSelectedItemPosition());
        outState.putInt("sp_hotels",hfv.getSpHotels().getSelectedItemPosition());
        outState.putString("et_check_in",hfv.getEtCkeckIn().getText().toString());
        outState.putString("et_check_out",hfv.getEtCkeckOut().getText().toString());
        outState.putString("et_rooms",hfv.getEtRooms().getText().toString());
        outState.putString("et_people",hfv.getEtPeople().getText().toString());
    }

    /**
     * Show a date picker dialog and pass the date inserted from EditText if exists
     * @param inputDate
     * @param ddh
     * @param etd
     * @param ma
     */
    public static void showDatePickerDialog(String inputDate, DatePickerHotel.DialogDateHotel ddh, HotelFragmentModel.EditTextsDate etd, MainActivity ma){
        DialogFragment df = new DatePickerHotel(inputDate,ddh,etd);
        df.show(ma.getSupportFragmentManager(),"DatePickerDialog");
    }
}
