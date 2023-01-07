package com.example.flightbooking.models.response.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Successful response body of the get posts request
 */
public class GetPosts {
    @SerializedName("done")
    @Expose
    public Boolean done;
    @SerializedName("empty")
    @Expose
    public Boolean empty;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("n_posts")
    @Expose
    public Integer nPosts;
    @SerializedName("posts")
    @Expose
    public List<Post> posts = null;
}
