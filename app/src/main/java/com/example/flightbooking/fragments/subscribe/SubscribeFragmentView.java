package com.example.flightbooking.fragments.subscribe;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class SubscribeFragmentView {
    private EditText et_username;
    private EditText et_email_address;
    private EditText et_email_address_conf;
    private EditText et_password;
    private EditText et_password_conf;
    private CheckBox cb_show_pass;
    private Button bt_subscribe;
    private Button bt_reset;

    public SubscribeFragmentView(EditText et_username,EditText et_email_address,EditText et_email_address_conf,EditText et_password,EditText et_password_conf,CheckBox cb_show_pass,Button bt_subscribe,Button bt_reset){
        this.et_username = et_username;
        this.et_email_address = et_email_address;
        this.et_email_address_conf = et_email_address_conf;
        this.et_password = et_password;
        this.et_password_conf = et_password_conf;
        this.cb_show_pass = cb_show_pass;
        this.bt_subscribe = bt_subscribe;
        this.bt_reset = bt_reset;
    }

    public EditText getEtUsername(){return this.et_username;}
    public EditText getEtEmailAddress(){return this.et_email_address;}
    public EditText getEtEmailAddressConf(){return this.et_email_address_conf;}
    public EditText getEtPassword(){return this.et_password;}
    public EditText getEtPasswordConf(){return this.et_password_conf;}
    public CheckBox getCbShowPass(){return this.cb_show_pass;}
    public Button getBtSubscribe(){return this.bt_subscribe;}
    public Button getBtReset(){return this.bt_reset;}
}
