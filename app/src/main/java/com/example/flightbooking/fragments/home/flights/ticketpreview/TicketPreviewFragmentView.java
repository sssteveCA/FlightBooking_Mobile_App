package com.example.flightbooking.fragments.home.flights.ticketpreview;

import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.HashMap;


public class TicketPreviewFragmentView {

    private LinearLayout container;
    private TableLayout table;
    private HashMap<String, TicketPreviewViews> flights_info_views;

    public LinearLayout getContainer(){return this.container;}
    public TableLayout getTable(){return this.table;}

    public void setContainer(LinearLayout container){this.container = container;}
    public void setTable(TableLayout table){this.table = table;}
}
