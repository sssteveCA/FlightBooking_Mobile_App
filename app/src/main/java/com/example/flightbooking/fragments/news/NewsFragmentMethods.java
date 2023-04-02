package com.example.flightbooking.fragments.news;

import android.view.View;
import android.widget.LinearLayout;

import com.example.flightbooking.R;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewsFragmentMethods {

    /**
     * Create a Map with NewsFragment view references
     * @param v
     * @return
     */
    public static Map<String, View> newsItems(View v){
        LinearLayout ll_news_list = v.findViewById(R.id.frag_news_ll_news_list);
        return Stream.of(
                new AbstractMap.SimpleImmutableEntry<>("ll_news_list",ll_news_list),
                new AbstractMap.SimpleImmutableEntry<>("tv_message",ll_news_list.findViewById(R.id.frag_news_tv_message)),
                new AbstractMap.SimpleImmutableEntry<>("rv_posts",ll_news_list.findViewById(R.id.frag_news_rv_posts)),
                new AbstractMap.SimpleImmutableEntry<>("pb",ll_news_list.findViewById(R.id.frag_news_pb)),
                new AbstractMap.SimpleImmutableEntry<>("bt_back",v.findViewById(R.id.frag_news_bt_back))
        ).collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue));
    }
}
