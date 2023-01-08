package com.example.flightbooking.models.response.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class HotelInfo implements Serializable {

    @SerializedName("done")
    @Expose
    public Boolean done;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("data")
    @Expose
    public Hotel data;
}
