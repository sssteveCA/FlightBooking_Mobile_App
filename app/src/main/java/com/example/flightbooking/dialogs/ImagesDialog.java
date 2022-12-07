package com.example.flightbooking.dialogs;

import static android.content.DialogInterface.BUTTON_POSITIVE;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * This dialog shows a list of images
 */
public class ImagesDialog extends DialogFragment implements DialogInterface.OnClickListener {
    private String title;
    private RecyclerView images;

    public ImagesDialog(String title, RecyclerView images){
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

    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == BUTTON_POSITIVE)
            dialogInterface.dismiss();
    }
}
