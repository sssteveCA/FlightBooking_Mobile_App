package com.example.flightbooking.fragments.home.flights.ticketpreview;

import android.app.Activity;
import android.content.Context;
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

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.models.response.flights.FlightInfo;

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
            //Log.d("TicketPreviewFragment","onCreate FlightInfo flight_type => "+fi.flightType);
            this.tpfm = new TicketPreviewFragmentModel(this.fi);
            //Log.d("TicketPreviewFragment","onCreate flights HashMap => "+this.tpfm.getFlights());
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
        TicketPreviewFragmentMethods.setTable(this.context,view,this.tpfm,this.tpfv);
        this.tpfv.getBtBook().setOnClickListener(this);
        this.tpfv.getBtBack().setOnClickListener(this);
        return view;
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_tprev_bt_book:
                break;
            case R.id.frag_tprev_bt_back:
                fc.fragmentChange(FragmentLabels.TICKET_PREVIEW.getLabelName(), FragmentLabels.FLIGHTS.getLabelName(), true,null);
                break;
        }
    }
}