package com.example.flightbooking.fragments.home.flights.ticketpreview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.flightbooking.R;

import java.util.HashMap;
import java.util.Map;

public class TicketPreviewFragmentMethods {

    /**
     * Set the table with the entry data from previous fragment
     * @param v
     */
    public static void setTable(Context context, View v, TicketPreviewFragmentModel tpfm, TicketPreviewFragmentView tpfv){
        LinearLayout ll = v.findViewById(R.id.frag_tprev_llayout);
        tpfv.setContainer(ll);
        HashMap<String, TicketPreviewViews> flights_map = new HashMap<>();
        for(Map.Entry<String, HashMap<String, Object>> flight: tpfm.getFlights().entrySet()){
            //Loop over flights list HashMap
            TicketPreviewViews tpv = new TicketPreviewViews();
            tpfv.setTvTableCaption(flight.getKey());
            TicketPreviewFragmentMethods.setTableLayout(context,flight,tpv,tpfv);
            flights_map.put(flight.getKey(),tpv);
        }//for(Map.Entry<String, HashMap<String, Object>> flight: tpfm.getFlights().entrySet()){
        tpfv.setFlightsInfoViews(flights_map);
    }

    /**
     * Set the Table view and its children
     * @param flight
     */
    public static void setTableLayout(Context context, Map.Entry<String, HashMap<String, Object>> flight, TicketPreviewViews tpv, TicketPreviewFragmentView tpfv){
        TableLayout tl = new TableLayout(context);
        tpfv.setTable(tl);
        LinearLayout.LayoutParams linear_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linear_lp.setMargins(0,10,0,0);
        tl.setLayoutParams(linear_lp);
        for(Map.Entry<String, Object> flight_info: flight.getValue().entrySet()){
            //Loop over each info of single flight
            TableRow tr = tpfv.flightTableRow(flight_info, tpv);
            tpfv.getTable().addView(tr);
        }//for(Map.Entry<String, Object> flight_info: flight.getValue().entrySet()){
        tpfv.getContainer().addView(tpfv.getTable());
    }
}
