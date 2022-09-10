package com.example.flightbooking.enums;

public enum FragmentPackages {
    ABOUT_US("com.example.flightbooking.fragments.aboutus.AboutUsFragment"),
    CONTACTS("com.example.flightbooking.fragments.contacts.ContactsFragment"),
    FLIGHTS("com.example.flightbooking.fragments.home.flights.FlightsFragment"),
    HOME("com.example.flightbooking.fragments.home.HomeFragment"),
    LOGIN("com.example.flightbooking.fragments.login.LoginFragment"),
    NEWS("com.example.flightbooking.fragments.news.NewsFragment"),
    NO_CONNECTION("com.example.flightbooking.fragments.noconnection.NoConnectionFragment"),
    SUBSCRIBE("com.example.flightbooking.fragments.subscribe.SubscribeFragment"),
    TICKET_PREVIEW("com.example.flightbooking.fragments.home.flights.ticketpreview");

    public String package_name;

    FragmentPackages(String package_name){
        this.package_name = package_name;
    }

    public String getPackageName(){return this.package_name;}
}
