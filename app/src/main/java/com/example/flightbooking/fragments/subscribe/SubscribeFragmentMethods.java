package com.example.flightbooking.fragments.subscribe;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.flightbooking.R;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubscribeFragmentMethods {

    /**
     * Set the subscribe body data
     * @return the Map with the subscribe data
     */
    public static Map<String, String> setSubscribeBody(SubscribeFragmentView sfv){
        return Stream.of(new Object[][]{
                {"username", sfv.getEtUsername().getText().toString()},
                {"email", sfv.getEtEmailAddress().getText().toString()},
                {"conf_email", sfv.getEtEmailAddressConf().getText().toString()},
                {"password", sfv.getEtPassword().getText().toString()},
                {"conf_password", sfv.getEtPasswordConf().getText().toString()}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));
    }

    /**
     * Create a Map with SubscribeFragment view references
     * @param view
     * @return
     */
    public static Map<String, View> subscribeItems(View view){
        return Stream.of(new Object[][]{
        {"et_username", view.findViewById(R.id.frag_subsc_et_username)},
        {"et_email_address", view.findViewById(R.id.frag_subsc_et_email_address)},
        {"et_email_address_conf", view.findViewById(R.id.frag_subsc_et_email_address_conf)},
        {"et_password", view.findViewById(R.id.frag_subsc_et_password)},
        {"et_password_conf", view.findViewById(R.id.frag_subsc_et_password_conf)},
        {"cb_show_pass", view.findViewById(R.id.frag_subsc_cb_show_pass)},
        {"bt_subscribe", view.findViewById(R.id.frag_subsc_bt_subscribe)},
        {"bt_reset", view.findViewById(R.id.frag_subsc_bt_reset)},
        {"bt_back",view.findViewById(R.id.frag_subsc_bt_back)},
        {"pb", view.findViewById(R.id.frag_subsc_pb)},
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (View) data[1]));
    }
}
