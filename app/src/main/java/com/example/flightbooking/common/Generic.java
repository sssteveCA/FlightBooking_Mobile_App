package com.example.flightbooking.common;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.List;

public class Generic {

    /**
     * Create an ArrayAdapter from list
     * @param items
     * @return
     */
    public static ArrayAdapter<String> arrayAdapterFromList(Context context, List<String> items){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }
}
