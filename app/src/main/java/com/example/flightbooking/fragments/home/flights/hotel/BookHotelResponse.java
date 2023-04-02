package com.example.flightbooking.fragments.home.flights.hotel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Response body for /api/bookhotel route
 */
public class BookHotelResponse {

    @SerializedName("done")
    @Expose
    public Boolean done;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("hotel")
    @Expose
    public Hotel hotel;

    class Hotel {

        @SerializedName("user_id")
        @Expose
        public Integer userId;
        @SerializedName("country")
        @Expose
        public String country;
        @SerializedName("city")
        @Expose
        public String city;
        @SerializedName("hotel")
        @Expose
        public String hotel;
        @SerializedName("booking_date")
        @Expose
        public String bookingDate;
        @SerializedName("checkin")
        @Expose
        public String checkin;
        @SerializedName("checkout")
        @Expose
        public String checkout;
        @SerializedName("rooms")
        @Expose
        public Integer rooms;
        @SerializedName("people")
        @Expose
        public Integer people;
        @SerializedName("price")
        @Expose
        public Double price;
    }
}
