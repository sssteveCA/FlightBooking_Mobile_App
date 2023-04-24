package com.example.flightbooking.fragments.login;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.util.Map;

public class LoginFragmentView {
    private EditText et_email;
    private EditText et_password;
    private CheckBox cb_show_pass;
    private Button bt_login;
    private Button bt_reset;
    private Button bt_back;
    private ProgressBar pb;

    public LoginFragmentView(Map<String,View> views){
        this.assignViews(views);

    }

    public EditText getEtEmail(){return this.et_email;}
    public EditText getEtPassword(){return this.et_password;}
    public CheckBox getCbShowPass(){return this.cb_show_pass;}
    public Button getBtLogin(){return this.bt_login;}
    public Button getBtReset(){return this.bt_reset;}
    public Button getBtBack(){return this.bt_back;}
    public ProgressBar getPb(){return this.pb;}

    private void assignViews(Map<String, View> views){
        this.et_email = (EditText) views.get("et_email");
        this.et_password = (EditText) views.get("et_password");
        this.cb_show_pass = (CheckBox) views.get("cb_show_pass");
        this.bt_login = (Button) views.get("bt_login");
        this.bt_reset = (Button) views.get("bt_reset");
        this.bt_back = (Button) views.get("bt_back");
        this.pb = (ProgressBar) views.get("pb");
        /*this.et_email.setText("user@mail.com");
        this.et_password.setText("12345678");*/
    }

    /**
     * Clear all Login EditText values
     */
    public void resetAll(){
        this.et_email.setText("");
        this.et_password.setText("");
    }

}
