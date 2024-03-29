package com.example.flightbooking.fragments.subscribe;

import android.util.Log;

import com.example.flightbooking.common.RegEx;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.requests.subscribe.SubscribeFormInputs;
import com.example.flightbooking.models.response.subscribe.SubscribeFormResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubscribeFragmentModel {

    private SubscribeFormInputs sfInputs;

    public interface SubscribeResponse{
        public void subscribeResponse(SubscribeFormResponse response);
        public void subscribeError(String message);
    }

    private SubscribeFragmentClient sfc;

    public SubscribeFragmentModel(){
        this.sfc = new SubscribeFragmentClient();
    }

    /**
     * Execute the user registration HTTP request
     * @param data the user data to create the account
     * @param sr lister to get the response
     */
    public void subscribeRequest(Map<String, String> data, SubscribeResponse sr){
        if(this.validateData(data)){
            this.sfc.getSfi().subscribe(this.sfInputs).enqueue(new Callback<SubscribeFormResponse>() {
                @Override
                public void onResponse(Call<SubscribeFormResponse> call, Response<SubscribeFormResponse> response) {
                    if(response.isSuccessful()){
                        SubscribeFormResponse sfr = (SubscribeFormResponse)response.body();
                        sr.subscribeResponse(sfr);
                    }
                    else{
                        try {
                            String jsonString = response.errorBody().string();
                            JsonElement jsonEl = JsonParser.parseString(jsonString);
                            JsonObject jsonObj = jsonEl.getAsJsonObject();
                            String message = jsonObj.get(Globals.KEY_MESSAGE).getAsString();
                            sr.subscribeError(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                            sr.subscribeError(Globals.ERR_REQUEST);
                        }
                    }
                }
                @Override
                public void onFailure(Call<SubscribeFormResponse> call, Throwable t) {
                    t.printStackTrace();
                    sr.subscribeError(Globals.ERR_REQUEST);
                }
            });
        }//if(this.validateData(data)){
        else{
            sr.subscribeError(Globals.ERR_INVALID_DATA_FORMAT);
        }
    }

    /**
     * Set the body class for subscribe request
     * @return a SubscribeFormInputs instance with the user registration data
     */
    private SubscribeFormInputs setSfi(String username, String email, String password, String confirmPassword){
        SubscribeFormInputs sfi = new SubscribeFormInputs();
        sfi.name = username;
        sfi.email = email;
        sfi.password = password;
        sfi.password_confirmation = confirmPassword;
        return sfi;
    }

    /**
     * Check if subscribe data are valid
     * @param data the map with the subscribe data
     * @return
     */
    private boolean validateData(Map<String, String> data){
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
        this.sfInputs = this.setSfi(username,email,password,confirmPassword);
        return true;
    }

}
