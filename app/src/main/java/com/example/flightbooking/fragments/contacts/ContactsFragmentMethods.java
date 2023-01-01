package com.example.flightbooking.fragments.contacts;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactsFragmentMethods {

    /**
     * Set the contacts body data for request
     * @return the Map with the contacts body data
     */
    public static Map<String, String> contactsBody(ContactsFragmentView cfv){
        Map<String, String> body = Stream.of(new String[][]{
                {"name", cfv.getEtName().getText().toString()},
                {"email", cfv.getEtFrom().getText().toString()},
                {"subject", cfv.getEtSubject().getText().toString()},
                {"message", cfv.getEtMessage().getText().toString()}
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
        return body;
    }
}
