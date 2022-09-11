package com.example.flightbooking.fragments.home.flights.ticketpreview;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.HashMap;


public class TicketPreviewFragmentView {

    private Context context;
    private LinearLayout container;
    private TableLayout table;
    private HashMap<String, TicketPreviewViews> flights_info_views = new HashMap<>();

    public TicketPreviewFragmentView(Context ctx){
        this.context = ctx;
    }

    public LinearLayout getContainer(){return this.container;}
    public TableLayout getTable(){return this.table;}
    public HashMap<String, TicketPreviewViews> getFlightInfoViews(){return this.flights_info_views;}

    public void setContainer(LinearLayout container){this.container = container;}
    public void setTable(TableLayout table){this.table = table;}
    public void setFlightsInfoViews(HashMap<String, TicketPreviewViews> flights_info_views){this.flights_info_views = flights_info_views;}

    /**
     * Add a TextView caption to TableLayout
     * @param caption
     */
    public void setTvTableCaption(String caption){
        TextView table_caption = new TextView(this.context);
        LinearLayout.LayoutParams linear_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linear_lp.setMargins(0,40,0,0);
        table_caption.setLayoutParams(linear_lp);
        table_caption.setText(caption);
        this.getContainer().addView(table_caption);
    }
}
