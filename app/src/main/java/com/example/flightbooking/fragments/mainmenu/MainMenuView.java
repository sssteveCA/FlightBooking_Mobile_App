package com.example.flightbooking.fragments.mainmenu;

import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenuView {

    private HashMap<String, View> widgets; //Widgets used in MainMenu

    public MainMenuView(){

    }

    public HashMap<String, View> getWidgets(){return this.widgets;}

    public void setWidgets(HashMap<String, View> widgets){this.widgets = widgets;}

    public void setListView(ArrayList<String> items){

    }
}
