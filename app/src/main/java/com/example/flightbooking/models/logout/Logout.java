package com.example.flightbooking.models.logout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logout {
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
