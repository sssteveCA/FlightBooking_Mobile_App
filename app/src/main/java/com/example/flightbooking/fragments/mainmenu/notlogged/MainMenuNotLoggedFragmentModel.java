package com.example.flightbooking.fragments.mainmenu.notlogged;

import android.content.Context;

import com.example.flightbooking.fragments.mainmenu.MenuModel;
import com.example.flightbooking.models.MenuItem;

public class MainMenuNotLoggedFragmentModel extends MenuModel {

    /**
     * Non logged user menu item labels
     */
    public static final String[] items = {
            "Home","Chi siamo","News","Contatti","Login","Registrati"
    };

    private boolean logged; //select menu to show(not logged menu or logged menu)
    private int menuStatus; //Status of menu (hidden or expaned)

    public MainMenuNotLoggedFragmentModel(Context ctx){
        super(ctx);
        this.lastLabelClicked = MainMenuNotLoggedFragmentModel.items[0]; //Trigger menu item 'Home' click
        this.setMenu();
    }

    public boolean isLogged(){return this.logged;}

    public void setLogged(boolean logged){this.logged = logged;}

    /**
     * Set the menu items list for non logged users
     */
    private void setMenu(){
        this.menuItems.clear();
        for(String label: MainMenuNotLoggedFragmentModel.items){
            MenuItem mi = new MenuItem(label);
            this.menuItems.add(mi);
        }
    }

}
