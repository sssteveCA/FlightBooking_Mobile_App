package com.example.flightbooking.fragments.home.flights;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.flightbooking.R;
import com.example.flightbooking.dialogs.DatePicker;
import com.example.flightbooking.dialogs.MessageDialog;
import com.example.flightbooking.exception.MissingValuesException;
import com.example.flightbooking.models.FlightInfo;
import com.example.flightbooking.models.FlightSearch;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FlightsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlightsFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, DatePicker.DialogDate {

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
        View view = inflater.inflate(R.layout.fragment_flights, container, false);
        this.ffm = new FlightsFragmentModel();
        try {
            this.ffv = new FlightsFragmentView(this.menuItemsMap(view));
            //Check the roundtrip radio button for default
            this.ffv.getRgFlightTypes().check(R.id.frag_flights_rb_roundtrip);
            this.ffv.getRgFlightTypes().setOnCheckedChangeListener(this);
            this.ffv.getSpDepCountry().setOnItemSelectedListener(this);
            this.ffv.getSpArrCountry().setOnItemSelectedListener(this);
            this.ffv.getEtOutDate().setOnClickListener(this);
            this.ffv.getEtRetDate().setOnClickListener(this);
            this.ffv.getBtSearch().setOnClickListener(this);
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
                new AbstractMap.SimpleImmutableEntry<>("flight_types", (RadioGroup)v.findViewById(R.id.frag_flights_rg_flight_types)),
                new AbstractMap.SimpleImmutableEntry<>("companies",(Spinner)v.findViewById(R.id.frag_flights_sp_companies)),
                new AbstractMap.SimpleImmutableEntry<>("dep_country", (Spinner)v.findViewById(R.id.frag_flights_sp_dep_country)),
                new AbstractMap.SimpleImmutableEntry<>("dep_airport", (Spinner)v.findViewById(R.id.frag_flights_sp_dep_airport)),
                new AbstractMap.SimpleImmutableEntry<>("arr_country", (Spinner)v.findViewById(R.id.frag_flights_sp_arr_country)),
                new AbstractMap.SimpleImmutableEntry<>("arr_airport", (Spinner)v.findViewById(R.id.frag_flights_sp_arr_airport)),
                new AbstractMap.SimpleImmutableEntry<>("out_date", (EditText) v.findViewById(R.id.frag_flights_et_out_date)),
                new AbstractMap.SimpleImmutableEntry<>("ret_date_tv", (TextView)v.findViewById(R.id.frag_flights_tv_ret_date)),
                new AbstractMap.SimpleImmutableEntry<>("ret_date_et", (EditText)v.findViewById(R.id.frag_flights_et_ret_date)),
                new AbstractMap.SimpleImmutableEntry<>("adults", (EditText)v.findViewById(R.id.frag_flights_et_adults)),
                new AbstractMap.SimpleImmutableEntry<>("teenagers", (EditText)v.findViewById(R.id.frag_flights_et_teenagers)),
                new AbstractMap.SimpleImmutableEntry<>("childrens", (EditText)v.findViewById(R.id.frag_flights_et_childrens)),
                new AbstractMap.SimpleImmutableEntry<>("newborns", (EditText)v.findViewById(R.id.frag_flights_et_newborns)),
                new AbstractMap.SimpleImmutableEntry<>("search", (Button)v.findViewById(R.id.frag_flights_bt_search))
        );
        return flightsItems;
    }

    private void airportsRequest(String country, FlightsFragmentModel.AirportsRequest ar){
        FlightsFragmentModel ffm_temp = this.ffm;
        FlightsFragmentView ffv_temp = this.ffv;
        FlightsFragment ff_temp = this;
        ffm_temp.getCountryAirports(country, new FlightsFragmentModel.GetCountryAirports() {
            @Override
            public void getCountryAirportsResponse(List<String> airports) {
                Log.d("FlightsFragment","airportsRequest getCountryAirports response => "+airports);
                if(ar == FlightsFragmentModel.AirportsRequest.DEPARTURE){
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
                //Log.d("FlightsFragment","companiesRequest getCompanies response => "+companies);
                ArrayAdapter<String> companiesAdapter = ff_temp.arrayAdapterFromList(companies);
                ffv_temp.getSpCompanies().setAdapter(companiesAdapter);
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
                //Log.d("FlightsFragment","countriesRequest getCountries response => "+countries);
                ArrayAdapter<String> countriesAdapter = ff_temp.arrayAdapterFromList(countries);
                ffv_temp.getSpDepCountry().setAdapter(countriesAdapter);
                ffv_temp.getSpArrCountry().setAdapter(countriesAdapter);
                int index = countries.indexOf(country);
                String country = countries.get(index);
                //Log.d("FlightsFragment","countriesRequest getCountries country => "+country);
                ff_temp.airportsRequest(country,FlightsFragmentModel.AirportsRequest.DEPARTURE);
                ff_temp.airportsRequest(country,FlightsFragmentModel.AirportsRequest.ARRIVAL);
            }
            @Override
            public void countriesError(String message) {
                Log.e("FlightsFragment","dataRequest getCountries error =>  "+message);
            }
        });
    }

    /**
     * Se the body for flightsearch POST request
     * @return
     */
    private FlightSearch setFlightSearchBody(){
        FlightSearch fs = new FlightSearch();
        if(this.ffv.getRgFlightTypes().getCheckedRadioButtonId() == R.id.frag_flights_rb_oneway)
           fs.flight_type = "oneway";
        else
            fs.flight_type = "roundtrip";
        fs.company_name = this.ffv.getSpCompanies().getSelectedItem().toString();
        fs.from_country = this.ffv.getSpDepCountry().getSelectedItem().toString();
        fs.from_airport = this.ffv.getSpDepAirport().getSelectedItem().toString();
        fs.to_country = this.ffv.getSpArrCountry().getSelectedItem().toString();
        fs.to_airport = this.ffv.getSpArrAirport().getSelectedItem().toString();
        if(fs.flight_type == "oneway") {
            fs.oneway_date = this.ffv.getEtOutDate().getText().toString();
        }
        else{
            fs.roundtrip_start_date = this.ffv.getEtOutDate().getText().toString();
            fs.roundtrip_end_date = this.ffv.getEtRetDate().getText().toString();
        }
        fs.adults = Integer.valueOf(this.ffv.getEtAdults().getText().toString());
        fs.teenagers = Integer.valueOf(this.ffv.getEtTeenagers().getText().toString());
        fs.children = Integer.valueOf(this.ffv.getEtChildrens().getText().toString());
        fs.newborns = Integer.valueOf(this.ffv.getEtNewborns().getText().toString());
        return fs;
    }

    /**
     * Show a date picker dialog and pass the date inserted from EditText if exists
     * @param inputDate
     */
    private void showDatePickerDialog(String inputDate, FlightsFragmentModel.EditTextsDate etd){
        DialogFragment df = new DatePicker(inputDate,this, etd);
        df.show(getActivity().getSupportFragmentManager(),"DatePickerDialog");
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_flights_bt_search:
                FlightSearch fs = this.setFlightSearchBody();
                this.ffm.flightTicketPreview(fs, new FlightsFragmentModel.GetFlightInfo() {
                    @Override
                    public void getTicketPreviewResponse(FlightInfo fp) {
                      //Log.i("FlightsFragment","getTicketPreviewResponse");
                      if(fp.flightType != null && fp.flightType.equals("oneway")){
                      }
                      else if(fp.flightType != null && fp.flightType.equals("roundtrip")){
                      }
                    }
                    @Override
                    public void getTicketPreviewError(String message) {
                        MessageDialog md = new MessageDialog(getActivity(),"Errore",message);
                    }
                });
                break;
            case R.id.frag_flights_et_out_date:
                this.showDatePickerDialog(this.ffv.getEtOutDate().getText().toString(), FlightsFragmentModel.EditTextsDate.OUTDATE);
                break;
            case R.id.frag_flights_et_ret_date:
                this.showDatePickerDialog(this.ffv.getEtRetDate().getText().toString(), FlightsFragmentModel.EditTextsDate.RETDATE);
                break;
        }
    }

    //RadioGroup.OnCheckedChangeListener
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        if(i == R.id.frag_flights_rb_oneway){
            //Oneway flight selected
            this.ffm.setSelectedFlightType(FlightsFragmentModel.FlightTypes.ONEWAY);
        }
        else{
            //Roundtrip flight selected
            this.ffm.setSelectedFlightType(FlightsFragmentModel.FlightTypes.ROUNDTRIP);
        }
        this.ffv.setFlightTypeViews(this.ffm.getSelectedFlightType());
    }

    //AdapterView.OnItemSelectedListener
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String country = (String)adapterView.getItemAtPosition(i);
        if(adapterView == this.ffv.getSpDepCountry()){
          //Item selected from country departure spinner
            FlightsFragmentModel.AirportsRequest flight_type = FlightsFragmentModel.AirportsRequest.DEPARTURE;
            this.airportsRequest(country,flight_type);
        }
        else if(adapterView == this.ffv.getSpArrCountry()){
            //Item selected from country arrival spinner
            FlightsFragmentModel.AirportsRequest flight_type = FlightsFragmentModel.AirportsRequest.ARRIVAL;
            this.airportsRequest(country,flight_type);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    //DatePicker.DialogDate
    @Override
    public void getDate(String date, FlightsFragmentModel.EditTextsDate editTextsDate) {
        //Log.d("FlightsFragment","getDate => "+date);
        if(editTextsDate == FlightsFragmentModel.EditTextsDate.OUTDATE)
            this.ffv.getEtOutDate().setText(date);
        else
            this.ffv.getEtRetDate().setText(date);
    }
}