package com.example.flightbooking.interfaces;

/**
 * Values used throughtout the application
 */
public interface Globals {
    String BASE_URL = "http://192.168.0.22:8000"; //Base URL which this app does requests
    String API_ROUTES_PREFIX = "/api/v1";
    String IMG_FOLDER = "/img";
    String HOTEL_IMG_FOLDER = Globals.IMG_FOLDER+"/hotels";

    //Messages
    String ERR_CONTACTS = "Siamo spiacenti, si è verificato un errore durante l'invio della mail";
    String ERR_INVALID_DATA_FORMAT = "Uno o più dati inseriti non hanno un formato valido, riprova";
    String ERR_LOGOUT = "Si è verificato un errore durante la disconnessione dell'account";
    String ERR_REQUEST = "Siamo spiacenti, si è verificato un errore durante l'esecuzione della richiesta";

}
