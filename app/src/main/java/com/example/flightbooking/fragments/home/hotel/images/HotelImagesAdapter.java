package com.example.flightbooking.fragments.home.hotel.images;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbooking.R;

import java.util.ArrayList;
import java.util.List;

public class HotelImagesAdapter extends RecyclerView.Adapter<HotelImagesAdapter.ViewHolder> {

    private ArrayList<Bitmap> bmps;

    public HotelImagesAdapter(ArrayList<Bitmap> bmps){
        this.bmps = bmps;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(parent.getContext());
        View view = li.inflate(R.layout.row_imageview,parent,false);
         return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageBitmap(this.bmps.get(position));
    }

    @Override
    public int getItemCount() {
        return this.bmps.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public View mView;
        public ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mView = itemView;
            this.imageView = itemView.findViewById(R.id.dlg_img_rv_iv);
        }
    }
}
