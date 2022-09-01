package com.example.flightbooking.fragments.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.flightbooking.R;

import java.util.AbstractMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

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

        return view;
    }

    private Map<String, View> menuItemsMap(View v){
        Map<String, View> homeItems = Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<>("flight_types", (RadioGroup)v.findViewById(R.id.frag_home_rg_flight_types)),
                new AbstractMap.SimpleImmutableEntry<>("companies",(Spinner)v.findViewById(R.id.frag_home_sp_companies)),
                new AbstractMap.SimpleImmutableEntry<>("dep_country", (Spinner)v.findViewById(R.id.frag_home_sp_dep_country)),
                new AbstractMap.SimpleImmutableEntry<>("dep_airport", (Spinner)v.findViewById(R.id.frag_home_sp_dep_airport)),
                new AbstractMap.SimpleImmutableEntry<>("arr_country", (Spinner)v.findViewById(R.id.frag_home_sp_arr_country)),
                new AbstractMap.SimpleImmutableEntry<>("arr_airport", (Spinner)v.findViewById(R.id.frag_home_sp_arr_airport)),
                new AbstractMap.SimpleImmutableEntry<>("out_date", (EditText) v.findViewById(R.id.frag_home_et_out_date)),
                new AbstractMap.SimpleImmutableEntry<>("ret_date", (EditText)v.findViewById(R.id.frag_home_et_ret_date)),
                new AbstractMap.SimpleImmutableEntry<>("adults", (EditText)v.findViewById(R.id.frag_home_et_adults)),
                new AbstractMap.SimpleImmutableEntry<>("teenagers", (EditText)v.findViewById(R.id.frag_home_et_teenagers)),
                new AbstractMap.SimpleImmutableEntry<>("childrens", (EditText)v.findViewById(R.id.frag_home_et_childrens)),
                new AbstractMap.SimpleImmutableEntry<>("newborns", (EditText)v.findViewById(R.id.frag_home_et_newborns))
        );
        return homeItems;
    }
}