package com.example.flightbooking.fragments.mainmenu.notlogged;

import android.content.Context;

import com.example.flightbooking.common.Connection;
import com.example.flightbooking.models.MenuItem;

import java.util.ArrayList;

public class MainMenuNotLoggedModel {

    public static final int MENU_HIDDEN = 0;
    public static final int MENU_SHOWN = 1;

    //These items are present in any case(if user is logged or not)
    private static final String[] globalItems = {
            "Home","Chi siamo","News","Contatti"
    };

    private static final String[] notLoggedItems = {
            "Login","Registrati"
    };

    private Context ctx;
    private ArrayList<MenuItem> menuItems;
    private boolean logged; //select menu to show(not logged menu or logged menu)
    private int menuStatus; //Status of menu (hidden or expaned)
    private String lastLabelClicked; //Last menu item label clicked

    public MainMenuNotLoggedModel(Context ctx){
        this.ctx = ctx;
        this.menuItems = new ArrayList<>();
        this.lastLabelClicked = MainMenuNotLoggedModel.globalItems[0]; //Trigger menu item 'Home' click
    }

    public ArrayList<MenuItem> getMenuItems(){return this.menuItems;}
    public int getMenuStatus(){return this.menuStatus;}
    public boolean isLogged(){return this.logged;}
    public String getLastLabelClicked(){return this.lastLabelClicked;}

    public void setLogged(boolean logged){this.logged = logged;}
    public void setMenuStatus(int menuStatus){this.menuStatus = menuStatus;}
    public void setLastLabelClicked(String lastLabelClicked){this.lastLabelClicked = lastLabelClicked;}

    /**
     * Set the menu items list for non logged users
     */
    public void setMenu(){
        this.menuItems.clear();
        for(String label: MainMenuNotLoggedModel.globalItems){
            MenuItem mi = new MenuItem(label);
            this.menuItems.add(mi);
        }
        boolean connected = Connection.checkInternet(this.ctx);
        if(connected){

        }
        else{
            for(String label: MainMenuNotLoggedModel.notLoggedItems){
                MenuItem mi = new MenuItem(label);
                this.menuItems.add(mi);
            }
        }
    }

}
