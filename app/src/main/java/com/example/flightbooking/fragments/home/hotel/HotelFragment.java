package com.example.flightbooking.fragments.home.hotel;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
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
import android.widget.Spinner;
import android.widget.TableLayout;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.common.Generic;
import com.example.flightbooking.dialogs.DatePickerHotel;
import com.example.flightbooking.dialogs.ImagesDialog;
import com.example.flightbooking.dialogs.MessageDialog;
import com.example.flightbooking.exception.MissingValuesException;
import com.example.flightbooking.fragments.home.hotel.images.HotelImagesModel;
import com.google.gson.JsonObject;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
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
    private HotelImagesModel him;
    private MainActivity ma;

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
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.ma = (MainActivity) activity;
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
            this.hfv = new HotelFragmentView(HotelFragmentMethods.hotelItems(v));
            this.hfv.getSpCountries().setOnItemSelectedListener(this);
            this.hfv.getSpCities().setOnItemSelectedListener(this);
            this.hfv.getSpHotels().setOnItemSelectedListener(this);
            this.hfv.getEtCkeckIn().setOnClickListener(this);
            this.hfv.getEtCkeckOut().setOnClickListener(this);
            this.hfv.getBtSearch().setOnClickListener(this);
            this.hfv.getBtShowImages().setOnClickListener(this);
        } catch (MissingValuesException e) {
            Log.e("HotelFragment","MissingValuesException");
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.loadHotelsData();
    }

    /**
     * Perform the HTTP requests to get all hotel images
     * @param country
     * @param city
     * @param hotel
     */
    private void hotelImagesRequests(String country, String city, String hotel){
        //Log.d("HotelFragment","HotelImagesRequests");
        HashMap<String, Object> hotelInfo = this.hfm.getHotelInfo(country,city,hotel);
        //Log.d("HotelFragment","HotelImagesRequests hotelInfo => "+hotelInfo);
        if(!hotelInfo.isEmpty()){
            int nImages = (int)hotelInfo.get("images");
            this.him = new HotelImagesModel(this.context,country,city,hotel,nImages);
            HotelFragment this_hf = this;
            HotelFragmentView this_hfv = this.hfv;
            this_hfv.getPbShowImages().setVisibility(View.VISIBLE);
            this.him.executeRequests(new HotelImagesModel.RequestsFinish() {
                @Override
                public void onFinish(ArrayList<Bitmap> images) {
                    this_hfv.getPbShowImages().setVisibility(View.GONE);
                    //Log.d("HotelFragment","hotelImagesRequests onFinish");
                    if(images.size() > 0){
                        ImagesDialog imagesDialog = new ImagesDialog(this_hf.context,this_hf.ma.getSupportFragmentManager(),"Immagini hotel",images);
                        imagesDialog.displayFullScreen();
                    }//if(images.size() > 0){
                    else{
                        MessageDialog md = new MessageDialog(this_hf.context, "Immagini hotel","Nessuna immagine di questo hotel da mostrare");
                    }
                }
            });
        }//if(!hotelInfo.isEmpty()){
        else{
            MessageDialog md = new MessageDialog(this.context,"Immagini hotel","Errore durente l'esecuzione della richiesta");
        }
    }

    private void hotelInfoRequest(){

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
                ArrayAdapter<String> countriesAdapter = Generic.arrayAdapterFromList(hf_temp.context,countries);
                hfv_temp.getSpCountries().setAdapter(countriesAdapter);
                String initialCity = countries.getFirst();
                //Log.d("HotelFragment","loadHotelsData initialCity => "+initialCity);
                HotelFragmentMethods.setCitiesList(hf_temp.context,initialCity,hfm_temp,hfv_temp);
            }
            @Override
            public void hotelInfoError(String message) {

            }
        });
    }



    //AdapterView.OnItemSelectedListener
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String country = ""; String city = ""; String hotel = "";
        switch(adapterView.getId()){
            case R.id.frag_hotel_sp_countries:
                country = (String) adapterView.getItemAtPosition(i);
                HotelFragmentMethods.setCitiesList(this.context,country,this.hfm,this.hfv);
                break;
            case R.id.frag_hotel_sp_cities:
                country = (String) this.hfv.getSpCountries().getSelectedItem();
                city = (String) adapterView.getItemAtPosition(i);

                HotelFragmentMethods.setHotelsList(this.context,country,city,this.hfm,this.hfv);
                break;
            case R.id.frag_hotel_sp_hotels:
                country = (String) this.hfv.getSpCountries().getSelectedItem();
                city = (String) this.hfv.getSpCities().getSelectedItem();
                hotel = (String) adapterView.getItemAtPosition(i);
                HotelFragmentMethods.setHotelInfoTable(this.context,country,city,hotel,this.hfm,this.hfv);
                break;
            default:
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
                HotelFragmentMethods.showDatePickerDialog(this_hfv.getEtCkeckIn().getText().toString(), this_hf, HotelFragmentModel.EditTextsDate.CKECK_IN, this_hf.ma);
                break;
            case R.id.frag_hotel_et_check_out:
                HotelFragmentMethods.showDatePickerDialog(this_hfv.getEtCkeckOut().getText().toString(), this_hf,HotelFragmentModel.EditTextsDate.CHECK_OUT, this_hf.ma);
                break;
            case R.id.frag_hotel_bt_search:
                break;
            case R.id.frag_hotel_bt_hotel_images:
                String country = (String) this.hfv.getSpCountries().getSelectedItem();
                String city = (String) this.hfv.getSpCities().getSelectedItem();
                String hotel = (String) this.hfv.getSpHotels().getSelectedItem();
                this.hotelImagesRequests(country,city,hotel);
                break;
            default:
                break;
        }
    }

    @Override
    public void getDate(String date, HotelFragmentModel.EditTextsDate editTextsDate) {
        if(editTextsDate == HotelFragmentModel.EditTextsDate.CKECK_IN)
            this.hfv.getEtCkeckIn().setText(date);
        else
            this.hfv.getEtCkeckOut().setText(date);
    }
}