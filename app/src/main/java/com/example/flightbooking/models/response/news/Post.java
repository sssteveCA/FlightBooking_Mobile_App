package com.example.flightbooking.models.response.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Post {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("excerpt")
    @Expose
    public String excerpt;
    @SerializedName("content")
    @Expose
    public String content;
    @SerializedName("categories")
    @Expose
    public String categories = null;
    @SerializedName("tags")
    @Expose
    public String tags = null;
    @SerializedName("created_at")
    @Expose
    public String createdAt;
    @SerializedName("updated_at")
    @Expose
    public String updatedAt;
}
