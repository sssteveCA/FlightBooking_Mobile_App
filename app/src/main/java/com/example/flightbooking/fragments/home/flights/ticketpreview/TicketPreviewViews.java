package com.example.flightbooking.fragments.home.flights.ticketpreview;

import android.widget.TextView;

public class TicketPreviewViews {
    private TextView tv_company_name;
    private TextView tv_departure_country;
    private TextView tv_departure_airport;
    private TextView tv_flight_date;
    private TextView tv_arrival_country;
    private TextView tv_arrival_airport;
    private TextView tv_adults;
    private TextView tv_teenagers;
    private TextView tv_chidren;
    private TextView tv_newborns;
    private TextView tv_flight_price;

    public TextView getTvCompanyName(){return this.tv_company_name;}
    public TextView getTvDepartureCountry(){return this.tv_departure_country;}
    public TextView getTvDepartureAirport(){return this.tv_departure_airport;}
    public TextView getTvFlightDate(){return this.tv_flight_date;}
    public TextView getTvArrivalCountry(){return this.tv_arrival_country;}
    public TextView getTvArrivalAirport(){return this.tv_arrival_airport;}
    public TextView getTvAdults(){return this.tv_adults;}
    public TextView getTvTeenagers(){return this.tv_teenagers;}
    public TextView getTvChildren(){return this.tv_chidren;}
    public TextView getTvNewborns(){return this.tv_newborns;}
    public TextView getTvFlightPrice(){return this.tv_flight_price;}

}
