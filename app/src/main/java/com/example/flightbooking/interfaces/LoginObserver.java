package com.example.flightbooking.interfaces;

import com.example.flightbooking.models.login.Auth;

public interface LoginObserver {
    public void onLogin(Auth auth);
    public void onLogout(Auth auth);
}
