package com.example.flightbooking.fragments.home.hotel.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class HotelImagesClient {

    public interface HotelImagesListener{
        public void imageResponse(Bitmap image);
        public void imageError(String message);
    }

    private Context context;
    private RequestQueue queue;
    private String url;

    public HotelImagesClient(Context context,String url){
        this.context = context;
        this.url = url;
        this.queue = Volley.newRequestQueue(this.context);
    }

    public String getUrl(){ return this.url; }

    /**
     * Add an image request to queue
     * @param hil the image request response listener
     */
    public void addRequest(HotelImagesListener hil){
        ImageRequest imageRequest = new ImageRequest(this.url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                hil.imageResponse(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hil.imageError(error.getMessage());
            }
        });
    }


}
