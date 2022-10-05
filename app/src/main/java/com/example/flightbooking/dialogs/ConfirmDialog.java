package com.example.flightbooking.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ConfirmDialog  extends AlertDialog.Builder {

    private String title;
    private String message;

    public ConfirmDialog(Context context, String title, String message) {
        super(context);
        this.title = title;
        this.message = message;
    }

    public String getTitle(){ return this.title; }
    public String getMessage(){ return this.message; }

    public void setDialog(DialogInterface.OnClickListener yesListener, DialogInterface.OnClickListener noListener){
        this.setTitle(this.title);
        this.setMessage(this.title);
        this.onYesClick(yesListener);
        this.onNoClick(noListener);
        AlertDialog ad = this.create();
        ad.show();
    }

    /**
     * When user press "YES" button in confirm dialog
     * @param click the listener
     */
    private void onYesClick(DialogInterface.OnClickListener click){
        this.setPositiveButton("SÌ",click);
    }

    /**
     * When user press "NO" button in confirm dialog
     * @param click the listener
     */
    private void onNoClick(DialogInterface.OnClickListener click){
        this.setNegativeButton("NO",click);
    }
}
