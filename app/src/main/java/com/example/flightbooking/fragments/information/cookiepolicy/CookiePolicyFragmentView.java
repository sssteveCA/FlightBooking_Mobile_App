package com.example.flightbooking.fragments.information.cookiepolicy;

import android.webkit.WebView;
import android.widget.Button;

public class CookiePolicyFragmentView {

    private WebView wv_content;
    private Button bt_back;

    public CookiePolicyFragmentView(WebView wv_content,Button bt_back){
        this.wv_content = wv_content;
        this.bt_back = bt_back;
    }

    public WebView getWvContent(){return this.wv_content;}
    public Button getBtBack(){return this.bt_back;}
}
