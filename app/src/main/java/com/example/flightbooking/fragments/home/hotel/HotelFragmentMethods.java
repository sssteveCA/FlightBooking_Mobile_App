package com.example.flightbooking.fragments.home.hotel;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.DialogFragment;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.common.Generic;
import com.example.flightbooking.dialogs.DatePickerHotel;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class HotelFragmentMethods {

    /**
     * Create a Map with Hotel view references
     * @param v
     * @return
     */
    public static Map<String, View> hotelItems(View v){
        Map<String, View> items = Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<>("layout", (ConstraintLayout)v.findViewById(R.id.frag_hotel_cl)),
                new AbstractMap.SimpleImmutableEntry<>("countries", (Spinner)v.findViewById(R.id.frag_hotel_sp_countries)),
                new AbstractMap.SimpleImmutableEntry<>("cities", (Spinner)v.findViewById(R.id.frag_hotel_sp_cities)),
                new AbstractMap.SimpleImmutableEntry<>("hotels", (Spinner)v.findViewById(R.id.frag_hotel_sp_hotels)),
                new AbstractMap.SimpleImmutableEntry<>("check_in", (EditText)v.findViewById(R.id.frag_hotel_et_check_in)),
                new AbstractMap.SimpleImmutableEntry<>("check_out", (EditText)v.findViewById(R.id.frag_hotel_et_check_out)),
                new AbstractMap.SimpleImmutableEntry<>("rooms", (EditText)v.findViewById(R.id.frag_hotel_et_rooms)),
                new AbstractMap.SimpleImmutableEntry<>("people", (EditText)v.findViewById(R.id.frag_hotel_et_people)),
                new AbstractMap.SimpleImmutableEntry<>("search", (Button)v.findViewById(R.id.frag_hotel_bt_search)),
                new AbstractMap.SimpleImmutableEntry<>("images", (Button)v.findViewById(R.id.frag_hotel_bt_hotel_images)),
                new AbstractMap.SimpleImmutableEntry<>("pb_images", (ProgressBar)v.findViewById(R.id.frag_hotel_pb_hotel_images))
        );
        return items;
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
            hfv.getCl().removeView(hfv.getTlInfo());
            hfv.setTlInfo(null);
        }//if(hfv.getTlInfo() != null){
        HashMap<String, Object> info = hfm.getHotelInfo(country,city,hotel);
        hfv.createHotelInfoTable(context,info);
        ConstraintSet cs = new ConstraintSet();
        cs.clone(hfv.getCl());
        hfv.getCl().addView(hfv.getTlInfo());
        cs.connect(hfv.getTlInfo().getId(), ConstraintSet.TOP, hfv.getBtSearch().getId(), ConstraintSet.BOTTOM, 40);
        cs.connect(hfv.getTlInfo().getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID,ConstraintSet.LEFT,20);
        cs.connect(hfv.getTlInfo().getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 20);
        cs.constrainHeight(hfv.getTlInfo().getId(), ConstraintSet.WRAP_CONTENT);
        cs.applyTo(hfv.getCl());
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
