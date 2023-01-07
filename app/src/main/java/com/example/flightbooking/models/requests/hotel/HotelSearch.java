package com.example.flightbooking.models.requests.hotel;

/**
 * This class is used by retrofit when user wants to get hotel rooms ticket info before the payment
 */
public class HotelSearch {
    public String country;
    public String city;
    public String hotel;
    public String checkin;
    public String checkout;
    public Integer rooms;
    public Integer people;
}
