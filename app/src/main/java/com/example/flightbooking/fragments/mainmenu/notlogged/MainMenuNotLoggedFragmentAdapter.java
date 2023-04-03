package com.example.flightbooking.fragments.mainmenu.notlogged;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.flightbooking.R;
import com.example.flightbooking.models.MenuItem;

import java.util.List;

public class MainMenuNotLoggedFragmentAdapter extends ArrayAdapter<MenuItem> {
    public MainMenuNotLoggedFragmentAdapter(@NonNull Context context, int resource, @NonNull List<MenuItem> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return optimizeView(position, convertView, parent);
    }

    public View optimizeView(int pos, View convertView, ViewGroup parent){
        ViewHolder vh = null;
        if(convertView == null){
            LayoutInflater inf = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.row,null);
            vh = new ViewHolder();
            vh.label = convertView.findViewById(R.id.main_menu_item_tv);
            convertView.setTag(vh);
        }//if(convertView == null){
        else{
            vh = (ViewHolder)convertView.getTag();
        }
        MenuItem mi = getItem(pos);
        vh.label.setText(mi.getLabel());
        return convertView;
    }

    private class ViewHolder{
        public TextView label;
    }
}
