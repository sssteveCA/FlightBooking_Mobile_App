package com.example.flightbooking.fragments.home.hotel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.flightbooking.exception.MissingValuesException;

import java.util.HashMap;
import java.util.Map;

public class HotelFragmentView {

    private ConstraintLayout cl;
    private Spinner sp_countries,sp_cities,sp_hotels;
    private EditText et_check_in,et_check_out,et_rooms,et_people;
    private Button bt_search;
    private ProgressBar pb_search;

    private static String[] itemsName = {
            "layout","countries","cities","hotels","check_in","check_out", "rooms","people"
    };

    public HotelFragmentView(Map<String, View> items) throws MissingValuesException {
        this.assignItems(items);
    }

    public ConstraintLayout getCl(){return this.cl;}
    public Spinner getSpCountries(){return this.sp_countries;}
    public Spinner getSpCities(){return this.sp_cities;}
    public Spinner getSpHotels(){return this.sp_hotels;}
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
        this.cl = (ConstraintLayout) items.get("layout");
        this.sp_countries = (Spinner) items.get("countries");
        this.sp_cities = (Spinner) items.get("cities");
        this.sp_hotels = (Spinner) items.get("hotels");
        this.et_check_in = (EditText) items.get("check_in");
        this.et_check_out = (EditText) items.get("check_out");
        this.et_rooms = (EditText) items.get("et_rooms");
        this.et_people = (EditText) items.get("et_people");
        this.bt_search = (Button) items.get("search");
    }

    /**
     * Create the table that displays the hotel information
     * @param context
     * @param info the information values of the hotel
     * @return a TableLayout view
     */
    @SuppressLint("SetTextI18n")
    public TableLayout createHotelInfoTable(Context context, HashMap<String, Object> info){
        TableLayout tl = new TableLayout(context);
        if(info.containsKey("max_people")){
            TableRow tr = new TableRow(context);
            TextView th = new TextView(context);
            th.setText("Numero massimo di persone per stanza");
            tr.addView(th);
            TextView td = new TextView(context);
            td.setText(String.valueOf(info.get("max_people")));
            tr.addView(td);
            tl.addView(tr);
        }
        if(info.containsKey("price")){
            TableRow tr = new TableRow(context);
            TextView th = new TextView(context);
            th.setText("Prezzo per notte");
            tr.addView(th);
            TextView td = new TextView(context);
            td.setText(info.get("price")+"€");
            tr.addView(td);
            tl.addView(tr);
        }
        if(info.containsKey("rooms")){
            TableRow tr = new TableRow(context);
            TextView th = new TextView(context);
            th.setText("Stanze disponibili");
            tr.addView(th);
            TextView td = new TextView(context);
            td.setText(info.get("rooms")+" stanze");
            tr.addView(td);
            tl.addView(tr);
        }
        if(info.containsKey("score")){
            TableRow tr = new TableRow(context);
            TextView th = new TextView(context);
            th.setText("Voto medio");
            tr.addView(th);
            TextView td = new TextView(context);
            td.setText(String.valueOf(info.get("score")));
            tr.addView(td);
            tl.addView(tr);
        }
        if(info.containsKey("stars")){
            TableRow tr = new TableRow(context);
            TextView th = new TextView(context);
            th.setText("Stelle");
            tr.addView(th);
            TextView td = new TextView(context);
            td.setText(info.get("stars")+" stelle");
            tr.addView(td);
            tl.addView(tr);
        }
        return tl;
    }
}
