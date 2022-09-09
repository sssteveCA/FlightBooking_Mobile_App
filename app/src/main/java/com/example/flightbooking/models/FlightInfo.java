package com.example.flightbooking.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class FlightInfo {
    @SerializedName("session_id")
    public String session_id;
    @SerializedName("flight_type")
    public String flight_type;
    @SerializedName("flights")
    public HashMap<String, TicketPreview> flights;
}
