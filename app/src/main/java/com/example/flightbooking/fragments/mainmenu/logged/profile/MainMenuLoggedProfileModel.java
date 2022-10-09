package com.example.flightbooking.fragments.mainmenu.logged.profile;

import com.example.flightbooking.models.login.Auth;

import java.util.HashMap;
import java.util.List;

public class MainMenuLoggedProfileModel {

    private static final String[] items = {
            "Il mio profilo", "I miei voli", "Logout"
    };

    private HashMap<String, List<String>> profile_submenu_items;
    private Auth auth;

    public MainMenuLoggedProfileModel(HashMap<String, List<String>> profile_submenu_items, Auth auth){
        this.profile_submenu_items = profile_submenu_items;
        this.auth = auth;
    }

    public HashMap<String, List<String>> getProfileSubmenuItems(){return this.profile_submenu_items;}
}
