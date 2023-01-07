package com.example.flightbooking.fragments.news;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.response.news.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class NewsFragmentClient {

    public interface NewsFragmentInterface{
        @GET(Globals.API_ROUTES_PREFIX+"/")
        Call<List<Post>> getPosts();
    }

    private Retrofit retrofit;
    private NewsFragmentInterface nfi;

    public NewsFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.nfi = this.retrofit.create(NewsFragmentInterface.class);
    }

    public NewsFragmentInterface getNfi(){return this.nfi;}

}
