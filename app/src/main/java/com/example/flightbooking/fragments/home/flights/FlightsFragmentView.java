package com.example.flightbooking.fragments.home.flights;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.flightbooking.exception.MissingValuesException;

import java.util.Map;

public class FlightsFragmentView {

    private RadioGroup rg_flight_types;
    private Spinner sp_companies, sp_dep_country, sp_dep_airport, sp_arr_country, sp_arr_airport;
    private TextView tv_ret_date;
    private EditText et_out_date, et_ret_date, et_adults, et_teenagers, et_childrens, et_newborns;
    private Button bt_search;

    private static String[] itemsName = {
            "flight_types","companies","dep_country","dep_airport","arr_country","arr_airport","out_date","ret_date_tv","ret_date_et",
            "adults","teenagers","childrens","newborns","search"
    };

    public FlightsFragmentView(Map<String, View> items) throws MissingValuesException {
        this.assignItems(items);
    }

    public RadioGroup getRgFlightTypes(){return this.rg_flight_types;}
    public Spinner getSpCompanies(){return this.sp_companies;}
    public Spinner getSpDepCountry(){return this.sp_dep_country;}
    public Spinner getSpDepAirport(){return this.sp_dep_airport;}
    public Spinner getSpArrCountry(){return this.sp_arr_country;}
    public Spinner getSpArrAirport(){return this.sp_arr_airport;}
    public EditText getEtOutDate(){return this.et_out_date;}
    public TextView getTvRetDate(){return this.tv_ret_date;}
    public EditText getEtRetDate(){return this.et_ret_date;}
    public EditText getEtAdults(){return this.et_adults;}
    public EditText getEtTeenagers(){return this.et_teenagers;}
    public EditText getEtChildrens(){return this.et_childrens;}
    public EditText getEtNewborns(){return this.et_newborns;}
    public Button getBtSearch(){return this.bt_search;}

    /**
     * Assign values from hashmap to properties
     * @param items
     */
    private void assignItems(Map<String, View> items) throws MissingValuesException {
        //Check if all views are prensent in hashmap
        for(String item: FlightsFragmentView.itemsName){
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
        this.tv_ret_date = (TextView) items.get("ret_date_tv");
        this.et_ret_date = (EditText) items.get("ret_date_et");
        this.et_adults = (EditText) items.get("adults");
        this.et_teenagers = (EditText) items.get("teenagers");
        this.et_childrens = (EditText) items.get("childrens");
        this.et_newborns = (EditText) items.get("newborns");
        this.bt_search = (Button) items.get("search");

    }

    /**
     * Show the proper views for the selected flight type
     * @param flight_type
     */
    public void setFlightTypeViews(int flight_type){
        if(flight_type == FlightsFragmentModel.FLIGHTTYPE_ROUNDTRIP){
            this.tv_ret_date.setVisibility(View.VISIBLE);
            this.et_ret_date.setVisibility(View.VISIBLE);
        }
        else{
            this.tv_ret_date.setVisibility(View.GONE);
            this.et_ret_date.setVisibility(View.GONE);
        }
    }
}
