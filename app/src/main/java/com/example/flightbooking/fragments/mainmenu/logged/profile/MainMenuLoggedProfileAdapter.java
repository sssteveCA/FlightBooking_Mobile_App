package com.example.flightbooking.fragments.mainmenu.logged.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.flightbooking.R;
import com.example.flightbooking.fragments.mainmenu.notlogged.MainMenuNotLoggedAdapter;
import com.example.flightbooking.models.login.Auth;

import java.util.HashMap;
import java.util.List;

public class MainMenuLoggedProfileAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> elv_items;
    private HashMap<String, List<String>> elv_containers;
    private Auth auth;

    public MainMenuLoggedProfileAdapter(Context context, List<String> elv_items, HashMap<String, List<String>> elv_containers, Auth auth){
        this.context = context;
        this.elv_items = elv_items;
        this.elv_containers = elv_containers;
        this.auth = auth;
    }

    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return null;
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String expandedListText = (String) getChild(i, i1);
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_logged_profile_item, null);
        }//if(view == null){
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    private class ViewHolder{
        public TextView label;
    }
}
