package com.example.flightbooking.fragments.home.hotel;

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
import android.widget.Spinner;

import com.example.flightbooking.R;
import com.example.flightbooking.dialogs.DatePickerHotel;
import com.example.flightbooking.exception.MissingValuesException;
import com.google.gson.JsonObject;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelFragment extends Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener, DatePickerHotel.DialogDateHotel{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;
    private HotelFragmentModel hfm;
    private HotelFragmentView hfv;

    public HotelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HotelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HotelFragment newInstance(String param1, String param2) {
        HotelFragment fragment = new HotelFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_hotel, container, false);
        this.hfm = new HotelFragmentModel(this.context);
        try {
            this.hfv = new HotelFragmentView(this.hotelItems(v));
            this.hfv.getSpCountries().setOnItemSelectedListener(this);
            this.hfv.getSpCities().setOnItemSelectedListener(this);
            this.hfv.getSpHotels().setOnItemSelectedListener(this);
            this.hfv.getEtCkeckIn().setOnClickListener(this);
            this.hfv.getEtCkeckOut().setOnClickListener(this);
            this.hfv.getBtSearch().setOnClickListener(this);
        } catch (MissingValuesException e) {
            Log.e("HotelFragment","MissingValuesException");
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.countriesRequest();
    }

    /**
     * Create a Map with Hotel view references
     * @param v
     * @return
     */
    private Map<String, View> hotelItems(View v){
        Map<String, View> items = Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<>("countries", (Spinner)v.findViewById(R.id.frag_hotel_sp_countries)),
                new AbstractMap.SimpleImmutableEntry<>("cities", (Spinner)v.findViewById(R.id.frag_hotel_sp_cities)),
                new AbstractMap.SimpleImmutableEntry<>("hotels", (Spinner)v.findViewById(R.id.frag_hotel_sp_hotels)),
                new AbstractMap.SimpleImmutableEntry<>("check_in", (EditText)v.findViewById(R.id.frag_hotel_et_check_in)),
                new AbstractMap.SimpleImmutableEntry<>("check_out", (EditText)v.findViewById(R.id.frag_hotel_et_check_out)),
                new AbstractMap.SimpleImmutableEntry<>("rooms", (EditText)v.findViewById(R.id.frag_hotel_et_rooms)),
                new AbstractMap.SimpleImmutableEntry<>("people", (EditText)v.findViewById(R.id.frag_hotel_et_people)),
                new AbstractMap.SimpleImmutableEntry<>("search", (Button)v.findViewById(R.id.frag_hotel_bt_search))
        );
        return items;
    }

    /**
     * Create an ArrayAdapter from list
     * @param items
     * @return
     */
    private ArrayAdapter<String> arrayAdapterFromList(List<String> items){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.context, android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    /**
     * Get the countries that contains bookable hotels
     */
    private void countriesRequest(){
        HotelFragmentModel hfm_temp = this.hfm;
        HotelFragmentView hfv_temp = this.hfv;
        HotelFragment hf_temp = this;
        hfm_temp.getCountries(new HotelFragmentModel.GetCountries() {
            @Override
            public void countriesResponse(List<String> countries) {
                //Log.d("HotelFragment","countriesRequest countries => "+countries);
                ArrayAdapter<String> countriesAdapter = hf_temp.arrayAdapterFromList(countries);
                hfv_temp.getSpCountries().setAdapter(countriesAdapter);
                String country = (String) hfv_temp.getSpCountries().getAdapter().getItem(0);
                hf_temp.citiesRequest(country);
            }
            @Override
            public void countriesError(String message) {
                Log.e("HotelFragment","countriesRequest error => "+message);
            }
        });
    }

    /**
     * Get the cities of a specific country that have bookable hotels
     * @param country
     */
    private void citiesRequest(String country){
        HotelFragmentModel hfm_temp = this.hfm;
        HotelFragmentView hfv_temp = this.hfv;
        HotelFragment hf_temp = this;
        hfm_temp.getCities(country, new HotelFragmentModel.GetCities() {
            @Override
            public void citiesResponse(List<String> cities) {
                //Log.d("HotelFragment","citiesRequest cities => "+cities);
                ArrayAdapter<String> citiesAdapter = hf_temp.arrayAdapterFromList(cities);
                hfv_temp.getSpCities().setAdapter(citiesAdapter);
                String city = (String) hfv_temp.getSpCities().getAdapter().getItem(0);
                hf_temp.hotelsRequest(country,city);
            }
            @Override
            public void citiesError(String message) {
                Log.e("HotelFragment","citiesRequest error => "+message);
            }
        });
    }

    /**
     * Get the hotels available in a city of a particular country
     * @param country
     * @param city
     */
    private void hotelsRequest(String country, String city){
        HotelFragmentModel hfm_temp = this.hfm;
        HotelFragmentView hfv_temp = this.hfv;
        HotelFragment hf_temp = this;
        hfm_temp.getHotels(country, city, new HotelFragmentModel.GetHotels() {
            @Override
            public void hotelsResponse(List<String> hotels) {
                //Log.d("HotelFragment","hotelsRequest hotels => "+hotels);
                ArrayAdapter<String> hotelsAdapter = hf_temp.arrayAdapterFromList(hotels);
                hfv_temp.getSpHotels().setAdapter(hotelsAdapter);
            }
            @Override
            public void hotelsError(String message) {
                Log.e("HotelFragment","hotelsRequest error => "+message);
            }
        });
    }

    /**
     * Do the HTTP request to get the full bookable hotels info
     */
    private void loadHotelsData(){
        HotelFragmentModel hfm_temp = this.hfm;
        HotelFragmentView hfv_temp = this.hfv;
        HotelFragment hf_temp = this;
        hfm_temp.getHotelsInfoRequest(new HotelFragmentModel.GetHotelsInfo() {
            @Override
            public void hotelInfoResponse(JsonObject hotels) {
                LinkedList<String> countries = hfm_temp.getHotelsCountries();
                ArrayAdapter<String> countriesAdapter = hf_temp.arrayAdapterFromList(countries);
                hfv_temp.getSpCountries().setAdapter(countriesAdapter);
                String initialCity = countries.getFirst();
                hf_temp.setCitiesList(initialCity);
            }
            @Override
            public void hotelInfoError(String message) {

            }
        });
    }

    /**
     * Set a list of city in the proper dropdown widget
     * @param country the countries of the cities to display
     */
    private void setCitiesList(String country){
        LinkedList<String> cities = this.hfm.getHotelCitiesOfCountry(country);
        ArrayAdapter<String> citiesAdapter = this.arrayAdapterFromList(cities);
        this.hfv.getSpCities().setAdapter(citiesAdapter);
    }


    //AdapterView.OnItemSelectedListener
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String country = "";
        switch(adapterView.getId()){
            case R.id.frag_hotel_sp_countries:
                country = (String) adapterView.getItemAtPosition(i);
                this.citiesRequest(country);
                break;
            case R.id.frag_hotel_sp_cities:
                country = (String) this.hfv.getSpCountries().getSelectedItem();
                String city = (String) adapterView.getItemAtPosition(i);
                this.hotelsRequest(country,city);
                break;
            case R.id.frag_hotel_sp_hotels:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        HotelFragment this_hf = this;
        HotelFragmentView this_hfv = this.hfv;
        switch(view.getId()){
            case R.id.frag_hotel_et_check_in:
                this_hf.showDatePickerDialog(this_hfv.getEtCkeckIn().getText().toString(), HotelFragmentModel.EditTextsDate.CKECK_IN);
                break;
            case R.id.frag_hotel_et_check_out:
                this_hf.showDatePickerDialog(this_hfv.getEtCkeckOut().getText().toString(), HotelFragmentModel.EditTextsDate.CHECK_OUT);
                break;
            case R.id.frag_hotel_bt_search:
                break;
        }
    }

    /**
     * Show a date picker dialog and pass the date inserted from EditText if exists
     * @param inputDate
     * @param etd
     */
    private void showDatePickerDialog(String inputDate, HotelFragmentModel.EditTextsDate etd){
        DialogFragment df = new DatePickerHotel(inputDate,this,etd);
        df.show(getActivity().getSupportFragmentManager(),"DatePickerDialog");
    }

    @Override
    public void getDate(String date, HotelFragmentModel.EditTextsDate editTextsDate) {
        if(editTextsDate == HotelFragmentModel.EditTextsDate.CKECK_IN)
            this.hfv.getEtCkeckIn().setText(date);
        else
            this.hfv.getEtCkeckOut().setText(date);
    }
}