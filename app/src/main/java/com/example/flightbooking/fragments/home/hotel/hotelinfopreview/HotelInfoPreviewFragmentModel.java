package com.example.flightbooking.fragments.home.hotel.hotelinfopreview;

import android.util.Log;

import com.example.flightbooking.models.response.hotel.Hotel;
import com.example.flightbooking.models.response.hotel.HotelInfo;

import java.lang.reflect.Field;
import java.util.HashMap;

public class HotelInfoPreviewFragmentModel {

    private HotelInfo hi;
    private String session_id;
    private HashMap<String, Object> table_data;
    private HotelInfoPreviewTableValues hiptv;

    public HotelInfoPreviewFragmentModel(HotelInfo hi){
        this.hiptv = new HotelInfoPreviewTableValues();
        this.hi = hi;
        Log.i("HotelInfoPrevievFragmentModel","constructor hi done => "+this.hi.done);
        this.session_id = this.hi.response.session_id;
        try {
            this.table_data = this.setHotelInfoHashMap();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            this.table_data = null;
        }
    }

    public String getSessionId(){return this.session_id;}
    public HashMap<String, Object> getTableData(){return this.table_data;}
    public HotelInfoPreviewTableValues getHiptv(){return this.hiptv;}

    /**
     * Set each property of the HotelInfoPreviewTableValues (needed for the next request)
     * @param key The label of the current row
     * @param value The value of the current row
     */
    public void setHiptvTableValues(String key, Object value){
        if(key.equalsIgnoreCase("Paese")){
            this.hiptv.country = (String) value; return;
        }
        else if(key.equalsIgnoreCase("Città")){
            this.hiptv.city = (String) value; return;
        }
        else if(key.equalsIgnoreCase("Albergo")) {
            this.hiptv.hotel = (String) value; return;
        }
        else if(key.equalsIgnoreCase("Data check-in")){
            this.hiptv.checkin = (String) value; return;
        }
        else if(key.equalsIgnoreCase("Data check-out")){
            this.hiptv.checkout = (String) value; return;
        }
        else if(key.equalsIgnoreCase("Persone")){
            this.hiptv.people = (Integer) value; return;
        }
        else if(key.equalsIgnoreCase("Stanze")){
            this.hiptv.rooms = (Integer) value; return;
        }
        else if(key.equalsIgnoreCase("Prezzo"))
            this.hiptv.price = (Double) value;
    }

    /**
     * Set the data to show in the TableLayout view
     * @return
     * @throws IllegalAccessException
     */
    private HashMap<String, Object> setHotelInfoHashMap() throws IllegalAccessException {
        HashMap<String, Object> data = new HashMap<>();
        Hotel hotel = this.hi.response.hotel;
        if(hotel != null){
            Class<?> hotel_class = hotel.getClass();
            Field[] hotel_fields = hotel_class.getDeclaredFields();
            for(Field field: hotel_fields){
                String field_name = field.getName();
                String row_name = this.setRowTitle(field_name);
                if(!row_name.equals("")){
                    Object hotel_val = field.get(hotel);
                    data.put(row_name,hotel_val);
                }
            }//for(Field field: hotel_fields){
        }//if(hotel != null){
        return data;
    }

    /**
     * Set table row title from HashMap key
     * @param key
     * @return
     */
    private String setRowTitle(String key){
        if(key.equalsIgnoreCase("country")) return "Paese";
        if(key.equalsIgnoreCase("city")) return "Città";
        if(key.equalsIgnoreCase("hotel")) return "Albergo";
        if(key.equalsIgnoreCase("checkin")) return "Data check-in";
        if(key.equalsIgnoreCase("checkout")) return "Data check-out";
        if(key.equalsIgnoreCase("people")) return "Persone";
        if(key.equalsIgnoreCase("rooms")) return "Stanze";
        if(key.equalsIgnoreCase("price")) return "Prezzo";
        return "";
    }



}
