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
     * @param context
     * @param tpfm
     * @param tpfv
     */
    public static void setTable(Context context, TicketPreviewFragmentModel tpfm, TicketPreviewFragmentView tpfv){
        HashMap<String, TicketPreviewTableValues> flights_map = new HashMap<>();
        for(Map.Entry<String, HashMap<String, Object>> flight: tpfm.getFlights().entrySet()){
            //Loop over flights list HashMap
            TicketPreviewTableValues tptv = new TicketPreviewTableValues();
            tpfv.setTvTableCaption(flight.getKey());
            TicketPreviewFragmentMethods.setTableLayout(flight,tpfm,tpfv);
            flights_map.put(flight.getKey(),tptv);
        }//for(Map.Entry<String, HashMap<String, Object>> flight: tpfm.getFlights().entrySet()){
        tpfv.setFlightsInfoViews(flights_map);
    }

    /**
     * Set the Table view and its children
     * @param flight
     */
    public static void setTableLayout(Map.Entry<String, HashMap<String, Object>> flight, TicketPreviewFragmentModel tpfm, TicketPreviewFragmentView tpfv){
        LinearLayout.LayoutParams linear_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linear_lp.setMargins(0,10,0,0);
        tpfv.getTable().setLayoutParams(linear_lp);
        for(Map.Entry<String, Object> flight_info: flight.getValue().entrySet()){
            //Loop over each info of single flight
            TableRow tr = tpfv.flightTableRow(flight_info.getKey(), flight_info.getValue());
            tpfv.getTable().addView(tr);
            tpfm.setTpfvTableValues(flight_info.getKey(), flight_info.getValue());
        }//for(Map.Entry<String, Object> flight_info: flight.getValue().entrySet()){
        tpfv.getLlTable().addView(tpfv.getTable());
    }
}
