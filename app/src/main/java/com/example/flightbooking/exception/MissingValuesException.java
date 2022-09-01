package com.example.flightbooking.exception;

/**
 * Thrown when one or more required value missed
 */
public class MissingValuesException extends Exception {

    public MissingValuesException(){
        super();
    }

    public MissingValuesException(String message){
        super(message);
    }

}
