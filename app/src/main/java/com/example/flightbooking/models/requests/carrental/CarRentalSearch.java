package com.example.flightbooking.models.requests.carrental;

/**
 * This class is used by retrofit when user wants to get car rental info before the payment
 */
public class CarRentalSearch {
    public String rent_company;
    public String car;
    public String pickup_country;
    public String pickup_location;
    public String delivery_country;
    public String delivery_location;
    public String rentstart;
    public String rentend;
    public String age_band;
}
