package com.example.flightbooking.fragments.contacts;

import android.widget.Button;
import android.widget.EditText;

public class ContactsFragmentView {
    private EditText et_from; //Customer's email address
    private EditText et_subject; //Contacts message subject
    private EditText et_message; //Contacts message body
    private Button bt_send;
    private Button bt_reset;

    public ContactsFragmentView(EditText et_from,EditText et_subject, EditText et_message, Button bt_send, Button bt_reset){
        this.et_from = et_from;
        this.et_subject = et_subject;
        this.et_message = et_message;
        this.bt_send = bt_send;
        this.bt_reset = bt_reset;
    }
}
