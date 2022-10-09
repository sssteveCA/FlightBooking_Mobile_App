package com.example.flightbooking.fragments.mainmenu.logged.profile;

import android.content.Context;
import android.graphics.Typeface;
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
        return this.elv_items.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.elv_containers.get(this.elv_items.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.elv_items.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.elv_containers.get(this.elv_items.get(i)).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        String listTitle = (String) getGroup(i);
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_logged_profile_elv_title, null);
        }//if(view == null){
        TextView tv_elv_title = (TextView) view.findViewById(R.id.main_menu_logged_elv_tv_title);
        tv_elv_title.setTypeface(null, Typeface.BOLD);
        tv_elv_title.setText(listTitle);
        return view;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        final String expandedListText = (String) getChild(i, i1);
        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.row_logged_profile_item, null);
        }//if(view == null){
        TextView tv_elv_item = (TextView) view.findViewById(R.id.main_menu_logged_profile_item_tv);
        tv_elv_item.setText(expandedListText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
