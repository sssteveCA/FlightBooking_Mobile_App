package com.example.flightbooking.fragments.home.flights;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.flightbooking.R;
import com.example.flightbooking.common.Generic;
import com.example.flightbooking.models.requests.flights.FlightSearch;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FlightsFragmentMethods {

    /**
     * Create a Map with Flights Fragment views reference
     * @param v
     * @return
     */
    public static Map<String, View> menuItemsMap(View v){
        Map<String, View> flightsItems = Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<>("flight_types", (RadioGroup)v.findViewById(R.id.frag_flights_rg_flight_types)),
                new AbstractMap.SimpleImmutableEntry<>("companies",(Spinner)v.findViewById(R.id.frag_flights_sp_companies)),
                new AbstractMap.SimpleImmutableEntry<>("dep_country", (Spinner)v.findViewById(R.id.frag_flights_sp_dep_country)),
                new AbstractMap.SimpleImmutableEntry<>("dep_airport", (Spinner)v.findViewById(R.id.frag_flights_sp_dep_airport)),
                new AbstractMap.SimpleImmutableEntry<>("arr_country", (Spinner)v.findViewById(R.id.frag_flights_sp_arr_country)),
                new AbstractMap.SimpleImmutableEntry<>("arr_airport", (Spinner)v.findViewById(R.id.frag_flights_sp_arr_airport)),
                new AbstractMap.SimpleImmutableEntry<>("out_date", (EditText) v.findViewById(R.id.frag_flights_et_out_date)),
                new AbstractMap.SimpleImmutableEntry<>("ret_date_tv", (TextView)v.findViewById(R.id.frag_flights_tv_ret_date)),
                new AbstractMap.SimpleImmutableEntry<>("ret_date_et", (EditText)v.findViewById(R.id.frag_flights_et_ret_date)),
                new AbstractMap.SimpleImmutableEntry<>("adults", (EditText)v.findViewById(R.id.frag_flights_et_adults)),
                new AbstractMap.SimpleImmutableEntry<>("teenagers", (EditText)v.findViewById(R.id.frag_flights_et_teenagers)),
                new AbstractMap.SimpleImmutableEntry<>("childrens", (EditText)v.findViewById(R.id.frag_flights_et_childrens)),
                new AbstractMap.SimpleImmutableEntry<>("newborns", (EditText)v.findViewById(R.id.frag_flights_et_newborns)),
                new AbstractMap.SimpleImmutableEntry<>("search", (Button)v.findViewById(R.id.frag_flights_bt_search)),
                new AbstractMap.SimpleImmutableEntry<>("progress_bar", (ProgressBar)v.findViewById(R.id.frag_flights_pb_search))
        );
        return flightsItems;
    }

    /**
     * Avoid losing FlightsFragment view data
     * @param ffv
     * @param savedInstanceState
     */
    public static void saveViewsValues(FlightsFragmentView ffv, Bundle savedInstanceState){
        ffv.getEtOutDate().setText(savedInstanceState.getString("out_date"));
        ffv.getEtRetDate().setText(savedInstanceState.getString("ret_date"));
        ffv.getEtAdults().setText(savedInstanceState.getString("et_adults"));
        ffv.getEtTeenagers().setText(savedInstanceState.getString("et_teenagers"));
        ffv.getEtChildrens().setText(savedInstanceState.getString("out_date"));
        ffv.getEtNewborns().setText(savedInstanceState.getString("out_date"));
    }

    /**
     * Set a list of bookable airports
     * @param country the country of the airports to display
     * @param ar the option to choose the dropdown to fill
     */
    public static void setAirportsList(Context context, String country, FlightsFragmentModel.AirportsRequest ar, FlightsFragmentModel ffm, FlightsFragmentView ffv){
        LinkedList<String> airports = ffm.getAirportsOfCountry(country);
        ArrayAdapter<String> airportsAdapter = Generic.arrayAdapterFromList(context, airports);
        if(ar == FlightsFragmentModel.AirportsRequest.DEPARTURE){
            ffv.getSpDepAirport().setAdapter(airportsAdapter);
        }//if(ar == FlightsFragmentModel.AirportsRequest.DEPARTURE){
        else{
            ffv.getSpArrAirport().setAdapter(airportsAdapter);
        }
    }

    /**
     * Se the body for flightsearch POST request
     * @return
     */
    public static FlightSearch setFlightSearchBody(FlightsFragmentView ffv){
        FlightSearch fs = new FlightSearch();
        if(ffv.getRgFlightTypes().getCheckedRadioButtonId() == R.id.frag_flights_rb_oneway)
            fs.flight_type = "oneway";
        else
            fs.flight_type = "roundtrip";
        fs.company_name = ffv.getSpCompanies().getSelectedItem().toString();
        fs.from_country = ffv.getSpDepCountry().getSelectedItem().toString();
        fs.from_airport = ffv.getSpDepAirport().getSelectedItem().toString();
        fs.to_country = ffv.getSpArrCountry().getSelectedItem().toString();
        fs.to_airport = ffv.getSpArrAirport().getSelectedItem().toString();
        if(fs.flight_type == "oneway") {
            fs.oneway_date = ffv.getEtOutDate().getText().toString();
        }
        else{
            fs.roundtrip_start_date = ffv.getEtOutDate().getText().toString();
            fs.roundtrip_end_date = ffv.getEtRetDate().getText().toString();
        }
        fs.adults = Integer.valueOf(ffv.getEtAdults().getText().toString());
        fs.teenagers = Integer.valueOf(ffv.getEtTeenagers().getText().toString());
        fs.children = Integer.valueOf(ffv.getEtChildrens().getText().toString());
        fs.newborns = Integer.valueOf(ffv.getEtNewborns().getText().toString());
        return fs;
    }

    /**
     * Set the listeners to FlightFragment views
     * @param ffv
     */
    public static void setListeners(FlightsFragmentView ffv, FlightsFragment ff){
        ffv.getRgFlightTypes().setOnCheckedChangeListener(ff);
        ffv.getSpDepCountry().setOnItemSelectedListener(ff);
        ffv.getSpArrCountry().setOnItemSelectedListener(ff);
        ffv.getEtOutDate().setOnClickListener(ff);
        ffv.getEtRetDate().setOnClickListener(ff);
        ffv.getBtSearch().setOnClickListener(ff);
    }
}
