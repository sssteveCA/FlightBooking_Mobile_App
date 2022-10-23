package com.example.flightbooking.fragments.contacts;

import android.util.Log;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.requests.contacts.Contacts;
import com.example.flightbooking.models.response.Message;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContactsFragmentModel {

    public interface ContactsResponse{
        /**
         * Email sent successfully
         * @param message the response
         */
        public void emailSuccess(Message message);

        /**
         * Error while sending the email
         * @param message the error
         */
        public void emailError(String message);
    }

    private ContactsFragmentClient cfc;
    private Contacts contacts;

    public ContactsFragmentModel(){
        this.cfc = new ContactsFragmentClient();
    }

    public ContactsFragmentClient getCfc(){return this.cfc;}

    /**
     * Execute the contacts HTTP request
     * @param data the contacts data for email sending
     * @param cr the listener to get the response
     */
    public void contactsRequest(Map<String, String> data, ContactsResponse cr){
        if(this.validateData(data)){
            this.getCfc().getCfi().sendEmail(this.contacts).enqueue(new Callback<Message>() {
                @Override
                public void onResponse(Call<Message> call, Response<Message> response) {
                    if(response.isSuccessful()){
                        Message message = (Message) response.body();
                        cr.emailSuccess(message);
                    }//if(response.isSuccessful()){
                    else{
                        String message = "";
                        try {
                            String jsonString = response.errorBody().string();
                            JsonElement je = JsonParser.parseString(jsonString);
                            JsonObject jo = je.getAsJsonObject();
                            message = jo.get("message").getAsString();
                            if(message == null) message = Globals.ERR_CONTACTS;
                        } catch (IOException e) {
                            e.printStackTrace();
                            message = Globals.ERR_CONTACTS;
                        } finally{
                            cr.emailError(message);
                        }

                    }// else of if(response.isSuccessful()){
                }
                @Override
                public void onFailure(Call<Message> call, Throwable t) {
                   /* Log.d("ContactsFragmentModel", "contactsRequest onFailure message => "+t.getMessage());
                    Log.d("ContactsFragmentModel", "contactsRequest onFailure request => "+call.request());
                    Log.d("ContactsFragmentModel", "contactsRequest onFailure throwable => "+t.toString());*/
                    String message = Globals.ERR_CONTACTS;
                    cr.emailError(message);
                }
            });
        }//if(this.validateData(data)){
        else cr.emailError(Globals.ERR_INVALID_DATA_FORMAT);
    }

    /**
     * Check if contacts data are valid
     * @param data the map with the contacts data
     * @return true if the data format is valid
     */
    private boolean validateData(Map<String, String> data){
        String name = data.get("name");
        if(name.equals(""))return false;
        String email = data.get("email");
        if(name.equals(""))return false;
        String subject = data.get("subject");
        if(subject.equals(""))return false;
        String message = data.get("message");
        if(message.equals(""))return false;
        this.contacts = this.setContacts(name,email,subject,message);
        return true;
    }

    /**
     * Set the body class for contacts request
     * @return a Contacts instance with the contacts data
     */
    private Contacts setContacts(String name, String email, String subject, String message){
        Contacts contacts = new Contacts();
        contacts.name = name;
        contacts.email = email;
        contacts.subject = subject;
        contacts.message = message;
        return contacts;
    }
}
