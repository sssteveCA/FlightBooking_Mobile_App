package com.example.flightbooking.fragments.information.terms;

import android.util.Log;

import com.example.flightbooking.interfaces.Globals;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TermsFragmentModel {

    public interface GetTermsResponse{
        public void getTermsSuccess(String terms);
        public void getTermsError(String message);
    }

    private TermsFragmentClient tfc;

    public TermsFragmentModel(){
        this.tfc = new TermsFragmentClient();
    }

    public TermsFragmentClient getTfc(){return this.tfc;}

    /**
     * Execute the HTTP request to get the full terms and conditions
     * @param gtr the listener called when the request returns a response
     */
    public void getTermsRequest(GetTermsResponse gtr){
        this.tfc.getTfi().terms().enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                try{
                    JsonObject jo = response.body();
                    String content = jo.get("terms").getAsString();
                    gtr.getTermsSuccess(content);
                }catch(Exception e){
                    gtr.getTermsError(Globals.ERR_TERMS);
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.e("TermsFragmentModel","getTermsRequest onFailure");
                Log.e("TermsFragmentModel",t.getMessage());
                gtr.getTermsError(Globals.ERR_TERMS);
            }
        });
    }
}
