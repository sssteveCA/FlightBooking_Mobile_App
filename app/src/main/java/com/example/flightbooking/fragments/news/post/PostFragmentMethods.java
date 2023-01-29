package com.example.flightbooking.fragments.news.post;

import android.view.View;

import com.example.flightbooking.R;

import java.util.AbstractMap;
import java.util.Map;

public class PostFragmentMethods {

    /**
     * Create a map that contains the objects associated with the PostFragment layout
     * @param v the container of the views
     * @return
     */
    public static Map<String, View> definePostViews(View v){
        Map<String, View> map = Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<String,View>("tv_title",v.findViewById(R.id.frag_post_tv_title)),
                new AbstractMap.SimpleImmutableEntry<String,View>("tv_content",v.findViewById(R.id.frag_post_tv_content)),
                new AbstractMap.SimpleImmutableEntry<String,View>("tv_categories",v.findViewById(R.id.frag_post_tv_categories)),
                new AbstractMap.SimpleImmutableEntry<String,View>("tv_tags",v.findViewById(R.id.frag_post_tv_tags)),
                new AbstractMap.SimpleImmutableEntry<String,View>("tv_created_at",v.findViewById(R.id.frag_post_tv_created_at)),
                new AbstractMap.SimpleImmutableEntry<String,View>("tv_updated_at",v.findViewById(R.id.frag_post_tv_updated_at)),
                new AbstractMap.SimpleImmutableEntry<String,View>("bt_back",v.findViewById(R.id.frag_post_bt_back))
        );
        return map;
    }
}
