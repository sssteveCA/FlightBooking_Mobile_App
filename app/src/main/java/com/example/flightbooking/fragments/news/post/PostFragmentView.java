package com.example.flightbooking.fragments.news.post;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class PostFragmentView {

    private TextView tv_title;
    private TextView tv_content;
    private TextView tv_categories;
    private TextView tv_tags;
    private TextView tv_created_at;
    private TextView tv_updated_at;
    private Button bt_back;

    public PostFragmentView(HashMap<String, View> views){

    }

    public TextView getTvTitle(){return this.tv_title;}
    public TextView getTvContent(){return this.tv_content;}
    public TextView getTvCategories(){return this.tv_categories;}
    public TextView getTvTags(){return this.tv_tags;}
    public TextView getTvCreatedAt(){return this.tv_created_at;}
    public TextView getTvUpdatedAt(){return this.tv_updated_at;}
    public Button getBtBack(){return this.bt_back;}
}
