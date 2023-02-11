package com.example.flightbooking.fragments.home.events.singleevent;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flightbooking.models.response.flightevents.FlightEvent;

import java.util.HashMap;
import java.util.Map;

public class SingleEventFragmentView {

    private TextView tv_title;
    private TextView tv_date;
    private TextView tv_link;
    private ImageView iv_image;
    private TextView tv_price;
    private EditText et_quantity;
    private Button bt_book;
    private Button bt_back;

    public SingleEventFragmentView(Map<String, View> items){
        this.assignItems(items);
    }

    public TextView getTvTitle(){return this.tv_title;}
    public TextView getTvDate(){return this.tv_date;}
    public TextView getTvLink(){return this.tv_link;}
    public ImageView getIvImage(){return this.iv_image;}
    public TextView getTvPrice(){return this.tv_price;}
    public EditText getEtQuantity(){return this.et_quantity;}
    public Button getBtBook(){return this.bt_book;}
    public Button getBtBack(){return this.bt_back;}

    private void assignItems(Map<String, View> items){
        this.tv_title = (TextView) items.get("tv_title");
        this.tv_date = (TextView) items.get("tv_date");
        this.tv_link = (TextView) items.get("tv_link");
        this.iv_image = (ImageView) items.get("iv_image");
        this.tv_price = (TextView) items.get("tv_price");
        this.et_quantity = (EditText) items.get("et_quantity");
        this.bt_book = (Button) items.get("bt_book");
        this.bt_back = (Button)items.get("bt_back");
    }
}
