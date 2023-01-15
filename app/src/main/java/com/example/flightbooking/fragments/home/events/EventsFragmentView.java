package com.example.flightbooking.fragments.home.events;

import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class EventsFragmentView {

    private LinearLayout ll;
    private TextView tv_message;
    private GridView gv;

    public EventsFragmentView(LinearLayout ll,TextView tv_message,GridView gv){
        this.ll = ll;
        this.tv_message = tv_message;
        this.gv = gv;
    }

    public LinearLayout getLl(){return this.ll;}
    public TextView getTvMessage(){return this.tv_message;}
    public GridView getGv(){return this.gv;}
}
