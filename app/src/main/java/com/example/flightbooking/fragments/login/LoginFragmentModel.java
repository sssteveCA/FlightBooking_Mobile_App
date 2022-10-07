package com.example.flightbooking.fragments.login;

import android.provider.Settings;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.login.Auth;
import com.example.flightbooking.models.login.LoginFormInputs;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginFragmentModel {

    public interface LoginResponse{
        public void loginResponse(Auth auth);
        public void loginError(String message);
    }

    private LoginFragmentClient lfc;
    private LoginFormInputs lfi;

    public LoginFragmentModel(){
        this.lfc = new LoginFragmentClient();
    }

    public LoginFragmentClient getLfc(){ return this.lfc; }

    /**
     * Execute the user login HTTP request
     * @param data the user data to login to an existing account
     * @param lr lister to get the response
     */
    public void loginRequest(Map<String, String> data, LoginResponse lr){
        if(this.validateData(data)){
            this.getLfc().getLfi().login(this.lfi).enqueue(new Callback<Auth>() {
                @Override
                public void onResponse(Call<Auth> call, Response<Auth> response) {
                    if(response.isSuccessful()){
                        Auth auth = (Auth) response.body();
                        lr.loginResponse(auth);
                    }//if(response.isSuccessful()){
                    else{
                        try {
                            String jsonString = response.errorBody().string();
                            JsonElement je = JsonParser.parseString(jsonString);
                            JsonObject jo = je.getAsJsonObject();
                            String message = jo.get("message").getAsString();
                            lr.loginError(message);
                        } catch (IOException e) {
                            e.printStackTrace();
                            lr.loginError(Globals.ERR_REQUEST);
                        }
                    }//else di if(response.isSuccessful()){
                }
                @Override
                public void onFailure(Call<Auth> call, Throwable t) {
                    t.printStackTrace();
                    lr.loginError(Globals.ERR_REQUEST);
                }
            });
        }
        else lr.loginError(Globals.ERR_INVALID_DATA_FORMAT);
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
        this.lfi = this.setLfi(username,password);
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
