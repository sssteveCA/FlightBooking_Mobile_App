package com.example.flightbooking.fragments.contacts;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.requests.contacts.Contacts;
import com.example.flightbooking.models.response.Message;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class ContactsFragmentClient {

    public interface ContactsFragmentInterface{
        @POST(Globals.API_ROUTES_PREFIX+"/contacts/sendemail")
        Call<Message> sendEmail(@Body Contacts contacts);

    }

    private Retrofit retrofit;
    public ContactsFragmentInterface cfi;

    public ContactsFragmentClient(){
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.cfi = this.retrofit.create(ContactsFragmentInterface.class);

    }
    public ContactsFragmentInterface getCfi(){return this.cfi;}
}
