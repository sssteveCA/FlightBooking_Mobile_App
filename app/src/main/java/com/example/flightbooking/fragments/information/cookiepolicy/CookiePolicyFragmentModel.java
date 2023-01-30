package com.example.flightbooking.fragments.information.cookiepolicy;

public class CookiePolicyFragmentModel {

    public interface GetCookiePolicyResponse{
        public void getCookiePolicySuccess(String cookie_policy);
        public void getCookiePolicyError(String message);
    }

    private CookiePolicyFragmentClient cpfc;

    public CookiePolicyFragmentModel(){ this.cpfc = new CookiePolicyFragmentClient(); }

    public CookiePolicyFragmentClient getCpfc(){ return this.cpfc; }
}
