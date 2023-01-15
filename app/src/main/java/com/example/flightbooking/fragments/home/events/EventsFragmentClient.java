package com.example.flightbooking.fragments.home.events;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.response.flightevents.GetFlightEventsResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class EventsFragmentClient {

    public interface EventsFragmentInterface{
        @GET(Globals.API_ROUTES_PREFIX+"/flightevents")
        Call<GetFlightEventsResponse> flightEvents();
    }

    private Retrofit retrofit;
    private EventsFragmentInterface efi;

    public EventsFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.efi = this.retrofit.create(EventsFragmentInterface.class);
    }

    public EventsFragmentInterface getEfi(){return this.efi;}

}
