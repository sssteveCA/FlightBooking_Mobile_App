package com.example.flightbooking.interfaces;

/**
 * Values used throughtout the application
 */
public interface Globals {
    //String BASE_URL = "http://ec2-3-12-208-246.us-east-2.compute.amazonaws.com/"; //Base URL which this app does requests
    String BASE_URL = "http://192.168.0.23:8000"; //Base URL which this app does requests
    String API_ROUTES_PREFIX = "/api/v1";
    String IMG_FOLDER = "/img";
    String HOTEL_IMG_FOLDER = Globals.IMG_FOLDER+"/hotels";

    //keys
    String KEY_DONE = "done";
    String KEY_EMPTY = "empty";
    String KEY_MESSAGE = "message";

    //Messages
    String ERR_CONTACTS = "Siamo spiacenti, si è verificato un errore durante l'invio della mail";
    String ERR_NEWS = "Errore durante il caricamento delle notizie";
    String ERR_INVALID_DATA_FORMAT = "Uno o più dati inseriti non hanno un formato valido, riprova";
    String ERR_LOGOUT = "Si è verificato un errore durante la disconnessione dell'account";
    String ERR_REQUEST = "Siamo spiacenti, si è verificato un errore durante l'esecuzione della richiesta";

}
