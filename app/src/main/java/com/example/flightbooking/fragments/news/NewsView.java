package com.example.flightbooking.fragments.news;

import android.widget.Button;

public class NewsView {

    private Button bt_back;

    public NewsView(Button bt_back){
        this.bt_back = bt_back;
    }

    public Button getBtBack(){return this.bt_back;}
}
