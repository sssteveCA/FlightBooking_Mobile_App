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
import android.widget.ProgressBar;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.common.Connection;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.interfaces.LoginObserver;
import com.example.flightbooking.models.requests.flights.BookFlightRequest;
import com.example.flightbooking.models.response.flights.BookFlightResponse;
import com.example.flightbooking.models.response.flights.FlightInfo;
import com.example.flightbooking.models.response.login.Auth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TicketPreviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TicketPreviewFragment extends Fragment implements View.OnClickListener {

    private TicketPreviewFragmentModel tpfm;
    private TicketPreviewFragmentView tpfv;
    private Context context;
    private FragmentChange fc = null;
    private LoginObserver lo = null;
    private Auth auth = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private FlightInfo fi = null;

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
        this.lo = (LoginObserver)activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.fi = (FlightInfo) getArguments().getSerializable("flightinfo");
            this.auth = (Auth) getArguments().getSerializable(Globals.KEY_AUTH);
            //Log.d("TicketPreviewFragment","onCreate FlightInfo flight_type => "+fi.flightType);
            //Log.d("TicketPreviewFragment","onCreate flights HashMap => "+this.tpfm.getFlights());
        }
        this.tpfm = new TicketPreviewFragmentModel(this.fi,this.auth);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket_preview, container, false);
        LinearLayout ll_table = view.findViewById(R.id.frag_tprev_ll_table);
        Button bt_book = view.findViewById(R.id.frag_tprev_bt_book);
        Button bt_back = view.findViewById(R.id.frag_tprev_bt_back);
        ProgressBar pb = view.findViewById(R.id.frag_tprev_pb);
        this.tpfv = new TicketPreviewFragmentView(this.context,ll_table,bt_book,bt_back,pb);
        TicketPreviewFragmentMethods.setTable(this.context,this.tpfm,this.tpfv);
        this.tpfv.getBtBook().setOnClickListener(this);
        this.tpfv.getBtBack().setOnClickListener(this);
        return view;
    }

    /**
     * Execute the request to book the selected flights
     */
    private void bookFlightRequest(){
        if(this.fi != null && Connection.checkInternet(this.context)){
            BookFlightRequest bfr = new BookFlightRequest();
            bfr.sessionId = this.fi.sessionId;
            Auth this_auth = this.auth;
            FragmentChange this_fc = this.fc;
            LoginObserver this_lo = this.lo;
            TicketPreviewFragmentView this_ptfv = this.tpfv;
            this_ptfv.getPb().setVisibility(View.VISIBLE);
            this.tpfm.bookFlightRequest(bfr, new TicketPreviewFragmentModel.BookFlightResponseInterface() {
                @Override
                public void bookFlightResponse(BookFlightResponse bfr) {
                    this_ptfv.getPb().setVisibility(View.INVISIBLE);
                    Log.i("TicketPreviewFragment","bookFlightRequest response done => "+bfr.done);
                    Log.i("TicketPreviewFragment","bookFlightRequest response message => "+bfr.message);
                }
                @Override
                public void bookFlightError() {
                    this_ptfv.getPb().setVisibility(View.INVISIBLE);
                    Bundle bundle = new Bundle();
                    bundle.putString(Globals.KEY_OLDFRAGMENT,FragmentLabels.TICKET_PREVIEW.getLabelName());
                    this_lo.onLogout(FragmentLabels.LOGIN.getLabelName(),bundle);
                }
            });
        }//if(this.fi != null && Connection.checkInternet(this.context)){
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_tprev_bt_book:
                this.bookFlightRequest();
                break;
            case R.id.frag_tprev_bt_back:
                fc.fragmentChange(FragmentLabels.TICKET_PREVIEW.getLabelName(), FragmentLabels.HOME.getLabelName(), true,null);
                break;
        }
    }
}