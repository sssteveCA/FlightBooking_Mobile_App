package com.example.flightbooking.common;

//Function called after an HTTP request
public interface RequestCallbacks {

    public void responseSuccess(Object response);
    public void responseFailure(String message);
}
