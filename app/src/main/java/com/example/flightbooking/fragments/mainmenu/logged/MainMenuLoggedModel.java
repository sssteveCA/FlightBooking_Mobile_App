package com.example.flightbooking.fragments.mainmenu.logged;

import android.content.Context;
import com.example.flightbooking.models.MenuItem;
import java.util.ArrayList;

public class MainMenuLoggedModel {
    private Context ctx;
    private ArrayList<MenuItem> menuItems;
    private int menuStatus; //Status of menu (hidden or expaned)

    public MainMenuLoggedModel(Context ctx){
        this.ctx = ctx;
        this.menuItems = new ArrayList<>();
    }

    public ArrayList<MenuItem> getMenuItems(){return this.menuItems;}
    public int getMenuStatus(){return this.menuStatus;}

    public void setMenuStatus(int menuStatus){this.menuStatus = menuStatus;}
}
