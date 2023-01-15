package com.example.flightbooking.models.response.flightevents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetFlightEventsResponse {

    @SerializedName("done")
    @Expose
    public Boolean done;
    @SerializedName("empty")
    @Expose
    public Boolean empty;
    @SerializedName("list")
    @Expose
    public List<FlightEvent> list = null;
    @SerializedName("message")
    @Expose
    public String message;
}
