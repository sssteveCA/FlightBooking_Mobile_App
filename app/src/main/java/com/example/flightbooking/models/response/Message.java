package com.example.flightbooking.models.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Generic response with message
 */
public class Message {
    @SerializedName("done")
    @Expose
    public boolean done;
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("message")
    @Expose
    public String message;

}
