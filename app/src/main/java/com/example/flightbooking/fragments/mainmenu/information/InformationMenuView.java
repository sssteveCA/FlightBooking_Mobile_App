package com.example.flightbooking.fragments.mainmenu.information;

import android.widget.ExpandableListView;

public class InformationMenuView {

    private ExpandableListView elv_info;

    public InformationMenuView(ExpandableListView elv_info){
        this.elv_info = elv_info;
    }

    public ExpandableListView getElvInfo(){return this.elv_info;}
}
