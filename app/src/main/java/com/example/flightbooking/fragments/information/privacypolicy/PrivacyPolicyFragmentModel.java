package com.example.flightbooking.fragments.information.privacypolicy;

import android.util.Log;

import com.example.flightbooking.interfaces.Globals;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrivacyPolicyFragmentModel {

    public interface GetPrivacyPolicyResponse{
        public void getPrivacyPolicySuccess(String privacy_policy);
        public void getPrivacyPolicyError(String message);
    }

    private PrivacyPolicyFragmentClient ppfc;

    public PrivacyPolicyFragmentModel(){this.ppfc = new PrivacyPolicyFragmentClient();}

    public PrivacyPolicyFragmentClient getPpfc(){return this.ppfc;}

    /**
     * Execute the HTTP request to get the full privacy policy
     * @param gppr the listener called when the request returns a response
     */
    public void getPrivacyPolicyRequest(GetPrivacyPolicyResponse gppr){
        this.ppfc.getPpfi().getPrivacyPolicy().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try{
                    JsonObject jo = response.body();
                    String content = jo.get("privacy").getAsString();
                    gppr.getPrivacyPolicySuccess(content);
                }catch(Exception e){
                    gppr.getPrivacyPolicyError(Globals.ERR_PRIVACY_POLICY);
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("PrivacyPolicyFragmentModel", "getPrivacyPolicyRequest onFailure");
                Log.e("PrivacyPolicyFragmentModel", t.getMessage());
                gppr.getPrivacyPolicyError(Globals.ERR_PRIVACY_POLICY);
            }
        });
    }

}
