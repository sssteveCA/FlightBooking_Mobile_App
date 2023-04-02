package com.example.flightbooking.fragments.news;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Map;

public class NewsFragmentView {

    private LinearLayout ll_news_list;
    private TextView tv_message;
    private RecyclerView rv_posts;
    private Button bt_back;
    private ProgressBar pb;

    public NewsFragmentView(Map<String, View> views){
        this.assignValues(views);
    }

    private void assignValues(Map<String, View> views){
        this.ll_news_list = (LinearLayout) views.get("ll_news_list");
        this.tv_message = (TextView) views.get("tv_message");
        this.rv_posts = (RecyclerView) views.get("rv_posts");
        this.pb = (ProgressBar) views.get("pb");
        this.bt_back = (Button) views.get("bt_back");
    }

    public LinearLayout getLlNewsList(){return this.ll_news_list;}
    public TextView getTvMessage(){return this.tv_message;}
    public RecyclerView getRvPosts(){return this.rv_posts;}
    public Button getBtBack(){return this.bt_back;}
    public ProgressBar getPb(){return this.pb;}
}
