package com.example.flightbooking.fragments.home.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.flightbooking.R;
import com.example.flightbooking.models.response.flightevents.FlightEvent;

import java.util.List;

public class EventsFragmentAdapter extends ArrayAdapter<FlightEvent> {
    public EventsFragmentAdapter(@NonNull Context context, int resource, @NonNull List<FlightEvent> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int pos, View view, ViewGroup vg){
        return this.getViewOptimize(pos,view,vg);
    }

    public View getViewOptimize(int pos, View view, ViewGroup vg){
        ViewHolder vh = null;
        if(view ==  null){
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = li.inflate(R.layout.row_event_item,null);
            vh = new ViewHolder();
            vh.image = view.findViewById(R.id.row_event_item_image);
            vh.name = view.findViewById(R.id.row_event_item_title);
            vh.date = view.findViewById(R.id.row_event_item_date);
            vh.city = view.findViewById(R.id.row_event_item_city);
            vh.price = view.findViewById(R.id.row_event_item_price);
        }//if(view ==  null){
        else{
            vh = (ViewHolder) view.getTag();
        }
        FlightEvent fe = getItem(pos);
        vh.name.setText(fe.name);
        vh.date.setText(fe.date);
        vh.city.setText(fe.city);
        vh.price.setText(fe.price+"€");
        return view;
    }

    private class ViewHolder{
        public ImageView image;
        public TextView name;
        public TextView date;
        public TextView city;
        public TextView price;
    }
}
