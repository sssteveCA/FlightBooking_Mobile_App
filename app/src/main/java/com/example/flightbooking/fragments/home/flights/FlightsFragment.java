package com.example.flightbooking.fragments.home.flights;

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
import com.example.flightbooking.fragments.home.HomeFragment;
import com.example.flightbooking.fragments.home.HomeFragmentModel;
import com.example.flightbooking.fragments.home.HomeFragmentView;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FlightsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlightsFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private FlightsFragmentModel ffm;
    private FlightsFragmentView ffv;
    private String initialCountry = "Austria";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FlightsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FlightsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FlightsFragment newInstance(String param1, String param2) {
        FlightsFragment fragment = new FlightsFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.ffm = new FlightsFragmentModel();
        try {
            this.ffv = new FlightsFragmentView(this.menuItemsMap(view));
            //Check the roundtrip radio button for default
            this.ffv.getRgFlightTypes().check(R.id.frag_home_rb_roundtrip);
            this.ffv.getRgFlightTypes().setOnCheckedChangeListener(this);
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
        Log.i("FlightsFragment", "arrayAdapterFromList begin ");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    /**
     * Create a Map with Flights Fragment views reference
     * @param v
     * @return
     */
    private Map<String, View> menuItemsMap(View v){
        Map<String, View> flightsItems = Map.ofEntries(
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
        return flightsItems;
    }

    private void airportsRequest(String country, int flight_type){
        Log.i("FlightsFragment", "airportsRequest begin");
        FlightsFragmentModel ffm_temp = this.ffm;
        FlightsFragmentView ffv_temp = this.ffv;
        FlightsFragment ff_temp = this;
        Log.i("FlightsFragment", "airportsRequest pre request");
        ffm_temp.getCountryAirports(country, new FlightsFragmentModel.GetCountryAirports() {
            @Override
            public void getCountryAirportsResponse(List<String> airports) {
                Log.d("FlightsFragment","airportsRequest getCountryAirports response => "+airports);
                if(flight_type == FlightsFragmentModel.AIRPORTS_REQUEST_DEPARTURE){
                    //edit the spinner with departure airports list
                    ArrayAdapter<String> airportsAdapter = ff_temp.arrayAdapterFromList(airports);
                    ffv_temp.getSpDepAirport().setAdapter(airportsAdapter);
                }
                else{
                    //edit the spinner with arrival airports list
                    ArrayAdapter<String> airportsAdapter = ff_temp.arrayAdapterFromList(airports);
                    ffv_temp.getSpArrAirport().setAdapter(airportsAdapter);
                }
            }
            @Override
            public void getCountryAirportsError(String message) {
                Log.e("FlightsFragment","dataRequest getCountryAirports error => "+message);
            }
        });
    }

    /**
     * Get the available flight companies list
     */
    private void companiesRequest(){
        Log.i("FlightsFragment","companiesRequest begin");
        FlightsFragmentModel ffm_temp = this.ffm;
        FlightsFragmentView ffv_temp = this.ffv;
        FlightsFragment ff_temp = this;
        ffm_temp.getCompanies(new FlightsFragmentModel.GetCompanies() {
            @Override
            public void companiesResponse(List<String> companies) {
                Log.d("FlightsFragment","companiesRequest getCompanies response => "+companies);
                ArrayAdapter<String> companiesAdapter = ff_temp.arrayAdapterFromList(companies);
                ffv_temp.getSpCompanies().setAdapter(companiesAdapter);
                Log.d("FlightsFragment","companiesRequest getCompanies response after setAdapter ");
            }
            @Override
            public void companiesError(String message) {
                Log.e("FlightsFragment","dataRequest getCompanies error => "+message);
            }
        });
    }

    /**
     * Get the countries list and airports list for specified country
     * @param country
     */
    private void countriesRequest(String country){
        FlightsFragmentModel ffm_temp = this.ffm;
        FlightsFragmentView ffv_temp = this.ffv;
        FlightsFragment ff_temp = this;
        ffm_temp.getCountries(new FlightsFragmentModel.GetCountries() {
            @Override
            public void countriesResponse(List<String> countries) {
                Log.d("FlightsFragment","countriesRequest getCountries response => "+countries);
                ArrayAdapter<String> countriesAdapter = ff_temp.arrayAdapterFromList(countries);
                ffv_temp.getSpDepCountry().setAdapter(countriesAdapter);
                ffv_temp.getSpArrCountry().setAdapter(countriesAdapter);
                int index = countries.indexOf(country);
                String country = countries.get(index);
                Log.d("FlightsFragment","countriesRequest getCountries country => "+country);
                ff_temp.airportsRequest(country,FlightsFragmentModel.AIRPORTS_REQUEST_DEPARTURE);
                ff_temp.airportsRequest(country,FlightsFragmentModel.AIRPORTS_REQUEST_ARRIVAL);
            }
            @Override
            public void countriesError(String message) {
                Log.d("FlightsFragment","dataRequest getCountries error =>  "+message);
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
            this.ffm.setSelectedFlightType(HomeFragmentModel.FLIGHTTYPE_ONEWAY);
        }
        else{
            //Roundtrip flight selected
            this.ffm.setSelectedFlightType(HomeFragmentModel.FLIGHTTYPE_ROUNDTRIP);
        }
        this.ffv.setFlightTypeViews(this.ffm.getSelectedFlightType());
    }
}