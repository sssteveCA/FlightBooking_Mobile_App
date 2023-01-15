package com.example.flightbooking.fragments.home.events;

import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.response.flightevents.GetFlightEventsResponse;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventsFragmentModel {

    public interface GetEventsResponse{
        public void eventsSuccess(GetFlightEventsResponse gfer);
        public void eventsError(String message);
    }

    private EventsFragmentClient efc;

    public EventsFragmentModel(){
        this.efc = new EventsFragmentClient();
    }

    public EventsFragmentClient getEfc(){return this.efc;}

    /**
     * Execute the request to get the flights associsted with events
     * @param ger the listener launched when a response is got
     */
    public void getEventsRequest(GetEventsResponse ger){
        this.efc.getEfi().flightEvents().enqueue(new Callback<GetFlightEventsResponse>() {
            @Override
            public void onResponse(Call<GetFlightEventsResponse> call, Response<GetFlightEventsResponse> response) {
                if(response.isSuccessful())
                    ger.eventsSuccess(response.body());
                else{
                    String message = "";
                    try {
                        String jsonString = response.errorBody().string();
                        JsonElement je = JsonParser.parseString(jsonString);
                        JsonObject jo = je.getAsJsonObject();
                        message = jo.get("message").getAsString();
                        if(message == null)message = Globals.ERR_EVENTS;
                    } catch (IOException e) {
                        e.printStackTrace();
                        message = Globals.ERR_EVENTS;
                    } finally {
                        ger.eventsError(message);
                    }

                }
            }
            @Override
            public void onFailure(Call<GetFlightEventsResponse> call, Throwable t) {
                String message = Globals.ERR_EVENTS;
                ger.eventsError(message);
            }
        });
    }
}
