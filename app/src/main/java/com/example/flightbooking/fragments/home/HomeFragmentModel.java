package com.example.flightbooking.fragments.home;

public class HomeFragmentModel {

    public final static int FLIGHTTYPE_ROUNDTRIP = 0;
    public final static int FLIGHTTYPE_ONEWAY = 1;

    private int selected_flight_type;

    public HomeFragmentModel(){
        this.selected_flight_type = FLIGHTTYPE_ROUNDTRIP;
    }

    public int getSelectedFlightType(){return this.selected_flight_type;}

    public void setSelectedFlightType(int flight_type){
        if(flight_type != FLIGHTTYPE_ROUNDTRIP && flight_type != FLIGHTTYPE_ONEWAY)
            this.selected_flight_type = FLIGHTTYPE_ROUNDTRIP;
        else
            this.selected_flight_type = flight_type;
    }
}
