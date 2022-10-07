package com.example.flightbooking.fragments.login;

import java.util.Map;

public class LoginFragmentModel {

    public LoginFragmentModel(){

    }

    /**
     * Check if login data are valid
     * @param data the map with the login data
     * @return
     */
    public boolean validateData(Map<String, String> data){
        String username = data.get("username");
        if(username.equals(""))return false;
        String password = data.get("password");
        if(password.equals(""))return false;
        return true;
    }
}
