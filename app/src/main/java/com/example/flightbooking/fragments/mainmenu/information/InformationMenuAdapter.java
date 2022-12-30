package com.example.flightbooking.fragments.mainmenu.information;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import com.example.flightbooking.models.MenuItem;

import java.util.HashMap;
import java.util.List;

public class InformationMenuAdapter  extends BaseExpandableListAdapter {

    private Context context;
    private List<String> inf_items;
    private HashMap<String, List<MenuItem>> inf_containers;



    @Override
    public int getGroupCount() {
        return this.inf_items.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return this.inf_containers.get(this.inf_items.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return this.inf_items.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return this.inf_containers.get(this.inf_items.get(i)).get(i1);
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
        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
