package com.example.flightbooking.fragments.home.events.singleevent;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.models.response.flightevents.FlightEvent;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SingleEventFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SingleEventFragment extends Fragment implements View.OnClickListener {

    private FlightEvent fe;
    private Bitmap bitmap;
    private SingleEventFragmentView sefv;
    private FragmentChange fc;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SingleEventFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SingleEventFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SingleEventFragment newInstance(String param1, String param2) {
        SingleEventFragment fragment = new SingleEventFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            this.fe = (FlightEvent) getArguments().getSerializable("flightEvent");
            this.bitmap = getArguments().getParcelable("image");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_single_event, container, false);
        Map<String, View> items = SingleEventFragmentMethods.setViews(v);
        this.sefv = new SingleEventFragmentView(items);
        SingleEventFragmentMethods.assignDataToItems(this.sefv,this.fe,this.bitmap);
        this.sefv.getBtBack().setOnClickListener(this);
        return v;
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_single_ev_bt_back:
                this.fc.fragmentChange(FragmentLabels.SINGLE_EVENT.getLabelName(), FragmentLabels.HOME.getLabelName(), true, null);
                break;
        }
    }
}