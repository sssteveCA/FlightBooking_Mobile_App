package com.example.flightbooking.fragments.news;

import android.widget.Button;
import android.widget.LinearLayout;

public class NewsView {

    private LinearLayout ll_news_list;
    private Button bt_back;

    public NewsView(LinearLayout ll_news_list, Button bt_back){
        this.ll_news_list = ll_news_list;
        this.bt_back = bt_back;
    }

    public LinearLayout getLlNewsList(){return this.ll_news_list;}
    public Button getBtBack(){return this.bt_back;}
}
