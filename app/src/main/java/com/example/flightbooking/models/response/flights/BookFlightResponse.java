package com.example.flightbooking.models.response.flights;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Response body for /api/bookflight route
 */
public class BookFlightResponse {

    @SerializedName("done")
    @Expose
    public Boolean done;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("inserted")
    @Expose
    public Boolean inserted;
    @SerializedName("flights_number")
    @Expose
    public Integer flightsNumber;
    @SerializedName("flights_array")
    @Expose
    public List<FlightsArray> flightsArray;

    class FlightsArray {
        @SerializedName("id")
        @Expose
        public Integer id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("flight_price")
        @Expose
        public Double flightPrice;
    }

}
