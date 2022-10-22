package com.example.flightbooking.interfaces;

import android.os.Bundle;

import com.example.flightbooking.models.login.Auth;

public interface LoginObserver {
    /**
     * When user login with his account
     * @param label the fragment to go after login
     * @param auth_data the data returned from login
     */
    public void onLogin(String label, Bundle auth_data);

    /**
     * When user logout from his session
     * @param label the fragment to go after logout
     * @param auth_data the data returned from logout
     */
    public void onLogout(String label, Bundle auth_data);
}
