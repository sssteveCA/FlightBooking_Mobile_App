package com.example.flightbooking.fragments.information.privacypolicy;

import android.widget.Button;

public class PrivacyPolicyFragmentView {

    private Button bt_back;

    public PrivacyPolicyFragmentView(Button bt_back){
        this.bt_back = bt_back;
    }

    public Button getBtBack(){return this.bt_back;}
}
