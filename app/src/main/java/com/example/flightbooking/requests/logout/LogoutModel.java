package com.example.flightbooking.requests.logout;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.logout.Logout;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogoutModel {

    public interface LogoutResponse{
        public void logoutResponse(Logout logout);
        public void logoutError(String message);
    }

    private LogoutClient lc;
    private Logout logoutResponse;

    public LogoutModel(){
        this.lc = new LogoutClient();
    }

    public LogoutClient getLc(){return this.lc;}
    public Logout getLogoutResponse(){return this.logoutResponse;}

    /**
     * Execute the user logout HTTP request
     * @param lr listener to get the response
     */
    public void logoutRequest(String token, LogoutResponse lr){
        this.getLc().getLi().logout("Beader "+token).enqueue(new Callback<Logout>() {
            @Override
            public void onResponse(Call<Logout> call, Response<Logout> response) {
                if(response.isSuccessful()){
                    Logout logout = (Logout) response.body();
                    lr.logoutResponse(logout);
                }//if(response.isSuccessful()){
                else{
                    String jsonString = null;
                    String message = "";
                    try {
                        jsonString = response.errorBody().string();
                        JsonElement je = JsonParser.parseString(jsonString);
                        JsonObject jo = je.getAsJsonObject();
                        message = jo.get("msg").getAsString();
                    } catch (IOException e) {
                        e.printStackTrace();
                        message = Globals.ERR_LOGOUT;
                    } finally {
                        lr.logoutError(message);
                    }
                }//else di if(response.isSuccessful()){
            }
            @Override
            public void onFailure(Call<Logout> call, Throwable t) {
                String message = Globals.ERR_LOGOUT;
                lr.logoutError(message);
            }
        });
    }

}
