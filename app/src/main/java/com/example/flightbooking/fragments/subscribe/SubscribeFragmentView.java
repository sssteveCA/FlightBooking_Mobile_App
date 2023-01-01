package com.example.flightbooking.fragments.subscribe;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

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

    public SubscribeFragmentView(EditText et_username,EditText et_email_address,EditText et_email_address_conf,EditText et_password,EditText et_password_conf,CheckBox cb_show_pass,Button bt_subscribe,Button bt_reset, Button bt_back, ProgressBar pb){
        this.et_username = et_username;
        this.et_email_address = et_email_address;
        this.et_email_address_conf = et_email_address_conf;
        this.et_password = et_password;
        this.et_password_conf = et_password_conf;
        this.cb_show_pass = cb_show_pass;
        this.bt_subscribe = bt_subscribe;
        this.bt_reset = bt_reset;
        this.bt_back = bt_back;
        this.pb = pb;
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
