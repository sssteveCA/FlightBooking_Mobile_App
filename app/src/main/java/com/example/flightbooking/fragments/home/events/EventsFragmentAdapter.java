package com.example.flightbooking.fragments.home.events;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.example.flightbooking.models.response.flightevents.FlightEvent;

import java.util.List;

public class EventsFragmentAdapter extends ArrayAdapter<FlightEvent> {
    public EventsFragmentAdapter(@NonNull Context context, int resource, @NonNull List<FlightEvent> objects) {
        super(context, resource, objects);
    }
}
