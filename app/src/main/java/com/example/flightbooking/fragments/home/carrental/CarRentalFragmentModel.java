package com.example.flightbooking.fragments.home.carrental;

import android.content.Context;

import com.example.flightbooking.common.Connection;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

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
}
