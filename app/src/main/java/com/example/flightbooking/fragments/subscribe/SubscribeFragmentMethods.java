package com.example.flightbooking.fragments.subscribe;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SubscribeFragmentMethods {

    /**
     * Set the subscribe body data
     * @return the Map with the subscribe data
     */
    public static Map<String, String> setSubscribeBody(SubscribeFragmentView sfv){
        Map<String, String> subscribeValues = Stream.of(new Object[][]{
                {"username", sfv.getEtUsername().getText().toString()},
                {"email", sfv.getEtEmailAddress().getText().toString()},
                {"conf_email", sfv.getEtEmailAddressConf().getText().toString()},
                {"password", sfv.getEtPassword().getText().toString()},
                {"conf_password", sfv.getEtPasswordConf().getText().toString()}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));
        return subscribeValues;
    }
}
