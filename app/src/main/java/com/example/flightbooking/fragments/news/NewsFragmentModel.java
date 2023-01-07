package com.example.flightbooking.fragments.news;

import com.example.flightbooking.fragments.contacts.ContactsFragmentClient;
import com.example.flightbooking.fragments.contacts.ContactsFragmentModel;
import com.example.flightbooking.models.response.news.Post;

import java.util.List;

public class NewsFragmentModel {

    public interface GetPostsResponse{
        /**
         * The request has returned the posts list
         * @param posts the posts list
         */
        public void getPostsSuccess(List<Post> posts);

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
}
