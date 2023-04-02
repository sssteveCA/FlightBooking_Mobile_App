package com.example.flightbooking.fragments.contacts;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.Map;

public class ContactsFragmentView {
    private EditText et_name; //Customer's name
    private EditText et_from; //Customer's email address
    private EditText et_subject; //Contacts message subject
    private EditText et_message; //Contacts message body
    private Button bt_send;
    private Button bt_reset;
    private Button bt_back;
    private ProgressBar pb;

    public ContactsFragmentView(Map<String, View> views){
        this.assignViews(views);
    }

    public EditText getEtName(){return this.et_name;}
    public EditText getEtFrom(){return this.et_from;}
    public EditText getEtSubject(){return this.et_subject;}
    public EditText getEtMessage(){return this.et_message;}
    public Button getBtSend(){return this.bt_send;}
    public Button getBtReset(){return this.bt_reset;}
    public Button getBtBack(){return this.bt_back;}
    public ProgressBar getPb(){return this.pb;}

    private void assignViews(Map<String, View> views){
        this.et_name = (EditText) views.get("et_name");
        this.et_from = (EditText) views.get("et_from");
        this.et_subject = (EditText) views.get("et_subject");
        this.et_message = (EditText) views.get("et_message");
        this.bt_send = (Button) views.get("bt_send");
        this.bt_reset = (Button) views.get("bt_reset");
        this.bt_back = (Button) views.get("bt_back");
        this.pb = (ProgressBar) views.get("pb");
    }

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
