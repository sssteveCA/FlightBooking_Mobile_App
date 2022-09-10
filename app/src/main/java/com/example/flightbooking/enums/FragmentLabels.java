package com.example.flightbooking.enums;

public enum FragmentLabels {
    ABOUT_US("Chi siamo"), CONTACTS("Contatti"),LOGIN("Login"), HOME("Home"),
    NEWS("News"), NO_CONNECTION("NoConnection"), SUBSCRIBE("Registrati");


    private String label_name;

    private FragmentLabels(String label_name){
        this.label_name = label_name;
    }

    public String getLabelName(){return this.label_name;}

}
