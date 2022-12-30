package com.example.flightbooking.fragments.contacts;

import com.example.flightbooking.common.Connection;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.requests.contacts.Contacts;
import com.example.flightbooking.models.response.Message;

import okhttp3.OkHttpClient;
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

    private OkHttpClient client;
    private Retrofit retrofit;
    public ContactsFragmentInterface cfi;

    public ContactsFragmentClient(){
        //this.client = Connection.clientTimeOptions();
        this.retrofit = new Retrofit.Builder()
                .baseUrl(Globals.BASE_URL)
                //.client(this.client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.cfi = this.retrofit.create(ContactsFragmentInterface.class);

    }
    public ContactsFragmentInterface getCfi(){return this.cfi;}
}
