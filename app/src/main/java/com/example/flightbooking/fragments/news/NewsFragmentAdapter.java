package com.example.flightbooking.fragments.news;

import android.view.LayoutInflater;
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

    public interface PostItemClickListener{
        public void click(Post post);
    }

    private List<Post> posts = Collections.emptyList();
    public PostItemClickListener picl;

    public NewsFragmentAdapter(List<Post> posts, PostItemClickListener picl){
        this.posts = posts;
        this.picl = picl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View postView = li.inflate(R.layout.row_post_item,parent,false);
        return new ViewHolder(postView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsFragmentAdapter this_nfa = this;
        final int index = holder.getBindingAdapterPosition();
        holder.tv_title.setText(this.posts.get(position).title);
        holder.tv_excerpt.setText(this.posts.get(position).excerpt);
        holder.tv_updated_at.setText("Ultima modifica: "+this.posts.get(position).updatedAt);
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post post = this_nfa.posts.get(index);
                picl.click(post);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
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
