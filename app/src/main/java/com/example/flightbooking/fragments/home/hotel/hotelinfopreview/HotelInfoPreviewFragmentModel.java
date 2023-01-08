package com.example.flightbooking.fragments.home.hotel.hotelinfopreview;

import com.example.flightbooking.models.response.hotel.HotelInfo;

import java.util.HashMap;

public class HotelInfoPreviewFragmentModel {

    private HotelInfo hi;
    private String session_id;
    private HashMap<String, Object> tableData;

    public HotelInfoPreviewFragmentModel(HotelInfo hi){
        this.hi = hi;
        this.session_id = this.hi.response.session_id;
    }

    public String getSessionId(){return this.session_id;}

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
