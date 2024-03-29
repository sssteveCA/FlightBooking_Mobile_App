package com.example.flightbooking.fragments.home.flights;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.common.Connection;
import com.example.flightbooking.common.Generic;
import com.example.flightbooking.dialogs.DatePicker;
import com.example.flightbooking.dialogs.MessageDialog;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.exception.MissingValuesException;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.response.flights.FlightInfo;
import com.example.flightbooking.models.requests.flights.FlightSearch;
import com.example.flightbooking.models.response.login.Auth;
import com.google.gson.JsonObject;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FlightsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FlightsFragment extends Fragment implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, AdapterView.OnItemSelectedListener, DatePicker.DialogDate {

    private Context context;
    private FlightsFragmentModel ffm;
    private FlightsFragmentView ffv;
    private String initialCountry = "Austria";
    public FragmentChange fc = null;

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
        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
       FlightsFragmentMethods.saveState(this.ffv,outState);
        super.onSaveInstanceState(outState);
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flights, container, false);
        this.ffm = new FlightsFragmentModel(getActivity());
        try {
            this.ffv = new FlightsFragmentView(FlightsFragmentMethods.menuItemsMap(view));
            if(savedInstanceState != null){
                FlightsFragmentMethods.reassignViewsValues(this.ffv,savedInstanceState);
            }
            //Check the roundtrip radio button for default
            this.ffv.getRgFlightTypes().check(R.id.frag_flights_rb_roundtrip);
            FlightsFragmentMethods.setListeners(this.ffv,this);
        } catch (MissingValuesException e) {
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.companiesRequest();
        this.loadAirportsData();
    }

    /**
     * Get the available flight companies list
     */
    private void companiesRequest(){
        FlightsFragmentModel ffm_temp = this.ffm;
        FlightsFragmentView ffv_temp = this.ffv;
        FlightsFragment ff_temp = this;
        ffm_temp.getCompanies(new FlightsFragmentModel.GetCompanies() {
            @Override
            public void companiesResponse(List<String> companies) {
                ArrayAdapter<String> companiesAdapter = Generic.arrayAdapterFromList(ff_temp.context,companies);
                ffv_temp.getSpCompanies().setAdapter(companiesAdapter);
            }
            @Override
            public void companiesError(String message) {
            }
        });
    }

    /**
     * Do the HTTP request to get the preview about some booked flights
     */
    private void flightInfoRequest(){
        Context this_context = this.context;
        FlightsFragment this_ff = this;
        FlightsFragmentView this_ffv = this.ffv;
        FlightSearch fs = FlightsFragmentMethods.setFlightSearchBody(this_ffv);
        this_ffv.getPbSearch().setVisibility(View.VISIBLE);
        this.ffm.flightTicketPreview(fs, new FlightsFragmentModel.GetFlightInfo() {
            @Override
            public void getTicketPreviewResponse(FlightInfo fp) {
                this_ffv.getPbSearch().setVisibility(View.GONE);
                if(fp != null){
                    if(fp.flightType != null && fp.flightType.equals("oneway")){
                    }
                    else if(fp.flightType != null && fp.flightType.equals("roundtrip")){
                    }
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("flightinfo",fp);
                    fc.fragmentChange(FragmentLabels.FLIGHTS.getLabelName(), FragmentLabels.TICKET_PREVIEW.getLabelName(),true,bundle);
                }//if(fp != null){
                else{
                    MessageDialog md = new MessageDialog(this_context,"Errore","Si è verificato un errore durante l'esecuzione della richiesta");
                }
            }
            @Override
            public void getTicketPreviewError(String message) {
                this_ffv.getPbSearch().setVisibility(View.GONE);
                MessageDialog md = new MessageDialog(getActivity(),"Errore",message);
            }
        });
    }


    /**
     * Do the HTTP request to get the full bookable airports info
     */
    private void loadAirportsData(){
        FlightsFragmentModel ffm_temp = this.ffm;
        FlightsFragmentView ffv_temp = this.ffv;
        FlightsFragment ff_temp = this;
        ffm_temp.getAirportsRequest(new FlightsFragmentModel.GetAirportsInfo() {
            @Override
            public void airportsResponse(JsonObject airports) {
                LinkedList<String> countries = ffm_temp.getAirportsCountries();
                ArrayAdapter<String> countriesAdapter = Generic.arrayAdapterFromList(ff_temp.context, countries);
                ffv_temp.getSpDepCountry().setAdapter(countriesAdapter);
                ffv_temp.getSpArrCountry().setAdapter(countriesAdapter);
                String initialCountry = countries.getFirst();
                FlightsFragmentMethods.setAirportsList(ff_temp.context, initialCountry, FlightsFragmentModel.AirportsRequest.DEPARTURE, ffm_temp, ffv_temp);
                FlightsFragmentMethods.setAirportsList(ff_temp.context, initialCountry, FlightsFragmentModel.AirportsRequest.ARRIVAL,ffm_temp,ffv_temp);
            }
            @Override
            public void airportsError(String message) {

            }
        });
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
                if(Connection.checkInternet(this.context)) this.flightInfoRequest();
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
            FlightsFragmentMethods.setAirportsList(this.context,country, flight_type, this.ffm, this.ffv);
        }
        else if(adapterView == this.ffv.getSpArrCountry()){
            //Item selected from country arrival spinner
            FlightsFragmentModel.AirportsRequest flight_type = FlightsFragmentModel.AirportsRequest.ARRIVAL;
            FlightsFragmentMethods.setAirportsList(this.context,country,flight_type,this.ffm,this.ffv);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    //DatePicker.DialogDate
    @Override
    public void getDate(String date, FlightsFragmentModel.EditTextsDate editTextsDate) {
        if(editTextsDate == FlightsFragmentModel.EditTextsDate.OUTDATE)
            this.ffv.getEtOutDate().setText(date);
        else
            this.ffv.getEtRetDate().setText(date);
    }
}