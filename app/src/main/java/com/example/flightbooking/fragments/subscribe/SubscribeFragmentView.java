package com.example.flightbooking.fragments.subscribe;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.Map;

public class SubscribeFragmentView {
    private EditText et_username;
    private EditText et_email_address;
    private EditText et_email_address_conf;
    private EditText et_password;
    private EditText et_password_conf;
    private CheckBox cb_show_pass;
    private Button bt_subscribe;
    private Button bt_reset;
    private Button bt_back;
    private ProgressBar pb;

    public SubscribeFragmentView(Map<String, View> items){
        this.assignValues(items);
    }

    public EditText getEtUsername(){return this.et_username;}
    public EditText getEtEmailAddress(){return this.et_email_address;}
    public EditText getEtEmailAddressConf(){return this.et_email_address_conf;}
    public EditText getEtPassword(){return this.et_password;}
    public EditText getEtPasswordConf(){return this.et_password_conf;}
    public CheckBox getCbShowPass(){return this.cb_show_pass;}
    public Button getBtSubscribe(){return this.bt_subscribe;}
    public Button getBtReset(){return this.bt_reset;}
    public Button getBtBack(){return this.bt_back;}
    public ProgressBar getPb(){return this.pb;}

    private void assignValues(Map<String, View> items){
       this.et_username = (EditText) items.get("et_username");
       this.et_email_address = (EditText) items.get("et_email_address");
       this.et_email_address_conf = (EditText) items.get("et_email_address_conf");
       this.et_password = (EditText) items.get("et_password");
       this.et_password_conf = (EditText) items.get("et_password_conf");
       this.cb_show_pass = (CheckBox) items.get("cb_show_pass");
       this.bt_subscribe = (Button) items.get("bt_subscribe");
       this.bt_reset = (Button) items.get("bt_reset");
       this.bt_back = (Button) items.get("bt_back");
       this.pb = (ProgressBar) items.get("pb");
    }

    /**
     * Clear all Subscribe EditText values
     */
    public void resetAll(){
        this.et_username.setText("");
        this.et_email_address.setText("");
        this.et_email_address_conf.setText("");
        this.et_password.setText("");
        this.et_password_conf.setText("");
    }
}
