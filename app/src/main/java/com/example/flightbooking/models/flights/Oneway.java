package com.example.flightbooking.models.flights;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Oneway {
    @SerializedName("company_name")
    @Expose
    public String companyName;
    @SerializedName("departure_country")
    @Expose
    public String departureCountry;
    @SerializedName("departure_airport")
    @Expose
    public String departureAirport;
    @SerializedName("booking_date")
    @Expose
    public String bookingDate;
    @SerializedName("flight_date")
    @Expose
    public String flightDate;
    @SerializedName("flight_time")
    @Expose
    public String flightTime;
    @SerializedName("arrival_country")
    @Expose
    public String arrivalCountry;
    @SerializedName("arrival_airport")
    @Expose
    public String arrivalAirport;
    @SerializedName("adults")
    @Expose
    public Integer adults;
    @SerializedName("teenagers")
    @Expose
    public Integer teenagers;
    @SerializedName("children")
    @Expose
    public Integer children;
    @SerializedName("newborns")
    @Expose
    public Integer newborns;
    @SerializedName("flight_price")
    @Expose
    public Float flightPrice;
}
