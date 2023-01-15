package com.example.flightbooking.fragments.home.events;

import com.example.flightbooking.models.response.flightevents.GetFlightEventsResponse;

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
}
