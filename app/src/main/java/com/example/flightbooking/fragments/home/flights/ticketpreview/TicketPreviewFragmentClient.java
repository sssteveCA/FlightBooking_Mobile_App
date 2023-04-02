package com.example.flightbooking.fragments.home.flights.ticketpreview;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.requests.flights.BookFlightRequest;
import com.example.flightbooking.models.response.flights.BookFlightResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class TicketPreviewFragmentClient {

    public interface TicketPreviewFragmentInterface{
        @POST(Globals.API_ROUTES_PREFIX+"/bookflight")
        Call<BookFlightResponse> bookflight(@Body BookFlightRequest bk_req);
    }

    private Retrofit retrofit;
    private TicketPreviewFragmentInterface tpfi;

    public TicketPreviewFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.tpfi = this.retrofit.create(TicketPreviewFragmentInterface.class);
    }

    public TicketPreviewFragmentInterface getTpfi(){ return this.tpfi; }
}
