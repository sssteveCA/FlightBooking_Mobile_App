package com.example.flightbooking.fragments.mainmenu;

import android.widget.Button;
import android.widget.ListView;

public abstract class MenuView {
    protected Button showHide;
    protected ListView menuView;

    public MenuView(ListView menuView, Button showHide){
        this.menuView = menuView;
        this.showHide = showHide;
    }

    public Button getShowHide(){return this.showHide;}
    public ListView getMenu(){return this.menuView;}

    public void setShowHide(Button show_hide){this.showHide = show_hide;}
    public void setMenuView(ListView menuView){this.menuView = menuView;}
}
