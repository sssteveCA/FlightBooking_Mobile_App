package com.example.flightbooking.fragments.contacts;

import android.widget.Button;
import android.widget.EditText;

public class ContactsFragmentView {
    private EditText et_name; //Customer's name
    private EditText et_from; //Customer's email address
    private EditText et_subject; //Contacts message subject
    private EditText et_message; //Contacts message body
    private Button bt_send;
    private Button bt_reset;

    public ContactsFragmentView(EditText et_name,EditText et_from,EditText et_subject, EditText et_message, Button bt_send, Button bt_reset){
        this.et_name = et_name;
        this.et_from = et_from;
        this.et_subject = et_subject;
        this.et_message = et_message;
        this.bt_send = bt_send;
        this.bt_reset = bt_reset;
    }

    public EditText getEtName(){return this.et_name;}
    public EditText getEtFrom(){return this.et_from;}
    public EditText getEtSubject(){return this.et_subject;}
    public EditText getEtMessage(){return this.et_message;}
    public Button getBtSend(){return this.bt_send;}
    public Button getBtReset(){return this.bt_reset;}

    /**
     * Clear all Contacts EditText values
     */
    public void resetAll(){
        this.et_from.setText("");
        this.et_subject.setText("");
        this.et_message.setText("");
    }
}
