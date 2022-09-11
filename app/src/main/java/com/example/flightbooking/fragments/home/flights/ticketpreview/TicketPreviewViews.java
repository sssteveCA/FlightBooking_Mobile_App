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

    public void setTvCompanyName(TextView tv_company_name){this.tv_company_name = tv_company_name;}
    public void setTvDepartureCountry(TextView tv_departure_country){this.tv_departure_country = tv_departure_country;}
    public void setTvDepartureAirport(TextView tv_departure_airport){this.tv_departure_airport = tv_departure_airport;}
    public void setTvFlightDate(TextView tv_flight_date){this.tv_flight_date = tv_flight_date;}
    public void setTvArrivalCountry(TextView tv_arrival_country){this.tv_arrival_country = tv_arrival_country;}
    public void setTvArrivalAirport(TextView tv_arrival_airport){this.tv_arrival_airport = tv_arrival_airport;}
    public void setTvAdults(TextView tv_adults){this.tv_adults = tv_adults;}
    public void setTvTeenagers(TextView tv_teenagers){this.tv_teenagers = tv_teenagers;}
    public void setTvChildren(TextView tv_children){this.tv_chidren = tv_children;}
    public void setTvNewborns(TextView tv_newborns){this.tv_newborns = tv_newborns;}
    public void setTvFlightPrice(TextView tv_flight_price){this.tv_flight_price = tv_flight_price;}

}
