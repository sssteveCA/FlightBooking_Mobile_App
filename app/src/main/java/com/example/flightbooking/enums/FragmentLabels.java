package com.example.flightbooking.enums;

public enum FragmentLabels {
    ABOUT_US("Chi siamo"),
    CONTACTS("Contatti"),
    COOKIE_POLICY("Cookie Policy"),
    EVENTS("Events"),
    FLIGHTS("Voli"),
    LOGIN("Login"),
    HOME("Home"),
    HOTELINFO_PREVIEW("HotelInfoPreview"),
    HOTELS("Alberghi"),
    NEWS("News"),
    NO_CONNECTION("NoConnection"),
    POST("Post"),
    PRIVACY_POLICY("Privacy Policy"),
    SINGLE_EVENT("SingleEvent"),
    SUBSCRIBE("Registrati"),
    TERMS("Termini e condizioni"),
    TICKET_PREVIEW("TicketPreview"),
    VERIFY("Verifica");


    private String label_name;

    FragmentLabels(String label_name){
        this.label_name = label_name;
    }

    public String getLabelName(){return this.label_name;}


}
