package com.example.flightbooking.fragments.subscribe;

import com.example.flightbooking.common.RegEx;
import com.example.flightbooking.models.subscribe.SubscribeFormResponse;

import java.util.Map;

public class SubscribeFragmentModel {

    public interface SubscribeResponse{
        public void subscribeResponse(SubscribeFormResponse response);
        public void subscribeError(String message);
    }

    public SubscribeFragmentModel(){

    }

    /**
     * Check if subscribe data are valid
     * @param data the map with the subscribe data
     * @return
     */
    public boolean validateData(Map<String, String> data){
        String username = data.get("username");
        if(username.equals("") || username.length() > 255)return false;
        String email = data.get("email");
        if(!RegEx.patternMatches(RegEx.PATTERN_EMAIL,email) || email.length() > 255)return false;
        String confirmEmail = data.get("conf_email");
        if(!confirmEmail.equals(email))return false;
        String password = data.get("password");
        if(password.length() < 8)return false;
        String confirmPassword = data.get("conf_password");
        if(!confirmPassword.equals(password))return false;
        return true;
    }
}
