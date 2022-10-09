package com.example.flightbooking.models.login;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Auth implements Serializable {

    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("logged")
    @Expose
    public Boolean logged;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("user")
    @Expose
    public User user;
}
