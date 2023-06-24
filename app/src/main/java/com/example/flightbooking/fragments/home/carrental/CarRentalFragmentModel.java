package com.example.flightbooking.fragments.home.carrental;

import android.content.Context;

import com.example.flightbooking.common.Connection;
import com.google.gson.JsonObject;

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
}
