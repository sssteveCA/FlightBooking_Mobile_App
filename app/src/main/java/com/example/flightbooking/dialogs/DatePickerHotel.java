package com.example.flightbooking.dialogs;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.flightbooking.common.RegEx;
import com.example.flightbooking.fragments.home.hotel.HotelFragmentModel;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DatePickerHotel extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    public interface DialogDateHotel{
        public void getDate(String date, HotelFragmentModel.EditTextsDate editTextsDate);
    }

    public DialogDateHotel ddh;
    private Integer year;
    private Integer month;
    private Integer day;
    private String inputDate;
    private HotelFragmentModel.EditTextsDate etd;

    public DatePickerHotel(String inputDate, DialogDateHotel ddh, HotelFragmentModel.EditTextsDate etd){
        this.inputDate = inputDate;
        this.ddh = ddh;
        this.etd = etd;
    }

    public int getYear(){return this.year;}
    public int getMonth(){return this.month;}
    public int getDay(){return this.day;}
    public String getInputDate(){return this.inputDate;}

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        this.setDate();
        DatePickerDialog dpd = new DatePickerDialog(requireContext(),this,this.year,this.month,this.day);
        dpd.getDatePicker().setMinDate(System.currentTimeMillis());
        return dpd;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        i1++;
        String month = (i1 < 10) ? "0" + i1 : String.valueOf(i1);
        String day = (i2 < 10) ? "0" + i2 : String.valueOf(i2);
        String date = i+"-"+month+"-"+day;
        this.ddh.getDate(date,etd);
    }

    /**
     * Set the date from EditText value or current date
     */
    private void setDate(){
        boolean valid_date = false;
        if(this.inputDate != null && !this.inputDate.isEmpty()){
            Pattern pattern = Pattern.compile(RegEx.PATTERN_DATE);
            Matcher matcher = pattern.matcher(this.inputDate);
            if(matcher.matches())
                valid_date = true;
        }
        if(valid_date){
            this.setEditTextDate();
        }
        else{
            final Calendar cal = Calendar.getInstance();
            this.year = cal.get(Calendar.YEAR);
            this.month = cal.get(Calendar.MONTH);
            this.day = cal.get(Calendar.DAY_OF_MONTH);
        }
    }

    /**
     * If there is a valid date from EditText, use that when date picker dialog is opened
     */
    private void setEditTextDate(){
        String[] date_args = this.inputDate.split("-");
        //Log.d("DatePicker","DatePicker setDate date_args => "+ Arrays.toString(date_args));
        this.year = Integer.valueOf(date_args[0]);
        if(date_args[1].startsWith("0"))
            date_args[1] = date_args[1].substring(1);
        this.month = Integer.parseInt(date_args[1]) - 1;
        if(date_args[2].startsWith("0"))
            date_args[2] = date_args[2].substring(1);
        this.day = Integer.valueOf(date_args[2]);
    }
}
