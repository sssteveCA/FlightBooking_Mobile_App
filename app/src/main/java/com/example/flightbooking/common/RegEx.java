package com.example.flightbooking.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Common used regular expression methods
public class RegEx {

    public static final String PATTERN_DATE = "^\\d{4}-((0\\d)|(1[0-2]))((?<=01|03|05|07|08|10|12)-(0\\d|1\\d|2\\d|3[01])|(?<=02)-(0\\d|1\\d|2\\d)|(?<=04|06|09|11)-(0\\d|1\\d|2\\d|30))$";
    public static final String PATTERN_EMAIL = "^[a-z][a-z0-9]{4,30}@([a-z]{3,20}\\.){1,5}[a-z]{2,10}$";
    public static final String PATTERN_NUMBER = "^(0|[1-9]\\d*)$";

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
