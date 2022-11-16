package com.example.flightbooking.fragments.home.flights;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.response.flights.FlightInfo;
import com.example.flightbooking.models.requests.flights.FlightSearch;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class FlightsFragmentClient
{
    public interface FlightsFragmentInterface{
        @GET(Globals.API_ROUTES_PREFIX+"/airports")
        Call<JsonObject> airports();

        @GET(Globals.API_ROUTES_PREFIX+"/companieslist")
        Call<List<String>> companies();

        @POST(Globals.API_ROUTES_PREFIX+"/flightprice")
        Call<FlightInfo> getFlightPrice(@Body FlightSearch fs);

    }

    private Retrofit retrofit;
    private FlightsFragmentInterface ffi;
    public FlightsFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.ffi = this.retrofit.create(FlightsFragmentInterface.class);
    }

    public FlightsFragmentInterface getFfi(){return this.ffi;}

}
