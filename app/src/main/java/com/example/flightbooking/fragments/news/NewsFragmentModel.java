package com.example.flightbooking.fragments.news;

import com.example.flightbooking.fragments.contacts.ContactsFragmentClient;
import com.example.flightbooking.fragments.contacts.ContactsFragmentModel;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.response.news.GetPosts;
import com.example.flightbooking.models.response.news.Post;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragmentModel {

    public interface GetPostsResponse{
        /**
         * The request has returned the posts list
         * @param posts the posts list
         */
        public void getPostsSuccess(GetPosts posts);

        /**
         * The request has returned an error message
         * @param message the error message
         */
        public void getPostsError(String message);
    }

    private NewsFragmentClient nfc;

    public NewsFragmentModel(){
        this.nfc = new NewsFragmentClient();
    }

    public NewsFragmentClient getNfc(){return this.nfc;}

    /**
     * Execute the HTTP request to get the posts list
     * @param gpr the listener called when the request returns a response
     */
    public void postsRequest(GetPostsResponse gpr){
        this.nfc.getNfi().getPosts().enqueue(new Callback<GetPosts>() {
            @Override
            public void onResponse(Call<GetPosts> call, Response<GetPosts> response) {
                if(response.isSuccessful()){
                    gpr.getPostsSuccess(response.body());
                }
                else{
                    String message = "";
                    try {
                        String jsonString = response.errorBody().string();
                        JsonElement je = JsonParser.parseString(jsonString);
                        JsonObject jo = je.getAsJsonObject();
                        message = jo.get(Globals.KEY_MESSAGE).getAsString();
                        if(message == null)message = Globals.ERR_NEWS;
                    } catch (IOException e) {
                        e.printStackTrace();
                        message = Globals.ERR_NEWS;
                    } finally {
                        gpr.getPostsError(message);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetPosts> call, Throwable t) {
                String message = Globals.ERR_NEWS;
                gpr.getPostsError(message);
            }
        });
    }
}
