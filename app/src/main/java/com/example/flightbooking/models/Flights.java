package com.example.flightbooking.models;

import com.example.flightbooking.models.flights.Oneway;
import com.example.flightbooking.models.flights.Outbound;
import com.example.flightbooking.models.flights.Return;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Flights {
    @SerializedName("oneway")
    @Expose
    public Oneway oneway;
    @SerializedName("outbound")
    @Expose
    public Outbound outbound;
    @SerializedName("return")
    @Expose
    public Return returnF;
}
