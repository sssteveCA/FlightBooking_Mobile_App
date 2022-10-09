package com.example.flightbooking.fragments.mainmenu.notlogged;

import android.content.Context;

import com.example.flightbooking.common.Connection;
import com.example.flightbooking.fragments.mainmenu.MenuModel;
import com.example.flightbooking.models.MenuItem;

import java.util.ArrayList;

public class MainMenuNotLoggedModel extends MenuModel {

    //These items are present in any case(if user is logged or not)
    private static final String[] items = {
            "Home","Chi siamo","News","Contatti","Login","Registrati"
    };

    private boolean logged; //select menu to show(not logged menu or logged menu)
    private int menuStatus; //Status of menu (hidden or expaned)
    private String lastLabelClicked; //Last menu item label clicked

    public MainMenuNotLoggedModel(Context ctx){
        super(ctx);
        this.lastLabelClicked = MainMenuNotLoggedModel.items[0]; //Trigger menu item 'Home' click
    }

    public boolean isLogged(){return this.logged;}
    public String getLastLabelClicked(){return this.lastLabelClicked;}

    public void setLogged(boolean logged){this.logged = logged;}
    public void setLastLabelClicked(String lastLabelClicked){this.lastLabelClicked = lastLabelClicked;}

    /**
     * Set the menu items list for non logged users
     */
    public void setMenu(){
        this.menuItems.clear();
        for(String label: MainMenuNotLoggedModel.items){
            MenuItem mi = new MenuItem(label);
            this.menuItems.add(mi);
        }
    }

}
