package com.example.flightbooking.fragments.home;

import com.example.flightbooking.common.RequestCallbacks;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragmentModel {

    public interface GetCompanies{
        public void companiesResponse(List<String> companies);
        public void companiesError(String message);
    }

    public final static int FLIGHTTYPE_ROUNDTRIP = 0;
    public final static int FLIGHTTYPE_ONEWAY = 1;

    private int selected_flight_type;
    private HomeFragmentClient hfc;
    public GetCompanies gc = null;

    public HomeFragmentModel(GetCompanies gc){
        this.selected_flight_type = FLIGHTTYPE_ROUNDTRIP;
        this.hfc = new HomeFragmentClient();
        this.gc = gc;
    }

    public int getSelectedFlightType(){return this.selected_flight_type;}

    public void setSelectedFlightType(int flight_type){
        if(flight_type != FLIGHTTYPE_ROUNDTRIP && flight_type != FLIGHTTYPE_ONEWAY)
            this.selected_flight_type = FLIGHTTYPE_ROUNDTRIP;
        else
            this.selected_flight_type = flight_type;
    }

    public void getCompanies(){
        HomeFragmentModel hfm_temp = this;
        this.hfc.getHfi().companies().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()){
                    List<String> companies = response.body();
                    hfm_temp.gc.companiesResponse(companies);
                }
                else{
                    try {
                        String errorBody = response.errorBody().string();
                        hfm_temp.gc.companiesError(errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                hfm_temp.gc.companiesError(t.getMessage());
            }
        });
    }
}
