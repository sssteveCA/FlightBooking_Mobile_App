package com.example.flightbooking.fragments.login;

import java.util.AbstractMap;
import java.util.Map;

public class LoginFragmentMethods {

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
