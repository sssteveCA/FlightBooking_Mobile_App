package com.example.flightbooking.fragments.home.hotel;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.requests.hotel.HotelSearch;
import com.example.flightbooking.fragments.home.flights.hotel.HotelInfo;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public class HotelFragmentClient {

    public interface HotelFragmentInterface{
        @GET(Globals.API_ROUTES_PREFIX+"/hotels")
        Call<JsonObject> hotels();

        @POST(Globals.API_ROUTES_PREFIX+"/hotelprice")
        Call<HotelInfo> hotelPrice(@Body HotelSearch hotelSearch);
    }

    private Retrofit retrofit;
    private HotelFragmentInterface hfi;

    public HotelFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.hfi = this.retrofit.create(HotelFragmentInterface.class);
    }

    public HotelFragmentInterface getHfi(){return this.hfi;}

}
