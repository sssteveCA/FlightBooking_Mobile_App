package com.example.flightbooking.fragments.login;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.flightbooking.R;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LoginFragmentMethods {

    /**
     * Create a Map with LoginFragment view references
     * @param view
     * @return
     */
    public static Map<String, View> loginItems(View view){
        return Stream.of(new Object[][]{
                {"et_email",view.findViewById(R.id.frag_login_et_email)},
                {"et_password",view.findViewById(R.id.frag_login_et_password)},
                {"cb_show_pass",view.findViewById(R.id.frag_login_cb_show_pass)},
                {"bt_login",view.findViewById(R.id.frag_login_bt_login)},
                {"bt_reset",view.findViewById(R.id.frag_login_bt_login)},
                {"bt_back",view.findViewById(R.id.frag_login_bt_back)},
                {"pb",view.findViewById(R.id.frag_login_pb)}
        }).collect(Collectors.toMap(data -> (String)data[0], data -> (View)data[1]));
    }

    /**
     * Set the login body data
     * @return the Map with the login data
     */
    public static Map<String, String> setLoginBody(LoginFragmentView lfv){
        Map<String, String> loginBody = Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<String, String>("email", lfv.getEtEmail().getText().toString()),
                new AbstractMap.SimpleImmutableEntry<String, String>("password", lfv.getEtPassword().getText().toString())
        );
        return loginBody;
    }
}
