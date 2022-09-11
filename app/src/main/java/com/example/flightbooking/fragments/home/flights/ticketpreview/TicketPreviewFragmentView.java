package com.example.flightbooking.fragments.home.flights.ticketpreview;

import android.widget.LinearLayout;
import android.widget.TableLayout;


public class TicketPreviewFragmentView {

    private LinearLayout container;
    private TableLayout table;

    public LinearLayout getContainer(){return this.container;}
    public TableLayout getTable(){return this.table;}

    public void setContainer(LinearLayout container){this.container = container;}
    public void setTable(TableLayout table){this.table = table;}
}
