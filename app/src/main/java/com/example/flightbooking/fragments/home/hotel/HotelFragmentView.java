package com.example.flightbooking.fragments.home.hotel;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.example.flightbooking.exception.MissingValuesException;

import java.util.Map;

public class HotelFragmentView {

    private Spinner sp_country,sp_city,sp_hotel;
    private EditText et_check_in,et_check_out,et_rooms,et_people;
    private Button bt_search;
    private ProgressBar pb_search;

    private static String[] itemsName = {
            "country","city","hotel","check_in","check_out", "rooms","people"
    };

    public HotelFragmentView(Map<String, View> items) throws MissingValuesException {
        this.assignItems(items);
    }

    public Spinner getSpCountry(){return this.sp_country;}
    public Spinner getSpCity(){return this.sp_city;}
    public Spinner getSpHotel(){return this.sp_hotel;}
    public EditText getEtCkeckIn(){return this.et_check_in;}
    public EditText getEtCkeckOut(){return this.et_check_out;}
    public EditText getEtRooms(){return this.et_rooms;}
    public EditText getEtPeople(){return this.et_people;}
    public Button getBtSearch(){return this.bt_search;}
    public ProgressBar getPbSearch(){return this.pb_search;}

    private void assignItems(Map<String, View> items) throws MissingValuesException {
        for(String item: HotelFragmentView.itemsName){
            if(!items.containsKey(item)){
                throw new MissingValuesException("Impossibile trovare uno o più dati richiesti");
            }
        }
        this.sp_country = (Spinner) items.get("country");
        this.sp_city = (Spinner) items.get("city");
        this.sp_hotel = (Spinner) items.get("hotel");
        this.et_check_in = (EditText) items.get("check_in");
        this.et_check_out = (EditText) items.get("check_out");
        this.et_rooms = (EditText) items.get("et_rooms");
        this.et_people = (EditText) items.get("et_people");
        this.bt_search = (Button) items.get("search");
    }
}
