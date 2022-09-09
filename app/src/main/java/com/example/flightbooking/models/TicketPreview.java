package com.example.flightbooking.models;

import com.google.gson.annotations.SerializedName;

public class TicketPreview {
    @SerializedName("company_name")
    public String company_name;
    @SerializedName("departure_country")
    public String departure_country;
    @SerializedName("departure_airport")
    public String departure_airport;
    @SerializedName("booking_date")
    public String booking_date;
    @SerializedName("flight_date")
    public String flight_date;
    @SerializedName("flight_time")
    public String flight_time;
    @SerializedName("arrival_country")
    public String arrival_country;
    @SerializedName("arrival_airport")
    public String arrival_airport;
    @SerializedName("adults")
    public int adults;
    @SerializedName("teenagers")
    public int teenagers;
    @SerializedName("children")
    public int children;
    @SerializedName("newborns")
    public int newborns;
    @SerializedName("flight_price")
    public float flight_price;
}
