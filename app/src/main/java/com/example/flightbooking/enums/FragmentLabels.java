package com.example.flightbooking.enums;

public enum FragmentLabels {
    ABOUT_US("Chi siamo"), CONTACTS("Contatti"), COOKIE_POLICY("Cookie Policy"), FLIGHTS("Voli"), LOGIN("Login"), HOME("Home"),
    NEWS("News"), NO_CONNECTION("NoConnection"), PRIVACY_POLICY("Privacy Policy"), TICKET_PREVIEW("TicketPreview"), SUBSCRIBE("Registrati"), TERMS("Termini e condizioni"), VERIFY("Verifica");


    private String label_name;

    FragmentLabels(String label_name){
        this.label_name = label_name;
    }

    public String getLabelName(){return this.label_name;}


}
