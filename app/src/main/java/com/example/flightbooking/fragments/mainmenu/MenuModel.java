package com.example.flightbooking.fragments.mainmenu;

import android.content.Context;

import com.example.flightbooking.models.MenuItem;

import java.util.ArrayList;

public abstract class MenuModel {

    public static final int MENU_HIDDEN = 0;
    public static final int MENU_SHOWN = 1;

    protected Context ctx;
    protected ArrayList<MenuItem> menuItems;
    protected int menuStatus; //Status of menu (hidden or expaned)

    public MenuModel(Context ctx){
        this.ctx = ctx;
        this.menuItems = new ArrayList<>();
    }

    public ArrayList<MenuItem> getMenuItems(){return this.menuItems;}
    public int getMenuStatus(){return this.menuStatus;}

    public void setMenuStatus(int menuStatus){this.menuStatus = menuStatus;}
}
