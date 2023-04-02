package com.example.flightbooking.fragments.home.hotel.hotelinfopreview;

import com.example.flightbooking.models.response.hotel.BookHotelResponse;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.requests.hotel.BookHotelRequest;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class HotelInfoPreviewFragmentClient {

    public interface HotelInfoPreviewFragmentInterface{
        @POST(Globals.API_ROUTES_PREFIX+"/bookhotel")
        Call<BookHotelResponse> bookhotel(@Body BookHotelRequest bh_req);
    }

    private Retrofit retrofit;
    private HotelInfoPreviewFragmentInterface hipfi;

    public HotelInfoPreviewFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.hipfi = this.retrofit.create(HotelInfoPreviewFragmentInterface.class);
    }

    public HotelInfoPreviewFragmentInterface getHipfi(){ return this.hipfi; }
}
