package com.example.flightbooking.fragments.home.hotel.hotelinfopreview;

import com.example.flightbooking.models.response.hotel.Hotel;
import com.example.flightbooking.models.response.hotel.HotelInfo;

import java.lang.reflect.Field;
import java.util.HashMap;

public class HotelInfoPreviewFragmentModel {

    private HotelInfo hi;
    private String session_id;
    private HashMap<String, Object> table_data;

    public HotelInfoPreviewFragmentModel(HotelInfo hi){
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
