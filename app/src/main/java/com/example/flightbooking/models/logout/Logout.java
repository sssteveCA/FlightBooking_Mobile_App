package com.example.flightbooking.models.logout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Logout {
    @SerializedName("done")
    @Expose
    public boolean done;
    @SerializedName("msg")
    @Expose
    public String msg;
}
