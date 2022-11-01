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
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

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
        if(valid_date){}
        else{
            final Calendar cal = Calendar.getInstance();
            this.year = cal.get(Calendar.YEAR);
            this.month = cal.get(Calendar.MONTH);
            this.day = cal.get(Calendar.DAY_OF_MONTH);
        }
    }
}
