package com.example.flightbooking.fragments.home.carrental;

import android.content.Context;

import com.example.flightbooking.common.Connection;
import com.google.gson.JsonObject;

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
