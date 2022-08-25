package com.example.flightbooking.fragments.mainmenu.notlogged;

import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainMenuNotLoggedView {

    private Button showHide;
    private ListView menuView;

    public MainMenuNotLoggedView(ListView menuView, Button showHide){
        this.menuView = menuView;
        this.showHide = showHide;
    }

    public Button getShowHide(){return this.showHide;}
    public ListView getMenu(){return this.menuView;}

    public void setShowHide(Button show_hide){this.showHide = show_hide;}
    public void setMenuView(ListView menuView){this.menuView = menuView;}


    public void setListView(ArrayList<String> items){

    }
}
