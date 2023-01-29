package com.example.flightbooking.fragments.news.post;

import android.view.View;

import com.example.flightbooking.R;
import com.example.flightbooking.models.response.news.Post;

import java.util.AbstractMap;
import java.util.Map;

public class PostFragmentMethods {

    /**
     * Create a map that contains the objects associated with the PostFragment layout
     * @param v the container of the views
     * @return the map with the view references
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

    /**
     * Set the TextViews value with the corresponding Post model values
     * @param pfv the view instance of the PostFragment class
     * @param post the model with the data to assign to aech view
     */
    public static void setViewsContent(PostFragmentView pfv, Post post){
        pfv.getTvTitle().setText(post.title);
        pfv.getTvContent().setText(post.content);
        pfv.getTvCategories().setText("CATEGORIE: "+post.categories);
        pfv.getTvTags().setText("TAG: "+post.tags);
        pfv.getTvCreatedAt().setText("DATA CREAZIONE: "+post.createdAt);
        pfv.getTvUpdatedAt().setText("ULTIMO AGGIORNAMENTO: "+post.updatedAt);
    }
}
