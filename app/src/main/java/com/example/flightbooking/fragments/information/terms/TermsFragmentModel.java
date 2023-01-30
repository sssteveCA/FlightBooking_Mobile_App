package com.example.flightbooking.fragments.information.terms;

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
}
