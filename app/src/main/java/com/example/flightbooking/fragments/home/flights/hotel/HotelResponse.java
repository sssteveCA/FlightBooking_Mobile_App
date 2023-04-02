package com.example.flightbooking.fragments.home.flights.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HotelResponse implements Serializable {

    @SerializedName("session_id")
    @Expose
    public String session_id;
    @SerializedName("hotel")
    @Expose
    public Hotel hotel;

}
