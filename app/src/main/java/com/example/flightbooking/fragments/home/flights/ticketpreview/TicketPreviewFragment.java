package com.example.flightbooking.fragments.home.flights.ticketpreview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.models.FlightInfo;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicketPreviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketPreviewFragment extends Fragment implements View.OnClickListener {

    private TicketPreviewFragmentModel tpfm;
    private TicketPreviewFragmentView tpfv;
    private Context context;
    public FragmentChange fc = null;

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
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.fc = (MainActivity)activity;
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
        Button bt_book = view.findViewById(R.id.frag_tprev_bt_book);
        Button bt_back = view.findViewById(R.id.frag_tprev_bt_back);
        this.tpfv = new TicketPreviewFragmentView(this.context,bt_book,bt_back);
        this.setTable(view);
        this.tpfv.getBtBook().setOnClickListener(this);
        this.tpfv.getBtBack().setOnClickListener(this);
        return view;
    }

    /**
     * Set the Table view and its children
     * @param flight
     */
    private void setTableLayout(Map.Entry<String, HashMap<String, Object>> flight, TicketPreviewViews tpv){
        TableLayout tl = new TableLayout(this.context);
        this.tpfv.setTable(tl);
        LinearLayout.LayoutParams linear_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        linear_lp.setMargins(0,10,0,0);
        tl.setLayoutParams(linear_lp);
        for(Map.Entry<String, Object> flight_info: flight.getValue().entrySet()){
            //Loop over each info of single flight
            TableRow tr = this.tpfv.flightTableRow(flight_info, tpv);
            this.tpfv.getTable().addView(tr);
        }//for(Map.Entry<String, Object> flight_info: flight.getValue().entrySet()){
        this.tpfv.getContainer().addView(this.tpfv.getTable());
    }

    /**
     * Set the table with the entry data from previous fragment
     * @param v
     */
    private void setTable(View v){
        LinearLayout ll = v.findViewById(R.id.frag_tprev_llayout);
        this.tpfv.setContainer(ll);
        HashMap<String, TicketPreviewViews> flights_map = new HashMap<>();
        for(Map.Entry<String, HashMap<String, Object>> flight: this.tpfm.getFlights().entrySet()){
            //Loop over flights list HashMap
            TicketPreviewViews tpv = new TicketPreviewViews();
            this.tpfv.setTvTableCaption(flight.getKey());
            this.setTableLayout(flight,tpv);
            flights_map.put(flight.getKey(),tpv);
        }//for(Map.Entry<String, HashMap<String, Object>> flight: this.tpfm.getFlights().entrySet()){
        this.tpfv.setFlightsInfoViews(flights_map);
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_tprev_bt_book:
                break;
            case R.id.frag_tprev_bt_back:
                fc.fragmentChange("TicketPreview","Voli",true,null);
                break;
        }
    }
}