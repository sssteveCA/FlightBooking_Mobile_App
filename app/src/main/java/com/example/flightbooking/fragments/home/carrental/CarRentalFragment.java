package com.example.flightbooking.fragments.home.carrental;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.flightbooking.R;
import com.example.flightbooking.exception.MissingValuesException;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.response.login.Auth;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CarRentalFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CarRentalFragment extends Fragment implements View.OnClickListener{

    private CarRentalFragmentView crfv;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_car_rental, container, false);
        Map<String,View> items = CarRentalFragmentMethods.carRentalItems(v);
        try {
            this.crfv = new CarRentalFragmentView(items);
        } catch (MissingValuesException e) {
            e.printStackTrace();
        }
        return v;
    }

    /**
     * Do the HTTP request to get the rentable cars info
     */
    private void loadCarRentalData(){

    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_cr_bt_go:
                break;
        }
    }
}