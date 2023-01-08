package com.example.flightbooking.enums;

public enum FragmentPackages {
    ABOUT_US("com.example.flightbooking.fragments.aboutus.AboutUsFragment"),
    CONTACTS("com.example.flightbooking.fragments.contacts.ContactsFragment"),
    COOKIE_POLICY("com.example.flightbooking.fragments.information.cookiepolicy.CookiePolicyFragment"),
    FLIGHTS("com.example.flightbooking.fragments.home.flights.FlightsFragment"),
    HOME("com.example.flightbooking.fragments.home.HomeFragment"),
    HOTELINFO_PREVIEW("com.example.flightbooking.fragments.home.hotel.hotelinfopreview.HotelInfoPreviewFragment"),
    HOTELS("com.example.flightbooking.fragments.home.hotel.HotelFragment"),
    LOGIN("com.example.flightbooking.fragments.login.LoginFragment"),
    NEWS("com.example.flightbooking.fragments.news.NewsFragment"),
    NO_CONNECTION("com.example.flightbooking.fragments.noconnection.NoConnectionFragment"),
    PRIVACY_POLICY("com.example.flightbooking.fragments.information.privacypolicy.PrivacyPolicyFragment"),
    SUBSCRIBE("com.example.flightbooking.fragments.subscribe.SubscribeFragment"),
    TERMS("com.example.flightbooking.fragments.information.terms.TermsFragment"),
    TICKET_PREVIEW("com.example.flightbooking.fragments.home.flights.ticketpreview.TicketPreviewFragment"),
    VERIFY("com.example.flightbooking.fragments.subscribe.verifyaccount.VerifyAccountFragment");

    public String package_name;

    FragmentPackages(String package_name){
        this.package_name = package_name;
    }

    public String getPackageName(){return this.package_name;}
}
