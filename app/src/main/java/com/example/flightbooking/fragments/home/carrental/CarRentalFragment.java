package com.example.flightbooking.fragments.home.carrental;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.flightbooking.R;
import com.example.flightbooking.common.Generic;
import com.example.flightbooking.exception.MissingValuesException;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.response.login.Auth;
import com.google.gson.JsonObject;

import java.util.LinkedList;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarRentalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarRentalFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private CarRentalFragmentModel crfm;
    private CarRentalFragmentView crfv;
    private Context context;

    public CarRentalFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CarRentalFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CarRentalFragment newInstance(String param1, String param2) {
        CarRentalFragment fragment = new CarRentalFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.crfm = new CarRentalFragmentModel(this.context);
        View v = inflater.inflate(R.layout.fragment_car_rental, container, false);
        Map<String,View> items = CarRentalFragmentMethods.carRentalItems(v);
        try {
            this.crfv = new CarRentalFragmentView(items);
        } catch (MissingValuesException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.loadCarRentalData();
    }

    /**
     * Do the HTTP request to get the rentable cars info
     */
    private void loadCarRentalData(){
        CarRentalFragment this_crf = this;
        this_crf.crfm.getCarRentalInfoRequest(new CarRentalFragmentModel.GetCarRentalInfo() {
            @Override
            public void carRentalInfoResponse(JsonObject carRental) {
                CarRentalFragmentMethods.setSpinnerAdapters(this_crf.context, this_crf.crfm,this_crf.crfv);
                CarRentalFragmentMethods.setViewListeners(this_crf,this_crf.crfv);
            }

            @Override
            public void carRentalInfoError(String message) {
                Log.i("CarRentalFragment","Error");
            }
        });

    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_cr_bt_go:
                break;
        }
    }

    //AdapterView.OnItemSelectedListener
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (view.getId()){
            case R.id.frag_cr_sp_rent_company:
                break;
            case R.id.frag_cr_sp_car:
                break;
            case R.id.frag_cr_sp_pickup_country:
                break;
            case R.id.frag_cr_sp_pickup_location:
                break;
            case R.id.frag_cr_sp_delivery_country:
                break;
            case R.id.frag_cr_sp_delivery_location:
                break;
            case R.id.frag_cr_sp_age_band:
                break;
        }
    }

    //AdapterView.OnItemSelectedListener
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}