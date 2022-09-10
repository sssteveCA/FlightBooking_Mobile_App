package com.example.flightbooking.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;

public class FlightInfo {
    @SerializedName("session_id")
    @Expose
    public String sessionId;
    @SerializedName("flight_type")
    @Expose
    public String flightType;
    @SerializedName("flights")
    @Expose
    public Flights flights;
}
