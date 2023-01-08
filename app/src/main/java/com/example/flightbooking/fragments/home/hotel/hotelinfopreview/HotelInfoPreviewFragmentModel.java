package com.example.flightbooking.fragments.home.hotel.hotelinfopreview;

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
    public void setHiptvTableValues(String key, String value){
        if(key.equalsIgnoreCase("Paese")){
            this.hiptv.country = value; return;
        }
        else if(key.equalsIgnoreCase("Città")){
            this.hiptv.city = value; return;
        }
        else if(key.equalsIgnoreCase("Albergo")) {
            this.hiptv.hotel = value; return;
        }
        else if(key.equalsIgnoreCase("Data check-in")){
            this.hiptv.checkin = value; return;
        }
        else if(key.equalsIgnoreCase("Data check-out")){
            this.hiptv.checkout = value; return;
        }
        else if(key.equalsIgnoreCase("Persone")){
            this.hiptv.people = value; return;
        }
        else if(key.equalsIgnoreCase("Stanze")){
            this.hiptv.rooms = value; return;
        }
        else if(key.equalsIgnoreCase("Prezzo"))
            this.hiptv.price = value;
    }

    /**
     * Set the data to show in the TableLayout view
     * @return
     * @throws IllegalAccessException
     */
    private HashMap<String, Object> setHotelInfoHashMap() throws IllegalAccessException {
        Hotel hotel = this.hi.response.hotel;
        if(hotel != null){
            HashMap<String, Object> data = new HashMap<>();
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
        return null;
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
        if(key.equalsIgnoreCase("")) return "Data check-in";
        if(key.equalsIgnoreCase("")) return "Data check-out";
        if(key.equalsIgnoreCase("")) return "Persone";
        if(key.equalsIgnoreCase("")) return "Stanze";
        if(key.equalsIgnoreCase("")) return "Prezzo";
        return "";
    }



}
