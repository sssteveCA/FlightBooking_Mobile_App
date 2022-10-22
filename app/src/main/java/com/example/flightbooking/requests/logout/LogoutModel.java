package com.example.flightbooking.requests.logout;

import android.util.Log;

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

    public LogoutModel(String token){
        this.lc = new LogoutClient(token);
    }

    public LogoutClient getLc(){return this.lc;}
    public Logout getLogoutResponse(){return this.logoutResponse;}

    /**
     * Execute the user logout HTTP request
     * @param lr listener to get the response
     */
    public void logoutRequest( LogoutResponse lr){
        this.getLc().getLi().logout().enqueue(new Callback<Logout>() {
            @Override
            public void onResponse(Call<Logout> call, Response<Logout> response) {
                Log.d("LogoutModel", "logoutRequest onResponse headers call => "+call.request().headers());
                Log.d("LogoutModel", "logoutRequest onResponse headers response => "+response.headers());
                if(response.isSuccessful()){
                    Logout logout = (Logout) response.body();
                    Log.d("LogoutModel","logoutRequest onResponse OK => "+logout.message);
                    lr.logoutResponse(logout);
                }//if(response.isSuccessful()){
                else{
                    String jsonString = null;
                    String message = "";
                    try {
                        jsonString = response.errorBody().string();
                        Log.d("LogoutModel","logoutRequest onResponse ERROR jsonString => "+jsonString);
                        JsonElement je = JsonParser.parseString(jsonString);
                        JsonObject jo = je.getAsJsonObject();
                        message = jo.get("message").getAsString();
                        if(message == null) message = Globals.ERR_LOGOUT;
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
                Log.d("LogoutModel", "logoutRequest onFailure message => "+t.getMessage());
                Log.d("LogoutModel", "logoutRequest onFailure throwable => "+t.toString());
                String message = Globals.ERR_LOGOUT;
                lr.logoutError(message);
            }
        });
    }

}
