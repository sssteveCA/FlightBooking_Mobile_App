package com.example.flightbooking.fragments.home;

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

    public interface GetCountries{
        public void countriesResponse(List<String> countries);
        public void countriesError(String message);
    }

    public interface GetCountryAirports{
        public void getCountryAirportsResponse(List<String> airports);
        public void getCountryAirports(String message);
    }

    public final static int FLIGHTTYPE_ROUNDTRIP = 0;
    public final static int FLIGHTTYPE_ONEWAY = 1;

    private int selected_flight_type;
    private HomeFragmentClient hfc;

    public HomeFragmentModel(){
        this.selected_flight_type = FLIGHTTYPE_ROUNDTRIP;
        this.hfc = new HomeFragmentClient();

    }

    public int getSelectedFlightType(){return this.selected_flight_type;}

    public void setSelectedFlightType(int flight_type){
        if(flight_type != FLIGHTTYPE_ROUNDTRIP && flight_type != FLIGHTTYPE_ONEWAY)
            this.selected_flight_type = FLIGHTTYPE_ROUNDTRIP;
        else
            this.selected_flight_type = flight_type;
    }

    /**
     * Perform the HTTP request to get the companies list
     */
    public void getCompanies(GetCompanies gc){
        this.hfc.getHfi().companies().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()){
                    List<String> companies = response.body();
                    gc.companiesResponse(companies);
                }
                else{
                    try {
                        String errorBody = response.errorBody().string();
                        gc.companiesError(errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                gc.companiesError(t.getMessage());
            }
        });
    }

    public void getCountries(GetCountries gc){
        this.hfc.getHfi().countries().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if(response.isSuccessful()){
                    List<String> countries = response.body();
                    gc.countriesResponse(countries);
                }
                else{
                    try {
                        String errorBody = response.errorBody().string();
                        gc.countriesError(errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                gc.countriesError(t.getMessage());
            }
        });

    }
}
