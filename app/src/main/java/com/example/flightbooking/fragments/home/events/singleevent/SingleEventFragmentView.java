package com.example.flightbooking.fragments.home.events.singleevent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;

public class SingleEventFragmentView {

    private TextView tv_title;
    private TextView tv_date;
    private TextView tv_link;
    private ImageView iv_image;
    private TextView tv_price;
    private EditText et_quantity;
    private Button bt_book;
    private Button bt_back;

    public SingleEventFragmentView(HashMap<String, View> items){

    }

    public TextView getTvTitle(){return this.tv_title;}
    public TextView getTvDate(){return this.tv_date;}
    public TextView getTvLink(){return this.tv_link;}
    public ImageView getIvImage(){return this.iv_image;}
    public TextView getTvPrice(){return this.tv_price;}
    public EditText getEtQuantity(){return this.et_quantity;}
    public Button getBtBook(){return this.bt_book;}
    public Button getBtBack(){return this.bt_back;}
}
