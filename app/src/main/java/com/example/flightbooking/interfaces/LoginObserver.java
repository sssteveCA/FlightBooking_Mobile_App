package com.example.flightbooking.interfaces;

import android.os.Bundle;

import com.example.flightbooking.models.login.Auth;

public interface LoginObserver {
    public void onLogin(Bundle auth_data);
    public void onLogout(Bundle auth_data);
}
