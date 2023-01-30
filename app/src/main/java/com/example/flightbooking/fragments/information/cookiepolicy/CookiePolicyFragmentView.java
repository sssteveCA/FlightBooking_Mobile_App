package com.example.flightbooking.fragments.information.cookiepolicy;

import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class CookiePolicyFragmentView {

    private TextView tv_message;
    private WebView wv_content;
    private Button bt_back;

    public CookiePolicyFragmentView(TextView tv_message,WebView wv_content,Button bt_back){
        this.tv_message = tv_message;
        this.wv_content = wv_content;
        this.bt_back = bt_back;
    }

    public TextView getTvMessage(){return this.tv_message;}
    public WebView getWvContent(){return this.wv_content;}
    public Button getBtBack(){return this.bt_back;}
}
