package com.example.flightbooking.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.flightbooking.common.RegEx;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public interface DialogDate{
        public void getDate(String date);
    }

    public DialogDate dd;
    private Integer year;
    private Integer month;
    private Integer day;
    private String inputDate;

    public DatePicker(String inputDate, DialogDate dd){
        this.inputDate = inputDate;
        this.dd = dd;
    }

    public int getYear(){return this.year;}
    public int getMonth(){return this.month;}
    public int getDay(){return this.day;}
    public String getInputDate(){return this.inputDate;}


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        this.setDate();
        return new DatePickerDialog(requireContext(),this,this.year,this.month,this.day);
    }

    @Override
    public void onDateSet(android.widget.DatePicker datePicker, int i, int i1, int i2) {
        Log.d("DatePicker","onDateSet year => "+i);
        Log.d("DatePicker","onDateSet month => "+i1);
        Log.d("DatePicker","onDateSet day => "+i2);
    }

    /**
     * Set the date from EditText value or current date
     */
    private void setDate(){
        boolean valid_date = false;
        //Check if the date from EditText is valid
        if(this.inputDate != null && !this.inputDate.isEmpty()){
            Pattern pattern = Pattern.compile(RegEx.PATTERN_DATE);
            Matcher matcher = pattern.matcher(this.inputDate);
            if(matcher.matches())
                valid_date = true;
        }//if(this.inputDate != null && !this.inputDate.isEmpty()){
        if(valid_date){
            String[] date_args = this.inputDate.split("-");
            this.year = Integer.valueOf(date_args[0]);
            this.month = Integer.valueOf(date_args[1]);
            this.year = Integer.valueOf(date_args[2]);
        }//if(valid_date){
        else{
            final Calendar cal = Calendar.getInstance();
            this.year = cal.get(Calendar.YEAR);
            this.month = cal.get(Calendar.MONTH);
            this.day = cal.get(Calendar.DAY_OF_MONTH);
        }
    }
}
