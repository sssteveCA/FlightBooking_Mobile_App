package com.example.flightbooking.fragments.news;

import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class NewsFragmentView {

    private LinearLayout ll_news_list;
    private TextView tv_message;
    private RecyclerView rv_posts;
    private Button bt_back;
    private ProgressBar pb;

    public NewsFragmentView(LinearLayout ll_news_list, TextView tv_message, RecyclerView rv_posts, ProgressBar pb, Button bt_back){
        this.ll_news_list = ll_news_list;
        this.tv_message = tv_message;
        this.rv_posts = rv_posts;
        this.pb = pb;
        this.bt_back = bt_back;
    }

    public LinearLayout getLlNewsList(){return this.ll_news_list;}
    public TextView getTvMessage(){return this.tv_message;}
    public RecyclerView getRvPosts(){return this.rv_posts;}
    public Button getBtBack(){return this.bt_back;}
    public ProgressBar getPb(){return this.pb;}
}
