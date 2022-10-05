package com.example.flightbooking.interfaces;

/**
 * Values used throughtout the application
 */
public interface Globals {
    String BASE_URL = "http://192.168.0.27:8000"; //Base URL which this app does requests
    String API_ROUTES_PREFIX = "/api/v1";

    //Messages
    String ERR_INVALID_DATA_FORMAT = "Uno o più dati inseriti non hanno un formato valido, riprova";
    String ERR_REQUEST = "Siamo spiacenti, si è verificato un errore durante l'esecuzione della richiesta";

}
