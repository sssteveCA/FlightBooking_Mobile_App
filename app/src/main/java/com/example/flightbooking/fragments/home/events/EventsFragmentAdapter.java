package com.example.flightbooking.fragments.home.events;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.example.flightbooking.R;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.response.flightevents.FlightEvent;

import java.util.List;

public class EventsFragmentAdapter extends ArrayAdapter<FlightEvent> {

    /**
     * Listener for single flight event data display
     */
    public interface OnItemButtonClick{
        /**
         * Callback invoked when the button of a GridView item is cliked
         * @param bundle the data to pass
         */
        public void itemButtonClick(Bundle bundle);
    }

    private RequestQueue queue;
    private OnItemButtonClick oibc;

    public EventsFragmentAdapter(OnItemButtonClick oibc, @NonNull Context context, int resource, @NonNull List<FlightEvent> objects) {
        super(context, resource, objects);
        this.oibc = oibc;
        this.queue = Volley.newRequestQueue(context);
    }

    @Override
    public View getView(int pos, View convertView, ViewGroup vg){
        return this.getViewOptimize(pos,convertView,vg);
    }

    public View getViewOptimize(int pos, View convertView, ViewGroup vg){
        ViewHolder vh = null;
        if(convertView ==  null){
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = li.inflate(R.layout.row_event_item,null);
            vh = new ViewHolder();
            vh.image = convertView.findViewById(R.id.row_event_item_image);
            vh.name = convertView.findViewById(R.id.row_event_item_title);
            vh.date = convertView.findViewById(R.id.row_event_item_date);
            vh.city = convertView.findViewById(R.id.row_event_item_city);
            vh.price = convertView.findViewById(R.id.row_event_item_price);
            vh.button = convertView.findViewById(R.id.row_event_item_button);
            convertView.setTag(vh);
        }//if(convertView ==  null){
        else{
            vh = (ViewHolder) convertView.getTag();
        }
        FlightEvent fe = getItem(pos);
        this.fetchImage(fe.image,vh.image);
        vh.name.setText(fe.name);
        vh.date.setText(fe.date);
        vh.city.setText(fe.city);
        vh.price.setText(fe.price+"€");
        this.onButtonClick(vh.button,vh.image,fe);
        return convertView;
    }

    private class ViewHolder{
        public ImageView image;
        public TextView name;
        public TextView date;
        public TextView city;
        public TextView price;
        public Button button;
    }

    /**
     * Execute the HTTP request to get the image of the current flight event item
     * @param filename the basename of the image to fetch
     * @param imageView the view
     */
    private void fetchImage(String filename,ImageView imageView){
        String url = Globals.BASE_URL+Globals.FLIGHT_EVENTS_IMG_FOLDER+"/"+filename;
        ImageRequest imageRequest = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imageView.setImageBitmap(response);
            }
        }, 0, 0, ImageView.ScaleType.CENTER, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        this.queue.add(imageRequest);
    }

    /**
     * View more info of the events when a button of the gridview is clicked
     * @param button
     * @param fe
     */
    private void onButtonClick(Button button, ImageView iv, FlightEvent fe){
        OnItemButtonClick this_oibc = this.oibc;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("flightEvent",fe);
                bundle.putParcelable("image",((BitmapDrawable)iv.getDrawable()).getBitmap());
                this_oibc.itemButtonClick(bundle);
            }
        });
    }
}
