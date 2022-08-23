package com.example.flightbooking.fragments.mainmenu;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.flightbooking.models.MenuItem;

import java.util.List;

public class MainMenuAdapter extends ArrayAdapter<MenuItem> {
    public MainMenuAdapter(@NonNull Context context, int resource, @NonNull List<MenuItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }

    public View optimizeView(int pos, View convertView, ViewGroup parent){

    }

    private class ViewHolder{
        public TextView label;
    }
}
