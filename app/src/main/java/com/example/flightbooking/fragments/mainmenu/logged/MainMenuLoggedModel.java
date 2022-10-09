package com.example.flightbooking.fragments.mainmenu.logged;

import android.content.Context;

import com.example.flightbooking.fragments.mainmenu.MenuModel;
import com.example.flightbooking.models.MenuItem;

public class MainMenuLoggedModel extends MenuModel {

    private static final String[] items = {
            "Home","Chi siamo","News","Contatti"
    };

    private String lastLabelClicked; //Last menu item label clicked

    public MainMenuLoggedModel(Context ctx){
        super(ctx);
        this.lastLabelClicked = MainMenuLoggedModel.items[0]; //Trigger menu item 'Home' click
    }
    public String getLastLabelClicked(){return this.lastLabelClicked;}

    /**
     * Set the menu items list for logged users
     */
    public void setMenu(){
        this.menuItems.clear();
        for(String label: MainMenuLoggedModel.items){
            MenuItem mi = new MenuItem(label);
            this.menuItems.add(mi);
        }
    }

}
