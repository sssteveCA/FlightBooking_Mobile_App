package com.example.flightbooking.fragments.home.events;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.flightbooking.models.response.flightevents.FlightEvent;

import java.util.List;

public class EventsFragmentAdapter extends ArrayAdapter<FlightEvent> {
    public EventsFragmentAdapter(@NonNull Context context, int resource, @NonNull List<FlightEvent> objects) {
        super(context, resource, objects);
    }

    private class ViewHolder{
        public ImageView image;
        public TextView name;
        public TextView date;
        public TextView city;
        public TextView price;
    }
}
