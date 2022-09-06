package com.example.flightbooking.fragments.home;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.exception.MissingValuesException;
import com.google.android.material.chip.ChipGroup;

import java.util.HashMap;
import java.util.Map;

public class HomeFragmentView {

    private ChipGroup cg_type;
    private MainActivity ma;

    public HomeFragmentView(MainActivity ma, ChipGroup cg_type) {
        this.ma = ma;
        this.cg_type = cg_type;
    }

    public ChipGroup getCgType(){return this.cg_type;}

}
