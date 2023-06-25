package com.example.flightbooking.fragments.home.carrental;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.flightbooking.exception.MissingValuesException;

import java.util.Map;

public class CarRentalFragmentView {

    private Spinner sp_rent_company, sp_car, sp_pickup_country, sp_pickup_location, sp_delivery_country, sp_delivery_location, sp_age_band;
    private EditText et_rentstart, et_rentend;
    private Button bt_go;

    private static String[] itemsName = {
            "rent_company","car","pickup_country","pickup_location","delivery_country","delivery_location","rentstart","rentend","age_band","go"
    };

    public CarRentalFragmentView(Map<String, View> items) throws MissingValuesException {
        this.assignItems(items);
    }

    public Spinner getSpRentCompany(){ return this.sp_rent_company; }
    public Spinner getSpCar(){ return this.sp_car; }
    public Spinner getSpPickupCountry(){ return this.sp_pickup_country; }
    public Spinner getSpPickupLocation(){ return this.sp_pickup_location; }
    public Spinner getSpDeliveryCountry(){ return this.sp_delivery_country; }
    public Spinner getSpDeliveryLocation(){ return this.sp_delivery_location; }
    public EditText getEtRentStart(){ return this.et_rentstart; }
    public EditText getEtRentEnd(){ return this.et_rentend; }
    public Spinner getSpAgeBand(){ return this.sp_age_band; }
    public Button getBtGo(){ return this.bt_go; }

    private void assignItems(Map<String, View> items) throws MissingValuesException {
        for(String item: CarRentalFragmentView.itemsName){
            if(!items.containsKey(item)){
                throw new MissingValuesException("Impossibile trovare uno o più dati richiesti");
            }
        }
        this.sp_rent_company = (Spinner) items.get("rent_company");
        this.sp_car = (Spinner) items.get("car");
        this.sp_pickup_country = (Spinner) items.get("pickup_country");
        this.sp_pickup_location = (Spinner) items.get("pickup_location");
        this.sp_delivery_country = (Spinner) items.get("delivery_country");
        this.sp_delivery_location = (Spinner) items.get("delivery_location");
        this.et_rentstart = (EditText) items.get("rentstart");
        this.et_rentend = (EditText) items.get("rentend");
        this.sp_age_band = (Spinner) items.get("age_band");
        this.bt_go = (Button)items.get("go");
    }
}
