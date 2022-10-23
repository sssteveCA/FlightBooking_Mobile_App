package com.example.flightbooking.fragments.contacts;

import com.example.flightbooking.models.requests.contacts.Contacts;
import com.example.flightbooking.models.response.Message;

import java.util.Map;

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

    public void contactsRequest(){

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
