package com.example.flightbooking.fragments.home.events.singleevent;

import android.view.View;

import com.example.flightbooking.R;

import java.util.AbstractMap;
import java.util.Map;

public class SingleEventFragmentMethods {

    /**
     * Create a Map with the single event fragment layout view references
     * @param v the view used by the fragment
     * @return
     */
    public static Map<String, View> setViews(View v){
        return Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<>("tv_title",v.findViewById(R.id.frag_single_ev_tv_title)),
                new AbstractMap.SimpleImmutableEntry<>("tv_date",v.findViewById(R.id.frag_single_ev_tv_date)),
                new AbstractMap.SimpleImmutableEntry<>("tv_link",v.findViewById(R.id.frag_single_ev_tv_link)),
                new AbstractMap.SimpleImmutableEntry<>("iv_image",v.findViewById(R.id.frag_single_ev_iv_image)),
                new AbstractMap.SimpleImmutableEntry<>("tv_price",v.findViewById(R.id.frag_single_ev_tv_price)),
                new AbstractMap.SimpleImmutableEntry<>("et_quantity",v.findViewById(R.id.frag_single_ev_et_qt)),
                new AbstractMap.SimpleImmutableEntry<>("bt_book",v.findViewById(R.id.frag_single_ev_bt_book)),
                new AbstractMap.SimpleImmutableEntry<>("bt_back",v.findViewById(R.id.frag_single_ev_bt_back))
        );
    }
}
