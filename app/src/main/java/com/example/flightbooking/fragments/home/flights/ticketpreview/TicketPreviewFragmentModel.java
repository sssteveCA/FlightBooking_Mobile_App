package com.example.flightbooking.fragments.home.flights.ticketpreview;

import android.util.Log;

import com.example.flightbooking.models.requests.flights.BookFlightRequest;
import com.example.flightbooking.models.response.flights.BookFlightResponse;
import com.example.flightbooking.models.response.flights.FlightInfo;
import com.example.flightbooking.models.response.login.Auth;

import java.lang.reflect.Field;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketPreviewFragmentModel {

    public interface BookFlightResponseInterface{
        public void bookFlightResponse(BookFlightResponse bfr);
        public void bookFlightError();
    }

    private Auth auth;
    private FlightInfo fi;
    private String session_id;
    private String flight_type;
    private HashMap<String, HashMap<String, Object>> flights;
    private TicketPreviewTableValues tptv;
    private TicketPreviewFragmentClient tpfc;

    public TicketPreviewFragmentModel(FlightInfo fi, Auth auth){
        this.assignValues(fi,auth);
    }

    public String getFlightType(){return this.flight_type;}
    public String getSessionId(){return this.session_id;}
    public HashMap<String, HashMap<String, Object>> getFlights(){return this.flights;}
    public TicketPreviewTableValues getTptv(){return this.tptv;}

    private void assignValues(FlightInfo fi, Auth auth){
        this.fi = fi;
        this.auth = auth;
        if(this.fi != null){
            this.session_id = fi.sessionId;
            this.flight_type = fi.flightType;
        }
        this.tptv = new TicketPreviewTableValues();
        this.tpfc = new TicketPreviewFragmentClient();
        try {
            this.setHashMap();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * Perform the HTTP request to book the previewed flights
     * @param bf_req
     * @param bf_resi
     */
    public void bookFlightRequest(BookFlightRequest bf_req, BookFlightResponseInterface bf_resi){
        String token = this.auth != null ? "Bearer "+this.auth.token : "";
        this.tpfc.getTpfi().bookflight(token,bf_req).enqueue(new Callback<BookFlightResponse>() {
            @Override
            public void onResponse(Call<BookFlightResponse> call, Response<BookFlightResponse> response) {
                if(response.isSuccessful())
                    bf_resi.bookFlightResponse(response.body());
                else bf_resi.bookFlightError();
            }
            @Override
            public void onFailure(Call<BookFlightResponse> call, Throwable t) {
                bf_resi.bookFlightError();
            }
        });
    }

    /**
     * Set the flights HashMap from FlightInfo properties
     */
    private void setHashMap() throws IllegalAccessException {
        this.flights = new HashMap<>();
        Class<?> flights_class = this.fi.flights.getClass();
        Field[] fi_fields = flights_class.getDeclaredFields();
        for(Field field: fi_fields){
           String name = field.getName();
           Object flight = field.get(this.fi.flights);
           if(flight != null){
               Class<?> flight_class = flight.getClass();
               Field[] fl_fields = flight_class.getDeclaredFields();
               HashMap<String, Object> map_flight = this.setFlightHashMap(fl_fields,flight);
               String hm_name = "";
               if(name.equalsIgnoreCase("oneway"))hm_name = "Volo di sola andata";
               else if(name.equalsIgnoreCase("outbound"))hm_name = "Volo di andata";
               else if(name.equalsIgnoreCase("returnF"))hm_name = "Volo di ritorno";
               this.flights.put(hm_name, map_flight);
           }//if(flight != null){
        }//for(Field field: fi_fields){
    }

    /**
     * Set single flight HashMap to be added to flights HashMap
     * @param fl_fields
     * @param flight
     * @return the single flight HashMap
     * @throws IllegalAccessException
     */
    private HashMap<String, Object> setFlightHashMap(Field[] fl_fields, Object flight) throws IllegalAccessException {
        HashMap<String, Object> map_flight = new HashMap<>();
        for(Field fl_field: fl_fields){
            String fl_name = fl_field.getName();
            String row_name = this.setRowTitle(fl_name);
            Object flight_val = fl_field.get(flight);
            map_flight.put(row_name, flight_val);
        }//for(Field fl_field: fl_fields){
        return map_flight;
    }

    /**
     * Set table row title from HashMap key
     * @param key
     * @return
     */
    private String setRowTitle(String key){
        if(key.equalsIgnoreCase("companyName"))
            return "Compagnia aerea";
        if(key.equalsIgnoreCase("departureCountry"))
            return "Paese di partenza";
        if(key.equalsIgnoreCase("departureAirport"))
            return "Aereoporto di partenza";
        if(key.equalsIgnoreCase("flightDate"))
            return "Data del volo";
        if(key.equalsIgnoreCase("flightTime"))
            return "Orario del volo";
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
        return "";
    }

    /**
     * Assign needed views to TicketPreviewTableValues object
     * @param key
     * @param val
     */
    public void setTpfvTableValues(String key, Object val){
        if(key.equalsIgnoreCase("Compagnia aerea"))
            this.tptv.company_name = (String) val;
        if(key.equalsIgnoreCase("Paese di partenza"))
            this.tptv.departure_country = (String) val;
        if(key.equalsIgnoreCase("Aereoporto di partenza"))
            this.tptv.departure_airport = (String) val;
        if(key.equalsIgnoreCase("Data del volo"))
            this.tptv.flight_date = (String) val;
        if(key.equalsIgnoreCase("Paese di arrivo"))
            this.tptv.arrival_country = (String) val;
        if(key.equalsIgnoreCase("Aereoporto di arrivo"))
            this.tptv.arrival_airport = (String) val;
        if(key.equalsIgnoreCase("Adulti"))
            this.tptv.adults = (Integer) val;
        if(key.equalsIgnoreCase("Adolescenti"))
            this.tptv.teenagers = (Integer) val;
        if(key.equalsIgnoreCase("Bambini"))
            this.tptv.chidren = (Integer) val;
        if(key.equalsIgnoreCase("Neonati"))
            this.tptv.newborns = (Integer) val;
        if(key.equalsIgnoreCase("Prezzo"))
            this.tptv.flight_price = (Float) val;
    }
}
