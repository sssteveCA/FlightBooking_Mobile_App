package com.example.flightbooking.fragments.home.carrental;

import android.content.Context;

import com.example.flightbooking.common.Connection;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CarRentalFragmentModel {

    public interface GetCarRentalInfo{
        public void carRentalInfoResponse(JsonObject carRental);
        public void carRentalInfoError(String message);
    }

    private Context context;
    private CarRentalFragmentClient crfc;
    private JsonObject carRentalInfo = null;

    public CarRentalFragmentModel(Context context){
        this.context = context;
        this.crfc = new CarRentalFragmentClient();
    }

    /**
     *  Check if phone is connected to internet
     * @return boolean
     */
    public boolean getConnectionStatus(){
        return Connection.checkInternet(this.context);
    }
    public JsonObject getCarRentalInfo(){ return this.carRentalInfo; }

    /**
     * Get the selectable age ranges for car rental
     * @return
     */
    public LinkedList<int[]> getAgeRanges(){
        if(this.carRentalInfo != null){
            if(this.carRentalInfo.has("age_ranges")){
                JsonArray ja = this.carRentalInfo.getAsJsonArray("age_ranges");
                LinkedList<int[]> list = new LinkedList<>();
                Iterator<JsonElement> jaIt = ja.iterator();
                while(jaIt.hasNext()){
                    JsonArray subJa = jaIt.next().getAsJsonArray();
                    int[] listEl = {
                            subJa.get(0).getAsInt(), subJa.get(1).getAsInt()
                    };
                    list.add(listEl);
                }
                return list;
            }
        }
        return null;
    }

    /**
     * Retrieve the details about a particular car of a company
     * @param company
     * @param car
     * @return
     */
    public JsonObject getCarDetails(String company,String car){
        if(this.carRentalInfo != null){
            if(this.carRentalInfo.has("companies_data")){
                JsonObject joCd = this.carRentalInfo.getAsJsonObject("companies_data");
                if(joCd.has(company)) {
                    JsonObject joCompany = joCd.getAsJsonObject(company);
                    if (joCompany.has("cars")) {
                        JsonObject cars = joCompany.getAsJsonObject("cars");
                        if (cars.has(car)) {
                            JsonObject carInfo = cars.getAsJsonObject(car);
                            return carInfo;
                        }
                    }//if(joCompany.has("cars")){
                }
            }//if(this.carRentalInfo.has("companies_data")){
        }//if(this.carRentalInfo != null){
        return null;
    }

    /**
     * Perform the HTTP request to get all available cars for rent
     * @param gcri
     */
    public void getCarRentalInfoRequest(GetCarRentalInfo gcri){
        CarRentalFragmentModel this_crfm = this;
        this.crfc.getCrfi().getCarRentalInfo().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if(response.isSuccessful()){
                    this_crfm.carRentalInfo = response.body();
                    gcri.carRentalInfoResponse(this_crfm.carRentalInfo);
                }
                else gcri.carRentalInfoError("Impossibile leggere i dati");
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                gcri.carRentalInfoError("Impossibile leggere i dati");
            }
        });
    }

    /**
     * Get the car rental companies list
     * @return
     */
    public LinkedList<String> getCompanies(){
        if(this.carRentalInfo != null){
            if(this.carRentalInfo.has("companies_data")){
                LinkedList<String> list = new LinkedList<>();
                JsonObject joCd = this.carRentalInfo.getAsJsonObject("companies_data");
                Set<Map.Entry<String, JsonElement>> entries = joCd.entrySet();
                for(Map.Entry<String,JsonElement> entry: entries){
                    list.add(entry.getKey());
                }
                return list;
            }//if(this.carRentalInfo.has("companies_data")){
        }
        return null;
    }

    /**
     * Get the cars offered by a company
     * @param company
     * @return
     */
    public LinkedList<String> getCompanyCars(String company){
        if(this.carRentalInfo != null){
            if(this.carRentalInfo.has("companies_data")){
                JsonObject joCd = this.carRentalInfo.getAsJsonObject("companies_data");
                if(joCd.has(company)){
                    JsonObject joCompany = joCd.getAsJsonObject(company);
                    if(joCompany.has("cars")){
                        LinkedList<String> list = new LinkedList<>();
                        JsonObject cars = joCompany.getAsJsonObject("cars");
                        Set<Map.Entry<String, JsonElement>> entries = cars.entrySet();
                        for(Map.Entry<String, JsonElement> entry : entries){
                            list.add(entry.getKey());
                        }
                        return list;
                    }
                }//if(joCd.has(company)){
            }//if(this.carRentalInfo.has("companies_data")){
        }//if(this.carRentalInfo != null){
        return null;
    }

    /**
     * Get the countries which the car rental service is available
     * @return
     */
    public LinkedList<String> getCountries(){
        if(this.carRentalInfo != null){
            if(this.carRentalInfo.has("available_locations")){
                LinkedList<String> list = new LinkedList<>();
                JsonObject joAl = this.carRentalInfo.getAsJsonObject("available_locations");
                Set<Map.Entry<String, JsonElement>> entries = joAl.entrySet();
                for(Map.Entry<String, JsonElement> entry: entries){
                    list.add(entry.getKey());
                }
                return list;
            }
        }
        return null;
    }

    /**
     * Get the available car rental location of a specific country
     * @param country
     * @return
     */
    public LinkedList<String> getCountryLocations(String country){
        if(this.carRentalInfo != null){
            if(this.carRentalInfo.has("available_locations")){
                JsonObject joAl = this.carRentalInfo.getAsJsonObject("available_locations");
                if(joAl.has(country)){
                    LinkedList<String> list = new LinkedList<>();
                    JsonObject joLocations = joAl.getAsJsonObject(country);
                    Set<Map.Entry<String, JsonElement>> entries = joLocations.entrySet();
                    for(Map.Entry<String, JsonElement> entry: entries){
                        list.add(entry.getKey());
                    }
                    return list;
                }
            }//if(this.carRentalInfo.has("available_locations")){
        }//if(this.carRentalInfo != null){
        return null;
    }
}
