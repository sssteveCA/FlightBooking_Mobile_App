package com.example.flightbooking.fragments.home.hotel;

import android.content.Context;

import com.example.flightbooking.common.Connection;

import java.util.List;

public class HotelFragmentModel {

    public interface GetCountries{
        public void countriesResponse(List<String> countries);
        public void countriesError(String message);
    }

    public interface GetCities{
        public void citiesResponse(List<String> cities);
        public void citiesError(String message);
    }

    public interface GetHotels{
        public void hotelsResponse(List<String> hotels);
        public void hotelsError(String message);
    }

    private Context context;
    private HotelFragmentClient hfc;

    public enum EditTextsDate{
        CKECK_IN,CHECK_OUT
    }

    public HotelFragmentModel(Context ctx){
        this.context = ctx;
        this.hfc = new HotelFragmentClient();
    }

    /***
     *
     *  Check if phone is connected to internet
     * @return boolean
     */
    public boolean getConnectionStatus(){
        return Connection.checkInternet(this.context);
    }
}
