package com.example.flightbooking.fragments.mainmenu.logged.profile;

import android.content.Context;

import com.example.flightbooking.models.MenuItem;
import com.example.flightbooking.models.login.Auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainMenuLoggedProfileModel {

    /**
     * Profile submenu item labels
     */
    public static final String[] items = {
            "Il mio profilo", "I miei voli", "Logout"
    };

    private HashMap<String, List<MenuItem>> profile_submenu_items;
    private Auth auth;

    public MainMenuLoggedProfileModel(Auth auth){
        this.auth = auth;
        this.profile_submenu_items = new HashMap<>();
        this.setMenu();
    }

    public HashMap<String, List<MenuItem>> getProfileSubmenuItems(){return this.profile_submenu_items;}

    /**
     * Set the submenu profile items list for logged users
     */
    private void setMenu(){
        List<MenuItem> elv_items = new ArrayList<>();
        for(String label: MainMenuLoggedProfileModel.items){
            MenuItem mi = new MenuItem(label);
            elv_items.add(mi);
        }
        this.profile_submenu_items.put(this.auth.user.name,elv_items);
    }
}
