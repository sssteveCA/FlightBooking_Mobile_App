package com.example.flightbooking.dialogs;

import static android.content.DialogInterface.BUTTON_POSITIVE;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

//Create a Dialog with message that is destroyed when OK button is pressed
public class MessageDialog extends AlertDialog.Builder implements DialogInterface.OnClickListener {

    private String title;
    private String message;

    public MessageDialog(Context context, String title, String message) {
        super(context);
        this.title = title;
        this.message = message;
        this.setDialog();
    }

    public String getTitle(){return this.title;}
    public String getMessage(){return this.message;}

    private void setDialog(){
        this.setTitle(this.title);
        this.setMessage(this.message);
        this.setPositiveButton("OK",this);
        AlertDialog ad = this.create();
        ad.show();
    }

    //DialogInterface.OnClickListener
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(i == BUTTON_POSITIVE)
            dialogInterface.dismiss();
    }
}
