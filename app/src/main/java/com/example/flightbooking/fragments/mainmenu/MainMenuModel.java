package com.example.flightbooking.fragments.mainmenu;

import android.content.Context;
import android.widget.ListView;

import com.example.flightbooking.common.Connection;

import java.util.ArrayList;
import java.util.Arrays;

public class MainMenuModel {

    //These items are present in any case(if user is logged or not)
    private static final String[] globalItems = {
            "Home","Chi siamo","News","Contatti"
    };

    private static final String[] notLoggedItems = {
            "Login","Registrati"
    };

    private Context ctx;
    private ArrayList<String> menuItems;
    private boolean logged; //select menu to show(not logged menu or logged menu)

    public MainMenuModel(Context ctx){
        this.ctx = ctx;
        this.menuItems = new ArrayList<>();
    }

    public ArrayList<String> getMenuItems(){return this.menuItems;}
    public boolean isLogged(){return this.logged;}

    public void setLogged(boolean logged){this.logged = logged;}

    /**
     * Set the menu items list for non logged users
     */
    public void setMenu(){
        this.menuItems.clear();
        this.menuItems.addAll(Arrays.asList(MainMenuModel.globalItems));
        boolean connected = Connection.checkInternet(this.ctx);
        if(connected){

        }
        else{
            this.menuItems.addAll(Arrays.asList(MainMenuModel.globalItems));
        }
    }

}
