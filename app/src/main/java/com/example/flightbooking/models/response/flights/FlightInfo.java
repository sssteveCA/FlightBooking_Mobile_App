package com.example.flightbooking.models.response.flights;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class FlightInfo implements Serializable {
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
