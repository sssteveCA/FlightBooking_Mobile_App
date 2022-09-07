package com.example.flightbooking.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Common used regular expression methods
public class RegEx {

    /**
     * Check if a string match with a regular expression
     * @param regex
     * @param subject
     * @return
     */
    public static boolean patternMatches(String regex, String subject){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(subject);
        return matcher.matches();
    }
}
