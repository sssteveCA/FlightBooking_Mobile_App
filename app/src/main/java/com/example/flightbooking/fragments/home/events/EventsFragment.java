package com.example.flightbooking.fragments.home.events;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.response.flightevents.GetFlightEventsResponse;
import com.example.flightbooking.models.response.login.Auth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment implements EventsFragmentAdapter.OnItemButtonClick {

    private Auth auth = null;
    private Context context;
    private EventsFragmentModel efm;
    private EventsFragmentView efv;
    private FragmentChange fc;

    public EventsFragment() {
        // Required empty public constructor
        this.efm = new EventsFragmentModel();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.fc = (MainActivity)activity;
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
            this.auth = (Auth) getArguments().getSerializable(Globals.KEY_AUTH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_events, container, false);
        LinearLayout ll = v.findViewById(R.id.frag_events_ll);
        TextView tv_message = ll.findViewById(R.id.frag_events_tv_message);
        GridView gv = ll.findViewById(R.id.frag_events_gv);
        ProgressBar pb = ll.findViewById(R.id.frag_events_pb);
        this.efv = new EventsFragmentView(ll,tv_message,gv,pb);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.eventsRequest();
    }

    /**
     * Get the events associated with a flight
     */
    private void eventsRequest(){
        EventsFragment this_ef = this;
        EventsFragmentView this_efv = this.efv;
        EventsFragmentAdapter.OnItemButtonClick this_oibc = this;
        this_efv.getPb().setVisibility(View.VISIBLE);
        this.efm.getEventsRequest(new EventsFragmentModel.GetEventsResponse() {
            @Override
            public void eventsSuccess(GetFlightEventsResponse gfer) {
                /*Log.i("EventsFragment","eventsRequest eventsSuccess done => "+gfer.done);
                Log.i("EventsFragment","eventsRequest eventsSuccess empty => "+gfer.empty);*/
                this_efv.getPb().setVisibility(View.GONE);
                EventsFragmentAdapter efa = new EventsFragmentAdapter(this_oibc,this_ef.context,R.layout.row_event_item,gfer.list);
                this_efv.getGv().setAdapter(efa);
            }
            @Override
            public void eventsError(String message) {
//                Log.e("EventsFragment", "eventsRequest eventsError => "+message);
                this_efv.getPb().setVisibility(View.GONE);
                this_efv.getTvMessage().setText(message);
            }
        });
    }

    //EventsFragmentAdapter.OnItemButtonClick
    @Override
    public void itemButtonClick(Bundle bundle) {
        //Log.i("EventsFragment","itemButtonClick");
        this.fc.fragmentChange(FragmentLabels.EVENTS.getLabelName(), FragmentLabels.SINGLE_EVENT.getLabelName(), true,bundle);
    }
}