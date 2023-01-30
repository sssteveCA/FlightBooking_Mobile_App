package com.example.flightbooking.fragments.news.post;

import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class PostFragmentView {

    private TextView tv_title;
    private WebView wv_content;
    private TextView tv_content;
    private TextView tv_categories;
    private TextView tv_tags;
    private TextView tv_created_at;
    private TextView tv_updated_at;
    private Button bt_back;

    public PostFragmentView(HashMap<String, View> views){
        this.assign(views);
    }

    public TextView getTvTitle(){return this.tv_title;}
    public WebView getWvContent(){return this.wv_content;}
    public TextView getTvContent(){return this.tv_content;}
    public TextView getTvCategories(){return this.tv_categories;}
    public TextView getTvTags(){return this.tv_tags;}
    public TextView getTvCreatedAt(){return this.tv_created_at;}
    public TextView getTvUpdatedAt(){return this.tv_updated_at;}
    public Button getBtBack(){return this.bt_back;}

    private void assign(HashMap<String,View> views){
        this.tv_title = (TextView) views.get("tv_title");
        this.wv_content = (WebView) views.get("wv_content");
        this.tv_content = (TextView) views.get("tv_content");
        this.tv_categories = (TextView) views.get("tv_categories");
        this.tv_tags = (TextView) views.get("tv_tags");
        this.tv_created_at = (TextView) views.get("tv_created_at");
        this.tv_updated_at = (TextView) views.get("tv_updated_at");
        this.bt_back = (Button) views.get("bt_back");
    }
}
