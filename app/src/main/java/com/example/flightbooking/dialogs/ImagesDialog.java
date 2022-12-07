package com.example.flightbooking.dialogs;

import static android.content.DialogInterface.BUTTON_POSITIVE;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This dialog shows a list of images
 */
public class ImagesDialog extends DialogFragment implements DialogInterface.OnClickListener {
    private FragmentManager fm;
    private String title;
    private RecyclerView images;

    public ImagesDialog(FragmentManager fm, String title, RecyclerView images){
        this.fm = fm;
        this.title = title;
        this.images = images;
    }

    public String getTitle(){ return this.title; }
    public RecyclerView getImages(){ return this.images; }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(this.title);
        builder.setPositiveButton("OK",this);
        return builder.create();
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
}
