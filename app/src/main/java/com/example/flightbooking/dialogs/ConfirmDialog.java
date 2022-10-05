package com.example.flightbooking.dialogs;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class ConfirmDialog  extends AlertDialog.Builder {

    private String title;
    private String message;

    public ConfirmDialog(Context context) {
        super(context);
    }

    public String getTitle(){ return this.title; }
    public String getMessage(){ return this.message; }

    private void setDialog(){
        this.setTitle(this.title);
        this.setMessage(this.title);
        AlertDialog ad = this.create();
        ad.show();
    }

    /**
     * When user press "YES" button in confirm dialog
     * @param click the listener
     */
    public void onYesClick(DialogInterface.OnClickListener click){
        this.setPositiveButton("SÌ",click);
    }

    /**
     * When user press "NO" button in confirm dialog
     * @param click the listener
     */
    public void onNoClick(DialogInterface.OnClickListener click){
        this.setNegativeButton("NO",click);
    }
}
