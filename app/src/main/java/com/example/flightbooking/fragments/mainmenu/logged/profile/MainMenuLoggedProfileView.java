package com.example.flightbooking.fragments.mainmenu.logged.profile;

import android.widget.ExpandableListView;

public class MainMenuLoggedProfileView {

    private ExpandableListView elv_profile;

    public MainMenuLoggedProfileView(ExpandableListView elv_profile){
        this.elv_profile = elv_profile;
    }

    public ExpandableListView getElvProfile(){return this.elv_profile;}
}
