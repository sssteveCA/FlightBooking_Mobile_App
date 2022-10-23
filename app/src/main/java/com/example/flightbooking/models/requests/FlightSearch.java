package com.example.flightbooking.models.requests;

//This class is used by retrofit when user wants to get ticket info before the payment
public class FlightSearch {
    public String flight_type;
    public String company_name;
    public String from_country;
    public String from_airport;
    public String to_country;
    public String to_airport;
    public String oneway_date;
    public String roundtrip_start_date;
    public String roundtrip_end_date;
    public Integer adults;
    public Integer teenagers;
    public Integer children;
    public Integer newborns;
}
