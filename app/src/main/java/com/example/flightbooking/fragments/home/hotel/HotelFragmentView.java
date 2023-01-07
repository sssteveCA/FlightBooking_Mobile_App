package com.example.flightbooking.fragments.home.hotel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

    private Spinner sp_countries,sp_cities,sp_hotels;
    private EditText et_check_in,et_check_out,et_rooms,et_people;
    private Button bt_search;
    private ProgressBar pb_search;
    private LinearLayout ll_table;
    private TableLayout tl_info = null;
    private Button bt_show_images;
    private ProgressBar pb_show_images;

    private static String[] itemsName = {
            "countries","cities","hotels","check_in","check_out",
            "rooms","people","ll_table","search","pb_search","images","pb_images"
    };

    public HotelFragmentView(Map<String, View> items) throws MissingValuesException {
        this.assignItems(items);
    }

    public Spinner getSpCountries(){return this.sp_countries;}
    public Spinner getSpCities(){return this.sp_cities;}
    public Spinner getSpHotels(){return this.sp_hotels;}
    public EditText getEtCkeckIn(){return this.et_check_in;}
    public EditText getEtCkeckOut(){return this.et_check_out;}
    public EditText getEtRooms(){return this.et_rooms;}
    public EditText getEtPeople(){return this.et_people;}
    public Button getBtSearch(){return this.bt_search;}
    public ProgressBar getPbSearch(){return this.pb_search;}
    public LinearLayout getLlTable(){return this.ll_table;}
    public TableLayout getTlInfo(){return this.tl_info;}
    public Button getBtShowImages(){ return this.bt_show_images; }
    public ProgressBar getPbShowImages(){ return this.pb_show_images; }

    public void setTlInfo(TableLayout tl_info){ this.tl_info = tl_info; }

    private void assignItems(Map<String, View> items) throws MissingValuesException {
        for(String item: HotelFragmentView.itemsName){
            if(!items.containsKey(item)){
                throw new MissingValuesException("Impossibile trovare uno o più dati richiesti");
            }
        }
        this.sp_countries = (Spinner) items.get("countries");
        this.sp_cities = (Spinner) items.get("cities");
        this.sp_hotels = (Spinner) items.get("hotels");
        this.et_check_in = (EditText) items.get("check_in");
        this.et_check_out = (EditText) items.get("check_out");
        this.et_rooms = (EditText) items.get("et_rooms");
        this.et_people = (EditText) items.get("et_people");
        this.ll_table = (LinearLayout) items.get("ll_table");
        this.bt_search = (Button) items.get("search");
        this.pb_search = (ProgressBar) items.get("pb_search");
        this.bt_show_images = (Button) items.get("images");
        this.pb_show_images = (ProgressBar) items.get("pb_images");
    }

    /**
     * Create the table that displays the hotel information
     * @param context
     * @param info the information values of the hotel
     * @return a TableLayout view
     */
    @SuppressLint("SetTextI18n")
    public void createHotelInfoTable(Context context, HashMap<String, Object> info){
        this.tl_info = new TableLayout(context);
        //this.tl_info.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        this.tl_info.setStretchAllColumns(true);
        this.tl_info.setShrinkAllColumns(true);
        this.tl_info.setWeightSum(4);
        TableRow tr; TextView th,td;
        tl_info.setId(View.generateViewId());
        if(info.containsKey("max_people")){
            tr = new TableRow(context);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            th = new TextView(context);
            th.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,3));
            th.setText("Numero massimo di persone per stanza");
            th.setTypeface(null, Typeface.BOLD);
            th.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            tr.addView(th);
            td = new TextView(context);
            td.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1));
            td.setPadding(10,0,0,0);
            td.setText(String.valueOf(info.get("max_people")));
            td.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            tr.setPadding(0,10,0,10);
            tr.addView(td);
            this.tl_info.addView(tr);
        }
        if(info.containsKey("price")){
            tr = new TableRow(context);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            th = new TextView(context);
            th.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,3));
            th.setText("Prezzo per notte");
            th.setTypeface(null, Typeface.BOLD);
            th.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            tr.addView(th);
            td = new TextView(context);
            td.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1));
            td.setPadding(10,0,0,0);
            td.setText(info.get("price")+"€");
            td.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            tr.setPadding(0,10,0,10);
            tr.addView(td);
            this.tl_info.addView(tr);
        }
        if(info.containsKey("rooms")){
            tr = new TableRow(context);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            th = new TextView(context);
            th.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,3));
            th.setText("Stanze disponibili");
            th.setTypeface(null, Typeface.BOLD);
            th.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            tr.addView(th);
            td = new TextView(context);
            td.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1));
            td.setPadding(10,0,0,0);
            td.setText(info.get("rooms")+" stanze");
            td.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            tr.setPadding(0,10,0,10);
            tr.addView(td);
            this.tl_info.addView(tr);
        }
        if(info.containsKey("score")){
            tr = new TableRow(context);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            th = new TextView(context);
            th.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,3));
            th.setText("Voto medio");
            th.setTypeface(null, Typeface.BOLD);
            th.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            tr.addView(th);
            td = new TextView(context);
            td.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1));
            td.setPadding(10,0,0,0);
            td.setText(String.valueOf(info.get("score")));
            td.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            tr.setPadding(0,10,0,10);
            tr.addView(td);
            this.tl_info.addView(tr);
        }
        if(info.containsKey("stars")){
            tr = new TableRow(context);
            tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT));
            th = new TextView(context);
            th.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,3));
            th.setText("Stelle");
            th.setTypeface(null, Typeface.BOLD);
            th.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            tr.addView(th);
            td = new TextView(context);
            td.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT,1));
            td.setPadding(10,0,0,0);
            td.setText(info.get("stars")+" stelle");
            td.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            tr.setPadding(0,10,0,10);
            tr.addView(td);
            this.tl_info.addView(tr);
        }
        //Log.d("HotelFragmentView","createHotelInfoTable end");
    }

}
