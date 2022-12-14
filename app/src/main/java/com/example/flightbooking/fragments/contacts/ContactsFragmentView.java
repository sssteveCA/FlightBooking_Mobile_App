package com.example.flightbooking.fragments.contacts;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class ContactsFragmentView {
    private EditText et_name; //Customer's name
    private EditText et_from; //Customer's email address
    private EditText et_subject; //Contacts message subject
    private EditText et_message; //Contacts message body
    private Button bt_send;
    private Button bt_reset;
    private Button bt_back;
    private ProgressBar pb;

    public ContactsFragmentView(EditText et_name,EditText et_from,EditText et_subject, EditText et_message, Button bt_send, Button bt_reset, Button bt_back, ProgressBar pb){
        this.et_name = et_name;
        this.et_from = et_from;
        this.et_subject = et_subject;
        this.et_message = et_message;
        this.bt_send = bt_send;
        this.bt_reset = bt_reset;
        this.bt_back = bt_back;
        this.pb = pb;
    }

    public EditText getEtName(){return this.et_name;}
    public EditText getEtFrom(){return this.et_from;}
    public EditText getEtSubject(){return this.et_subject;}
    public EditText getEtMessage(){return this.et_message;}
    public Button getBtSend(){return this.bt_send;}
    public Button getBtReset(){return this.bt_reset;}
    public Button getBtBack(){return this.bt_back;}
    public ProgressBar getPb(){return this.pb;}

    /**
     * Clear all Contacts EditText values
     */
    public void resetAll(){
        this.et_name.setText("");
        this.et_from.setText("");
        this.et_subject.setText("");
        this.et_message.setText("");
    }
}
