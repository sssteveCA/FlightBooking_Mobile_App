package com.example.flightbooking.fragments.login;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

public class LoginFragmentView {
    private EditText et_username;
    private EditText et_password;
    private CheckBox cb_show_pass;
    private Button bt_login;
    private Button bt_reset;
    private ProgressBar pb;

    public LoginFragmentView(EditText et_username,EditText et_password,CheckBox cb_show_pass,Button bt_login,Button bt_reset, ProgressBar pb){
        this.et_username = et_username;
        this.et_password = et_password;
        this.cb_show_pass = cb_show_pass;
        this.bt_login = bt_login;
        this.bt_reset = bt_reset;
        this.pb = pb;
    }

    public EditText getEtUsername(){return this.et_username;}
    public EditText getEtPassword(){return this.et_password;}
    public CheckBox getCbShowPass(){return this.cb_show_pass;}
    public Button getBtLogin(){return this.bt_login;}
    public Button getBtReset(){return this.bt_reset;}
    public ProgressBar getPb(){return this.pb;}

    /**
     * Clear all Login EditText values
     */
    public void resetAll(){
        this.et_username.setText("");
        this.et_password.setText("");
    }

}
