package com.example.flightbooking.fragments.home;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.exception.MissingValuesException;
import com.example.flightbooking.fragments.home.carrental.CarRentalFragment;
import com.example.flightbooking.fragments.home.events.EventsFragment;
import com.example.flightbooking.fragments.home.flights.FlightsFragment;
import com.example.flightbooking.fragments.home.hotel.HotelFragment;
import com.google.android.material.chip.ChipGroup;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements ChipGroup.OnCheckedChangeListener{

    private HomeFragmentModel hfm;
    private HomeFragmentView hfv;
    private MainActivity ma_ref; //Useful for execute fragment operations inside this fragment

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.ma_ref = (MainActivity) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ChipGroup cg_type = view.findViewById(R.id.frag_home_cg_type);
        this.hfv = new HomeFragmentView(this.ma_ref,cg_type);
        this.hfv.getCgType().setSelectionRequired(true);
        this.hfv.getCgType().setSingleSelection(true);
        this.hfv.getCgType().setOnCheckedChangeListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.hfv.updateFragment(new FlightsFragment());
    }

    //ChipGroup.OnCheckedChangeListener
    @Override
    public void onCheckedChanged(ChipGroup group, int checkedId) {
        switch(checkedId){
            case R.id.frag_home_chip_flights:
                //Log.i("HomeFragment","chip flights checked");
                this.hfv.updateFragment(new FlightsFragment());
                break;
            case R.id.frag_home_chip_crent:
                //Log.i("HomeFragment","chip car rental checked");
                this.hfv.updateFragment(new CarRentalFragment());
                break;
            case R.id.frag_home_chip_hotel:
                //Log.i("HomeFragment","chip hotel checked");
                this.hfv.updateFragment(new HotelFragment());
                break;
            case R.id.frag_home_chip_events:
                //Log.i("HomeFragment","chip events checked");
                this.hfv.updateFragment(new EventsFragment());
                break;
        }
    }
}