package com.example.flightbooking.dialogs;

import static android.content.DialogInterface.BUTTON_POSITIVE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightbooking.R;
import com.example.flightbooking.fragments.home.hotel.images.HotelImagesAdapter;

import java.util.ArrayList;

/**
 * This dialog shows a list of images
 */
public class ImagesDialog extends DialogFragment implements DialogInterface.OnClickListener {
    private Context context;
    private FragmentManager fm;
    private String title;
    private RecyclerView rv_images;
    private HotelImagesAdapter hia;
    private ArrayList<Bitmap> bitmaps;

    public ImagesDialog(Context ctx,FragmentManager fm, String title, ArrayList<Bitmap> bitmaps){
        Log.d("ImagesDialog","constructor");
        this.context = ctx;
        this.fm = fm;
        this.title = title;
        this.bitmaps = bitmaps;
    }

    public String getTitle(){ return this.title; }
    public RecyclerView getImages(){ return this.rv_images; }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Log.d("ImagesDialog","onCreateDialog");
        LayoutInflater li = requireActivity().getLayoutInflater();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(this.title);
        View v = li.inflate(R.layout.dialog_images,null);
        builder.setView(v);
        this.setReciclerView(v);
        builder.setPositiveButton("OK",this);
        return builder.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if(dialog != null){
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    /**
     * Display the hotel images dialog fullscreen
     */
    public void displayFullScreen(){
        this.show(this.fm, "hotelImages");
    }

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == BUTTON_POSITIVE)
            dialogInterface.dismiss();
    }

    private void setReciclerView(View v){
        Log.d("ImagesDialog","setRecyclerView");
        this.rv_images = v.findViewById(R.id.dlg_img_rv);
        LinearLayoutManager llm = new LinearLayoutManager(this.context);
        this.rv_images.setLayoutManager(llm);
        this.rv_images.setHasFixedSize(true);
        this.hia = new HotelImagesAdapter(this.bitmaps);
        this.rv_images.setAdapter(this.hia);
    }
}
