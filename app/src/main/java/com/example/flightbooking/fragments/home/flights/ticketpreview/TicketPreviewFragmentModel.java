package com.example.flightbooking.fragments.home.flights.ticketpreview;

import com.example.flightbooking.models.FlightInfo;

import java.util.HashMap;

public class TicketPreviewFragmentModel {

    private FlightInfo fi;
    private String flight_type;
    private HashMap<String, HashMap<String, Object>> flights;

    public TicketPreviewFragmentModel(FlightInfo fi){
        this.fi = fi;
        this.flight_type = fi.flightType;
    }

    public String getFlightType(){return this.flight_type;}
    public HashMap<String, HashMap<String, Object>> getFlights(){return this.flights;}
}
