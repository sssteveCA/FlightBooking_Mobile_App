package com.example.flightbooking.fragments.home.flights;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightsFragmentModel {

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
        public void getCountryAirportsError(String message);
    }

    public final static int FLIGHTTYPE_ROUNDTRIP = 0;
    public final static int FLIGHTTYPE_ONEWAY = 1;
    public final static int AIRPORTS_REQUEST_DEPARTURE = 0;
    public final static int AIRPORTS_REQUEST_ARRIVAL = 1;

    private int selected_flight_type;
    private String sel_country; //Countries selected for view airports list
    private FlightsFragmentClient ffc;

    public FlightsFragmentModel(){
        this.selected_flight_type = FLIGHTTYPE_ROUNDTRIP;
        this.ffc = new FlightsFragmentClient();

    }

    public int getSelectedFlightType(){return this.selected_flight_type;}
    public String getSelCountry(){return this.sel_country;}

    public void setSelectedFlightType(int flight_type){
        if(flight_type != FLIGHTTYPE_ROUNDTRIP && flight_type != FLIGHTTYPE_ONEWAY)
            this.selected_flight_type = FLIGHTTYPE_ROUNDTRIP;
        else
            this.selected_flight_type = flight_type;
    }
    public void setSelCountry(String sel_country){this.sel_country = sel_country;}

    /**
     * Perform the HTTP request to get the companies list
     */
    public void getCompanies(GetCompanies gc){
        this.ffc.getFfi().companies().enqueue(new Callback<List<String>>() {
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

    /**
     * Perform the HTTP request to get the countries list
     * @param gc
     */
    public void getCountries(GetCountries gc){
        this.ffc.getFfi().countries().enqueue(new Callback<List<String>>() {
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

    public void getCountryAirports(String country, GetCountryAirports gca){
        this.ffc.getFfi().airports_search(country).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    JsonObject airports = response.body();
                    LinkedList<String> airports_name = new LinkedList<String>();
                    //Loop over the JsonObject to put all keys in LinkedList
                    Set<Map.Entry<String, JsonElement>> entries = airports.entrySet();
                    for(Map.Entry<String, JsonElement> entry: entries){
                        airports_name.add(entry.getKey());
                    }
                    gca.getCountryAirportsResponse(airports_name);
                }
                else{
                    try {
                        String errorBody = response.errorBody().string();
                        gca.getCountryAirportsError(errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                gca.getCountryAirportsError(t.getMessage());
            }
        });
    }
}
