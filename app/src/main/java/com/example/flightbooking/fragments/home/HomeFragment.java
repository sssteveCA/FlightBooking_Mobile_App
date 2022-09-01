package com.example.flightbooking.fragments.home;

import android.os.Bundle;

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

import com.example.flightbooking.R;
import com.example.flightbooking.exception.MissingValuesException;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private HomeFragmentModel hfm;
    private HomeFragmentView hfv;
    private String initialCountry = "Austria";

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
        this.hfm = new HomeFragmentModel();
        try {
            this.hfv = new HomeFragmentView(this.menuItemsMap(view));
            //Check the roundtrip radio button for default
            this.hfv.getRgFlightTypes().check(R.id.frag_home_rb_roundtrip);
            this.hfv.getRgFlightTypes().setOnCheckedChangeListener(this);
        } catch (MissingValuesException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.companiesRequest();
        this.countriesRequest(this.initialCountry);
    }

    /**
     * Create an ArrayAdapter from list
     * @param items
     * @return
     */
    private ArrayAdapter<String> arrayAdapterFromList(List<String> items){
        Log.i("HomeFragment", "arrayAdapterFromList begin ");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    /**
     * Create a Map with Home Fragment views reference
     * @param v
     * @return
     */
    private Map<String, View> menuItemsMap(View v){
        Map<String, View> homeItems = Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<>("flight_types", (RadioGroup)v.findViewById(R.id.frag_home_rg_flight_types)),
                new AbstractMap.SimpleImmutableEntry<>("companies",(Spinner)v.findViewById(R.id.frag_home_sp_companies)),
                new AbstractMap.SimpleImmutableEntry<>("dep_country", (Spinner)v.findViewById(R.id.frag_home_sp_dep_country)),
                new AbstractMap.SimpleImmutableEntry<>("dep_airport", (Spinner)v.findViewById(R.id.frag_home_sp_dep_airport)),
                new AbstractMap.SimpleImmutableEntry<>("arr_country", (Spinner)v.findViewById(R.id.frag_home_sp_arr_country)),
                new AbstractMap.SimpleImmutableEntry<>("arr_airport", (Spinner)v.findViewById(R.id.frag_home_sp_arr_airport)),
                new AbstractMap.SimpleImmutableEntry<>("out_date", (EditText) v.findViewById(R.id.frag_home_et_out_date)),
                new AbstractMap.SimpleImmutableEntry<>("ret_date_tv", (TextView)v.findViewById(R.id.frag_home_tv_ret_date)),
                new AbstractMap.SimpleImmutableEntry<>("ret_date_et", (EditText)v.findViewById(R.id.frag_home_et_ret_date)),
                new AbstractMap.SimpleImmutableEntry<>("adults", (EditText)v.findViewById(R.id.frag_home_et_adults)),
                new AbstractMap.SimpleImmutableEntry<>("teenagers", (EditText)v.findViewById(R.id.frag_home_et_teenagers)),
                new AbstractMap.SimpleImmutableEntry<>("childrens", (EditText)v.findViewById(R.id.frag_home_et_childrens)),
                new AbstractMap.SimpleImmutableEntry<>("newborns", (EditText)v.findViewById(R.id.frag_home_et_newborns)),
                new AbstractMap.SimpleImmutableEntry<>("search", (Button)v.findViewById(R.id.frag_home_bt_search))
        );
        return homeItems;
    }

    private void airportsRequest(String country, int flight_type){
        Log.i("HomeFragment", "airportsRequest begin");
        HomeFragmentModel hfm_temp = this.hfm;
        HomeFragmentView hfv_temp = this.hfv;
        HomeFragment hf_temp = this;
        Log.i("HomeFragment", "airportsRequest pre request");
        hfm_temp.getCountryAirports(country, new HomeFragmentModel.GetCountryAirports() {
            @Override
            public void getCountryAirportsResponse(List<String> airports) {
                Log.d("HomeFragment","airportsRequest getCountryAirports response => "+airports);
                if(flight_type == HomeFragmentModel.AIRPORTS_REQUEST_DEPARTURE){
                    //edit the spinner with departure airports list
                    ArrayAdapter<String> airportsAdapter = hf_temp.arrayAdapterFromList(airports);
                    hfv_temp.getSpDepAirport().setAdapter(airportsAdapter);
                }
                else{
                    //edit the spinner with arrival airports list
                    ArrayAdapter<String> airportsAdapter = hf_temp.arrayAdapterFromList(airports);
                    hfv_temp.getSpArrAirport().setAdapter(airportsAdapter);
                }
            }
            @Override
            public void getCountryAirportsError(String message) {
                Log.e("HomeFragment","dataRequest getCountryAirports error => "+message);
            }
        });
    }

    private void companiesRequest(){
        Log.i("HomeFragment","companiesRequest begin");
        HomeFragmentModel hfm_temp = this.hfm;
        HomeFragmentView hfv_temp = this.hfv;
        HomeFragment hf_temp = this;
        hfm_temp.getCompanies(new HomeFragmentModel.GetCompanies() {
            @Override
            public void companiesResponse(List<String> companies) {
                Log.d("HomeFragment","companiesRequest getCompanies response => "+companies);
                ArrayAdapter<String> companiesAdapter = hf_temp.arrayAdapterFromList(companies);
                hfv_temp.getSpCompanies().setAdapter(companiesAdapter);
                Log.d("HomeFragment","companiesRequest getCompanies response after setAdapter ");
            }
            @Override
            public void companiesError(String message) {
                Log.e("HomeFragment","dataRequest getCompanies error => "+message);
            }
        });
    }


    /**
     * Get the countries list and airports list for specified country
     * @param country
     */
    private void countriesRequest(String country){
        HomeFragmentModel hfm_temp = this.hfm;
        HomeFragmentView hfv_temp = this.hfv;
        HomeFragment hf_temp = this;
        hfm_temp.getCountries(new HomeFragmentModel.GetCountries() {
            @Override
            public void countriesResponse(List<String> countries) {
                Log.d("HomeFragment","countriesRequest getCountries response => "+countries);
                ArrayAdapter<String> countriesAdapter = hf_temp.arrayAdapterFromList(countries);
                hfv_temp.getSpDepCountry().setAdapter(countriesAdapter);
                hfv_temp.getSpArrCountry().setAdapter(countriesAdapter);
                int index = countries.indexOf(country);
                String country = countries.get(index);
                Log.d("HomeFragment","countriesRequest getCountries country => "+country);
                hf_temp.airportsRequest(country,HomeFragmentModel.AIRPORTS_REQUEST_DEPARTURE);
                hf_temp.airportsRequest(country,HomeFragmentModel.AIRPORTS_REQUEST_ARRIVAL);
            }
            @Override
            public void countriesError(String message) {
                Log.d("HomeFragment","dataRequest getCountries error =>  "+message);
            }
        });
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_home_bt_search:
                break;
        }
    }

    //RadioGroup.OnCheckedChangeListener
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i == R.id.frag_home_rb_oneway){
            //Oneway flight selected
            this.hfm.setSelectedFlightType(HomeFragmentModel.FLIGHTTYPE_ONEWAY);
        }
        else{
            //Roundtrip flight selected
            this.hfm.setSelectedFlightType(HomeFragmentModel.FLIGHTTYPE_ROUNDTRIP);
        }
        this.hfv.setFlightTypeViews(this.hfm.getSelectedFlightType());
    }

}