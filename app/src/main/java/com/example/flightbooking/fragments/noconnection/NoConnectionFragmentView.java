package com.example.flightbooking.fragments.noconnection;

import android.widget.Button;

public class NoConnectionFragmentView {

    private Button bt_retry;

    public NoConnectionFragmentView(Button bt_retry){
        this.bt_retry = bt_retry;
    }

    public Button getBtRetry(){return this.bt_retry;}
}
