package com.example.flightbooking.fragments.home.flights.ticketpreview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
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
    private HashMap<String, TicketPreviewTableValues> flights_info_views = new HashMap<>();
    private Button bt_book, bt_back;

    public TicketPreviewFragmentView(Context ctx, Button bt_book, Button bt_back){
        this.context = ctx;
        this.bt_book = bt_book;
        this.bt_back = bt_back;
    }

    public LinearLayout getContainer(){return this.container;}
    public TableLayout getTable(){return this.table;}
    public HashMap<String, TicketPreviewTableValues> getFlightInfoViews(){return this.flights_info_views;}
    public Button getBtBook(){return this.bt_book;}
    public Button getBtBack(){return this.bt_back;}

    public void setContainer(LinearLayout container){this.container = container;}
    public void setTable(TableLayout table){this.table = table;}
    public void setFlightsInfoViews(HashMap<String, TicketPreviewTableValues> flights_info_views){this.flights_info_views = flights_info_views;}

    /**
     * Add a TextView caption to TableLayout
     * @param caption
     */
    public void setTvTableCaption(String caption){
        TextView table_caption = new TextView(this.context);
        LinearLayout.LayoutParams linear_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linear_lp.setMargins(0,40,0,0);
        table_caption.setLayoutParams(linear_lp);
        table_caption.setText(caption.toUpperCase());
        table_caption.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        table_caption.setGravity(Gravity.CENTER);
        this.getContainer().addView(table_caption);
    }

    /**
     * Set single row to be added to the TableLayout
     * @param flight_info
     * @param tpv
     * @return TableRow view
     */
    public TableRow flightTableRow(Map.Entry<String, Object> flight_info, TicketPreviewTableValues tpv){
        String key = flight_info.getKey();
        String value = flight_info.getValue().toString();
        TableRow tr = new TableRow(this.context);
        TextView tv_row_name = new TextView(this.context);
        tv_row_name.setText(key);
        tv_row_name.setTypeface(null, Typeface.BOLD);
        tv_row_name.setPadding(10,20,10,20);
        tv_row_name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        tr.addView(tv_row_name);
        TextView tv_row_field = new TextView(this.context);
        tv_row_field.setText(value);
        tv_row_field.setPadding(10,20,10,20);
        tv_row_field.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        tr.addView(tv_row_field);
        this.setTpfvTableViews(key,tv_row_field.getText().toString(),tpv);
        return tr;
    }

    /**
     * Assign needed views to TicketPreviewTableValues object
     * @param key
     * @param val
     * @param tpv,
     */
    private void setTpfvTableViews(String key, String val, TicketPreviewTableValues tpv){
        if(key.equalsIgnoreCase("Compagnia aerea"))
            tpv.company_name = val;
        if(key.equalsIgnoreCase("Paese di partenza"))
            tpv.departure_country = val;
        if(key.equalsIgnoreCase("Aereoporto di partenza"))
            tpv.departure_airport = val;
        if(key.equalsIgnoreCase("Data del volo"))
            tpv.flight_date = val;
        if(key.equalsIgnoreCase("Paese di arrivo"))
            tpv.arrival_country = val;
        if(key.equalsIgnoreCase("Aereoporto di arrivo"))
            tpv.arrival_airport = val;
        if(key.equalsIgnoreCase("Adulti"))
            tpv.adults = val;
        if(key.equalsIgnoreCase("Adolescenti"))
            tpv.teenagers = val;
        if(key.equalsIgnoreCase("Bambini"))
            tpv.chidren = val;
        if(key.equalsIgnoreCase("Neonati"))
            tpv.newborns = val;
        if(key.equalsIgnoreCase("Prezzo"))
            tpv.flight_price = val;
    }
}
