package com.example.flightbooking.fragments.home.flights.ticketpreview;

import android.util.Log;

import com.example.flightbooking.models.FlightInfo;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TicketPreviewFragmentModel {

    private FlightInfo fi;
    private String session_id;
    private String flight_type;
    private HashMap<String, HashMap<String, Object>> flights;

    public TicketPreviewFragmentModel(FlightInfo fi){
        this.fi = fi;
        this.session_id = fi.sessionId;
        this.flight_type = fi.flightType;
        try {
            this.setHashMap();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public String getFlightType(){return this.flight_type;}
    public String getSessionId(){return this.session_id;}
    public HashMap<String, HashMap<String, Object>> getFlights(){return this.flights;}

    /**
     * Set the flights HashMap from FlightInfo properties
     */
    private void setHashMap() throws IllegalAccessException {
        this.flights = new HashMap<>();
        Class<?> flights_class = this.fi.flights.getClass();
        Field[] fi_fields = flights_class.getDeclaredFields();
        for(Field field: fi_fields){
           String name = field.getName();
           Log.d("TicketPreviewFragmentModel","setHashMap name => "+ name);
           Object flight = field.get(this.fi.flights);
           if(flight != null){
               Class<?> flight_class = flight.getClass();
               Field[] fl_fields = flight_class.getDeclaredFields();
               HashMap<String, Object> map_flight = new HashMap<>();
               for(Field fl_field: fl_fields){
                   field.setAccessible(true);
                   String fl_name = fl_field.getName();
                   Log.d("TicketPreviewFragmentModel","setHashMap flight name => "+ fl_name);
                   String row_name = this.setRowTitle(fl_name);
                   Log.d("TicketPreviewFragmentModel","setHashMap row name => "+ row_name);
                   Object flight_val = fl_field.get(flight);
                   map_flight.put(row_name, flight_val);
               }//for(Field fl_field: fl_fields){
               this.flights.put(name, map_flight);
           }//if(flight != null){
        }//for(Field field: fi_fields){
    }

    /**
     * Set table row title from HashMap key
     * @param key
     * @return
     */
    private String setRowTitle(String key){
        String title = "";
        if(key.equalsIgnoreCase("companyName"))
            return "Compagnia aerea";
        if(key.equalsIgnoreCase("departureCountry"))
            return "Paese di partenza";
        if(key.equalsIgnoreCase("departureAirport"))
            return "Aereoporto di partenza";
        if(key.equalsIgnoreCase("flightDate"))
            return "Data del volo";
        if(key.equalsIgnoreCase("arrivalCountry"))
            return "Paese di arrivo";
        if(key.equalsIgnoreCase("arrivalAirport"))
            return "Aereoporto di arrivo";
        if(key.equalsIgnoreCase("adults"))
            return "Adulti";
        if(key.equalsIgnoreCase("teenagers"))
            return "Adolescenti";
        if(key.equalsIgnoreCase("children"))
            return "Bambini";
        if(key.equalsIgnoreCase("newborns"))
            return "Neonati";
        if(key.equalsIgnoreCase("flightPrice"))
            return "Prezzo";
        return title;
    }
}
