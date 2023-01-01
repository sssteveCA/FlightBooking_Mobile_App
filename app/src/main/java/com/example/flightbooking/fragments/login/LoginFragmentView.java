package com.example.flightbooking.fragments.login;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

public class LoginFragmentView {
    private EditText et_email;
    private EditText et_password;
    private CheckBox cb_show_pass;
    private Button bt_login;
    private Button bt_reset;
    private Button bt_back;
    private ProgressBar pb;

    public LoginFragmentView(EditText et_email,EditText et_password,CheckBox cb_show_pass,Button bt_login,Button bt_reset, Button bt_back, ProgressBar pb){
        this.et_email = et_email;
        this.et_password = et_password;
        this.cb_show_pass = cb_show_pass;
        this.bt_login = bt_login;
        this.bt_reset = bt_reset;
        this.bt_back = bt_back;
        this.pb = pb;
        this.et_email.setText("user@mail.com");
        this.et_password.setText("12345678");

    }

    public EditText getEtEmail(){return this.et_email;}
    public EditText getEtPassword(){return this.et_password;}
    public CheckBox getCbShowPass(){return this.cb_show_pass;}
    public Button getBtLogin(){return this.bt_login;}
    public Button getBtReset(){return this.bt_reset;}
    public Button getBtBack(){return this.bt_back;}
    public ProgressBar getPb(){return this.pb;}

    /**
     * Clear all Login EditText values
     */
    public void resetAll(){
        this.et_email.setText("");
        this.et_password.setText("");
    }

}
