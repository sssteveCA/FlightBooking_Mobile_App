package com.example.flightbooking.models.response.flightevents;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FlightEvent {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("location")
    @Expose
    public String location;
    @SerializedName("gmLink")
    @Expose
    public String gmLink;
    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("price")
    @Expose
    public Double price;
    @SerializedName("date")
    @Expose
    public String date;
    @SerializedName("image")
    @Expose
    public String image;
}
