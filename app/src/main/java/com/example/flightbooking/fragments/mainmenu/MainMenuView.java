package com.example.flightbooking.fragments.mainmenu;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenuView {

    private Button showHide;
    private ListView menuView;

    public MainMenuView(ListView menuView, Button showHide){
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
