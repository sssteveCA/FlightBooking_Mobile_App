package com.example.flightbooking.fragments.home.flights.ticketpreview;

import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


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

    /**
     * Set single row to be added to the TableLayout
     * @param flight_info
     * @param tpv
     * @return TableRow view
     */
    public TableRow flightTableRow(Map.Entry<String, Object> flight_info, TicketPreviewViews tpv){
        String key = flight_info.getKey();
        String value = flight_info.getValue().toString();
        TableRow tr = new TableRow(this.context);
        TextView tv_row_name = new TextView(this.context);
        tv_row_name.setText(key);
        tv_row_name.setTypeface(null, Typeface.BOLD);
        tr.addView(tv_row_name);
        TextView tv_row_field = new TextView(this.context);
        tv_row_field.setText(value);
        tr.addView(tv_row_field);
        this.setTpfvTableViews(key,tv_row_field,tpv);
        return tr;
    }

    /**
     * Assign needed views to TicketPreviewViews object
     * @param key
     * @param tv
     * @param tpv,
     */
    private void setTpfvTableViews(String key, TextView tv, TicketPreviewViews tpv){
        if(key.equalsIgnoreCase("Compagnia aerea"))
            tpv.setTvCompanyName(tv);
        if(key.equalsIgnoreCase("Paese di partenza"))
            tpv.setTvDepartureCountry(tv);
        if(key.equalsIgnoreCase("Aereoporto di partenza"))
            tpv.setTvDepartureAirport(tv);
        if(key.equalsIgnoreCase("Data del volo"))
            tpv.setTvFlightDate(tv);
        if(key.equalsIgnoreCase("Paese di arrivo"))
            tpv.setTvArrivalCountry(tv);
        if(key.equalsIgnoreCase("Aereoporto di arrivo"))
            tpv.setTvArrivalAirport(tv);
        if(key.equalsIgnoreCase("Adulti"))
            tpv.setTvAdults(tv);
        if(key.equalsIgnoreCase("Adolescenti"))
            tpv.setTvTeenagers(tv);
        if(key.equalsIgnoreCase("Bambini"))
            tpv.setTvChildren(tv);
        if(key.equalsIgnoreCase("Neonati"))
            tpv.setTvNewborns(tv);
        if(key.equalsIgnoreCase("Prezzo"))
            tpv.setTvFlightPrice(tv);
    }
}
