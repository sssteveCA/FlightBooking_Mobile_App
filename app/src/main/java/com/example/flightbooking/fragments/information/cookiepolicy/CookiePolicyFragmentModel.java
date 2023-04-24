package com.example.flightbooking.fragments.information.cookiepolicy;

import android.util.Log;

import com.example.flightbooking.interfaces.Globals;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CookiePolicyFragmentModel {

    public interface GetCookiePolicyResponse{
        public void getCookiePolicySuccess(String cookie_policy);
        public void getCookiePolicyError(String message);
    }

    private CookiePolicyFragmentClient cpfc;

    public CookiePolicyFragmentModel(){ this.cpfc = new CookiePolicyFragmentClient(); }

    public CookiePolicyFragmentClient getCpfc(){ return this.cpfc; }

    /**
     * Execute the HTTP request to get the full cookie policy
     * @param gcpr the listener called when the request returns a response
     */
    public void getCookiePolicyRequest(GetCookiePolicyResponse gcpr){
        this.cpfc.getCpfi().getCookiePolicy().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try{
                    JsonObject jo = response.body();
                    String content = jo.get("cookie").getAsString();
                    gcpr.getCookiePolicySuccess(content);
                }catch(Exception e){
                    gcpr.getCookiePolicyError(Globals.ERR_COOKIE_POLICY);
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
               gcpr.getCookiePolicyError(Globals.ERR_COOKIE_POLICY);
            }
        });
    }
}
