package com.example.flightbooking.fragments.news;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbooking.R;
import com.example.flightbooking.models.response.news.Post;

import java.util.Collections;
import java.util.List;

public class NewsFragmentAdapter extends RecyclerView.Adapter<NewsFragmentAdapter.ViewHolder> {

    private List<Post> posts = Collections.emptyList();

    public NewsFragmentAdapter(List<Post> posts){
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public View view;
        public TextView tv_title;
        public TextView tv_excerpt;
        public TextView tv_updated_at;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_title = itemView.findViewById(R.id.rpi_tv_title);
            this.tv_excerpt = itemView.findViewById(R.id.rpi_tv_excerpt);
            this.tv_updated_at = itemView.findViewById(R.id.rpi_tv_updated_at);
            this.view = itemView;
        }
    }
}