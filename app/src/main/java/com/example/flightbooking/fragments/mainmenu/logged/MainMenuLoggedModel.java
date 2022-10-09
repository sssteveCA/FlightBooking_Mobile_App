package com.example.flightbooking.fragments.mainmenu.logged;

import android.content.Context;

import com.example.flightbooking.fragments.mainmenu.MenuModel;
import com.example.flightbooking.models.MenuItem;

public class MainMenuLoggedModel extends MenuModel {

    private static final String[] items = {
            "Home","Chi siamo","News","Contatti"
    };

    public MainMenuLoggedModel(Context ctx){
        super(ctx);
        this.lastLabelClicked = MainMenuLoggedModel.items[0]; //Trigger menu item 'Home' click
        this.setMenu();
    }

    /**
     * Set the menu items list for logged users
     */
    private void setMenu(){
        this.menuItems.clear();
        for(String label: MainMenuLoggedModel.items){
            MenuItem mi = new MenuItem(label);
            this.menuItems.add(mi);
        }
    }

}
