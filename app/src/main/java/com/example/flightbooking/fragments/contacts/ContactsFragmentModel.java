package com.example.flightbooking.fragments.contacts;

import com.example.flightbooking.models.requests.contacts.Contacts;
import com.example.flightbooking.models.response.Message;

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
}
