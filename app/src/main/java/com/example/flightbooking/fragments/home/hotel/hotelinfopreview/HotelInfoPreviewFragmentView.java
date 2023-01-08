package com.example.flightbooking.fragments.home.hotel.hotelinfopreview;

import android.widget.Button;
import android.widget.LinearLayout;

public class HotelInfoPreviewFragmentView {

    private LinearLayout ll_table;
    private Button bt_book;
    private Button bt_back;

    public HotelInfoPreviewFragmentView(LinearLayout ll_table, Button bt_book, Button bt_back){
        this.ll_table = ll_table;
        this.bt_book = bt_book;
        this.bt_back = bt_back;
    }

    public LinearLayout getLlTable(){return this.ll_table;}
    public Button getBtBook(){return this.bt_book;}
    public Button getBtBack(){return this.bt_back;}
}
