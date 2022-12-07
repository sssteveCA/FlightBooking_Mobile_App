package com.example.flightbooking.fragments.home.hotel.images;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageRequest;

import java.util.ArrayList;

public class HotelImagesClient {
    private RequestQueue queue;
    private ImageRequest request;
    private String url;

    public HotelImagesClient(String url){
        this.url = url;
    }

    public String getUrl(){ return this.url; }


}
