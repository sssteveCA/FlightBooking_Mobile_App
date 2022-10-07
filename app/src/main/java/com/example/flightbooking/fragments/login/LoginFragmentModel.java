package com.example.flightbooking.fragments.login;

import com.example.flightbooking.models.login.LoginFormInputs;

import java.util.Map;

public class LoginFragmentModel {

    public interface LoginResponse{

    }

    public LoginFragmentModel(){

    }

    /**
     * Check if login data are valid
     * @param data the map with the login data
     * @return
     */
    private boolean validateData(Map<String, String> data){
        String username = data.get("username");
        if(username.equals(""))return false;
        String password = data.get("password");
        if(password.equals(""))return false;
        return true;
    }

    /**
     * Set the body class for login request
     * @return a LoginFormInputs instance with the user login data
     */
    private LoginFormInputs setLfi(String username, String password){
        LoginFormInputs lfi = new LoginFormInputs();
        lfi.username = username;
        lfi.password = password;
        return lfi;
    }
}
