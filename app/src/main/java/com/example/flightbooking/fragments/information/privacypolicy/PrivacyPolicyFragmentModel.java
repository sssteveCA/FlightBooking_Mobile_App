package com.example.flightbooking.fragments.information.privacypolicy;

public class PrivacyPolicyFragmentModel {

    public interface GetPrivacyPolicyResponse{
        public void getPrivacyPolicySuccess(String privacy_policy);
        public void getPrivacyPolicyError(String message);
    }

    private PrivacyPolicyFragmentClient ppfc;

    public PrivacyPolicyFragmentModel(){this.ppfc = new PrivacyPolicyFragmentClient();}

    public PrivacyPolicyFragmentClient getPpfc(){return this.ppfc;}


}
