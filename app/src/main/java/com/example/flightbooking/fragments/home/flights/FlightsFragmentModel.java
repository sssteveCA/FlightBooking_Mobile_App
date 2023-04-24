package com.example.flightbooking.fragments.home.flights;

import android.content.Context;
import android.util.Log;

import com.example.flightbooking.common.Connection;
import com.example.flightbooking.common.RegEx;
import com.example.flightbooking.models.response.flights.FlightInfo;
import com.example.flightbooking.models.requests.flights.FlightSearch;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightsFragmentModel {

    public interface GetAirportsInfo{
        public void airportsResponse(JsonObject airports);
        public void airportsError(String message);
    }

    public interface GetCompanies{
        public void companiesResponse(List<String> companies);
        public void companiesError(String message);
    }

    public interface GetFlightInfo{
        public void getTicketPreviewResponse(FlightInfo fp);
        public void getTicketPreviewError(String message);
    }

    public enum FlightTypes{
        ROUNDTRIP, ONEWAY
    }

    public enum AirportsRequest{
        DEPARTURE, ARRIVAL
    }

    public enum EditTextsDate{
        OUTDATE, RETDATE
    }

    private Context context;
    private FlightTypes selected_flight_type;
    private String sel_country; //Countries selected for view airports list
    private FlightsFragmentClient ffc;
    private JsonObject airportsInfo = null;

    public FlightsFragmentModel(Context ctx){
        this.context = ctx;
        this.selected_flight_type = FlightTypes.ROUNDTRIP;
        this.ffc = new FlightsFragmentClient();
    }

    public FlightTypes getSelectedFlightType(){return this.selected_flight_type;}
    public String getSelCountry(){return this.sel_country;}
    public JsonObject getAirportsInfo(){return this.airportsInfo;}

    public void setSelectedFlightType(FlightTypes ft){
       this.selected_flight_type = ft;
    }
    public void setSelCountry(String sel_country){this.sel_country = sel_country;}


    /**
     * Check if single property passes the validation
     * @param field
     * @param fs
     * @return
     */
    private boolean flightSearchFieldValid(Field field, FlightSearch fs) throws IllegalAccessException {
        //List of fields that must match date pattern
        ArrayList<String> date_fields = new ArrayList<>(Arrays.asList("oneway_date","roundtrip_start_date","roundtrip_end_date"));
        ArrayList<String> number_fields = new ArrayList<>(Arrays.asList("adults","teenagers","children","newborns"));
        Object field_val = field.get(fs);
        if(field_val != null){
            String val_str = field_val.toString();
            if(val_str != ""){
                //No class properties can be empty
                String field_name = field.getName();
                if(date_fields.contains(field_name)){
                    //Current field looped is date field
                    if(!RegEx.patternMatches(RegEx.PATTERN_DATE,val_str))return false;
                }
                else if(number_fields.contains(field_name)){
                    //Current field looped is number field
                    if(!RegEx.patternMatches(RegEx.PATTERN_NUMBER,val_str))return false;
                }
            }//if(val_str != ""){
            else return false;
        }//if(field_val != null){
        return true;
    }

    /**
     * Check if FlightSearch properties pass the validation
     * @param fs
     * @return
     */
    private boolean flightSearchValid(FlightSearch fs){
        try{
            Class<?> flightsearch = fs.getClass();
            Field[] fields = flightsearch.getDeclaredFields();
            for(Field field: fields){
                if(!this.flightSearchFieldValid(field,fs))return false;
            }//for(Field field: fields){
        }catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Perform the HTTP request to get the flight ticket preview information
     * @param fs
     */
    public void flightTicketPreview(FlightSearch fs,GetFlightInfo gfi){
        boolean valid = this.flightSearchValid(fs);
        if(valid){
            //Provied input data are valid
            this.ffc.getFfi().getFlightPrice(fs).enqueue(new Callback<FlightInfo>() {
                @Override
                public void onResponse(Call<FlightInfo> call, Response<FlightInfo> response) {
                    FlightInfo fi = (FlightInfo)response.body();
                    gfi.getTicketPreviewResponse(fi);
                }

                @Override
                public void onFailure(Call<FlightInfo> call, Throwable t) {
                    gfi.getTicketPreviewError("Errore durante l'esecuzione della richiesta");
                }
            });
        }
        else{
            //One or more inputs are not valid
            gfi.getTicketPreviewError("Uno o più dati non sono nel formato corretto, riprova");
        }
    }

    /**
     * Permorm the HTTP request to get all the available airports details
     * @param gai
     */
    public void getAirportsRequest(GetAirportsInfo gai){
        FlightsFragmentModel this_ffm = this;
        this.ffc.getFfi().airports().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    this_ffm.airportsInfo = response.body();
                    gai.airportsResponse(this_ffm.airportsInfo);
                }
                else{
                    try {
                        String errorBody = response.errorBody().string();
                        gai.airportsError(errorBody);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                gai.airportsError(t.getMessage());
            }
        });
    }

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
     * Get a list with the countries that contain bookable airports
     * @return
     */
    public LinkedList<String> getAirportsCountries(){
        LinkedList<String> countries = new LinkedList<>();
        if(this.airportsInfo != null){
            Set<Map.Entry<String, JsonElement>> entries = this.airportsInfo.entrySet();
            for(Map.Entry<String, JsonElement> entry: entries){
                countries.add(entry.getKey());
            }
        }//if(this.airportsInfo != null){
        return countries;
    }

    /**
     * Get a list of bookable airports in a specific country
     * @param country the country to search the airports
     * @return
     */
    public LinkedList<String> getAirportsOfCountry(String country){
        LinkedList<String> airports = new LinkedList<>();
        if(this.airportsInfo != null){
            if(this.airportsInfo.has(country)){
                JsonObject joAirports = this.airportsInfo.getAsJsonObject(country);
                Set<Map.Entry<String, JsonElement>> entries = joAirports.entrySet();
                for(Map.Entry<String,JsonElement> entry: entries){
                    airports.add(entry.getKey());
                }
            }//if(this.airportsInfo.has(country)){
        }//if(this.airportsInfo != null){
        return airports;
    }
}
