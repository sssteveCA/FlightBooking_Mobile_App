package com.example.flightbooking.fragments.home.flights.ticketpreview;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.flightbooking.R;
import com.example.flightbooking.models.FlightInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicketPreviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketPreviewFragment extends Fragment {

    private TicketPreviewFragmentModel tpfm;
    private TicketPreviewFragmentView tpfv;
    private Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private FlightInfo fi;

    public TicketPreviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param fi FlightInfo object
     * @return A new instance of fragment TicketPreviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TicketPreviewFragment newInstance(FlightInfo fi) {
        TicketPreviewFragment fragment = new TicketPreviewFragment();
        Bundle args = new Bundle();
        args.putSerializable("flightinfo",fi);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.fi = (FlightInfo) getArguments().getSerializable("flightinfo");
            Log.d("TicketPreviewFragment","onCreate FlightInfo flight_type => "+fi.flightType);
            this.tpfm = new TicketPreviewFragmentModel(this.fi);
            Log.d("TicketPreviewFragment","onCreate flights HashMap => "+this.tpfm.getFlights());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_preview, container, false);
        return view;
    }

    /**
     * Add a TextView caption to TableLayout
     * @param caption
     */
    private void tvTableCaption(String caption){
        TextView table_caption = new TextView(this.context);
        LinearLayout.LayoutParams linear_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linear_lp.setMargins(0,40,0,0);
        table_caption.setLayoutParams(linear_lp);
        table_caption.setText(caption);
        this.tpfv.getContainer().addView(table_caption);
    }

    /**
     * Set the table with the entry data from previous fragment
     * @param v
     */
    private void setTable(View v){
        this.tpfv = new TicketPreviewFragmentView();
        LinearLayout ll = v.findViewById(R.id.frag_tprev_llayout);
        this.tpfv.setContainer(ll);
        for(Map.Entry<String, HashMap<String, Object>> flight: this.tpfm.getFlights().entrySet()){
            //Loop over flights list HashMap
            this.tvTableCaption(flight.getKey());
            TableLayout tl = new TableLayout(this.context);
            this.tpfv.setTable(tl);
            LinearLayout.LayoutParams linear_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            linear_lp.setMargins(0,10,0,0);
            tl.setLayoutParams(linear_lp);
            for(Map.Entry<String, Object> flight_info: flight.getValue().entrySet()){
                //Loop over each info of single flight
                TableRow tr = this.flightTableRow(flight_info);
                this.tpfv.getTable().addView(tr);
            }//for(Map.Entry<String, Object> flight_info: flight.getValue().entrySet()){

            this.tpfv.getContainer().addView(this.tpfv.getTable());
        }//for(Map.Entry<String, HashMap<String, Object>> flight: this.tpfm.getFlights().entrySet()){
    }

    /**
     * Set single row to be added to the TableLayout
     * @param flight_info
     * @return TableRow view
     */
    private TableRow flightTableRow(Map.Entry<String, Object> flight_info){
        TableRow tr = new TableRow(this.context);
        TextView tv_row_name = new TextView(this.context);
        tv_row_name.setText(flight_info.getKey());
        tv_row_name.setTypeface(null,Typeface.BOLD);
        tr.addView(tv_row_name);
        TextView tv_row_field = new TextView(this.context);
        tv_row_field.setText(flight_info.getValue().toString());
        tr.addView(tv_row_field);
        return tr;
    }

    /**
     * Assign needed views to TicketPreviewViews object
     * @param key
     * @param tv
     * @param tpv,
     * @param fi_views
     */
    private void setTpfvTableViews(String key, TextView tv, TicketPreviewViews tpv, HashMap<String, TicketPreviewViews> fi_views){
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