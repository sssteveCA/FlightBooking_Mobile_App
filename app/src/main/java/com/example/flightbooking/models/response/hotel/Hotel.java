package com.example.flightbooking.models.response.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hotel {

    @SerializedName("country")
    @Expose
    public String country;
    @SerializedName("city")
    @Expose
    public String city;
    @SerializedName("hotel")
    @Expose
    public String hotel;
    @SerializedName("checkin")
    @Expose
    public String checkin;
    @SerializedName("checkout")
    @Expose
    public String checkout;
    @SerializedName("people")
    @Expose
    public Integer people;
    @SerializedName("rooms")
    @Expose
    public Integer rooms;
    @SerializedName("price")
    @Expose
    public Double price;

}
