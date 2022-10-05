package com.example.flightbooking.enums;

public enum FragmentLabels {
    ABOUT_US("Chi siamo"), CONTACTS("Contatti"), FLIGHTS("Voli"), LOGIN("Login"), HOME("Home"),
    NEWS("News"), NO_CONNECTION("NoConnection"), TICKET_PREVIEW("TicketPreview"), SUBSCRIBE("Registrati"), VERIFY("Verifica");


    private String label_name;

    FragmentLabels(String label_name){
        this.label_name = label_name;
    }

    public String getLabelName(){return this.label_name;}


}
