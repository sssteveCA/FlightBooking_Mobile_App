package com.example.flightbooking.interfaces;

import android.provider.Settings;

/**
 * Values used throughtout the application
 */
public interface Globals {
    //String BASE_URL = "http://ec2-3-12-208-246.us-east-2.compute.amazonaws.com/"; //Base URL which this app does requests
    String BASE_URL = "http://192.168.0.24:8000"; //Base URL which this app does requests
    String API_ROUTES_PREFIX = "/api/v1";
    String IMG_FOLDER = "/img";
    String HOTEL_IMG_FOLDER = Globals.IMG_FOLDER+"/hotels";
    String FLIGHT_EVENTS_IMG_FOLDER = Globals.IMG_FOLDER+"/flightevents";

    //keys
    String KEY_AUTH = "auth";
    String KEY_DONE = "done";
    String KEY_EMPTY = "empty";
    String KEY_MESSAGE = "message";
    String KEY_OLDFRAGMENT = "oldfragment";

    //Messages
    String ERR_CONTACTS = "Siamo spiacenti, si è verificato un errore durante l'invio della mail";
    String ERR_COOKIE_POLICY = "Impossibile mostrare la cookie policy";
    String ERR_EVENTS = "Errore durante il caricamento degli eventi";
    String ERR_NEWS = "Errore durante il caricamento delle notizie";
    String ERR_INVALID_DATA_FORMAT = "Uno o più dati inseriti non hanno un formato valido, riprova";
    String ERR_LOGOUT = "Si è verificato un errore durante la disconnessione dell'account";
    String ERR_PRIVACY_POLICY = "Impossibile mostrare la privacy policy";
    String ERR_REQUEST = "Siamo spiacenti, si è verificato un errore durante l'esecuzione della richiesta";
    String ERR_TERMS = "Impossibile mostrare i termini e le condizioni";

}
