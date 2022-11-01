package com.example.flightbooking.fragments.home.hotel;

import android.widget.EditText;
import android.widget.Spinner;

public class HotelFragmentView {

    private Spinner sp_country,sp_city,sp_hotel;
    private EditText et_check_in,et_check_out,et_rooms,et_people;

    public HotelFragmentView(){

    }

    public Spinner getSpCountry(){return this.sp_country;}
    public Spinner getSpCity(){return this.sp_city;}
    public EditText getEtCkeckIn(){return this.et_check_in;}
    public EditText getEtCkeckOut(){return this.et_check_out;}
    public EditText getEtRooms(){return this.et_rooms;}
    public EditText getEtPeople(){return this.et_people;}
}
