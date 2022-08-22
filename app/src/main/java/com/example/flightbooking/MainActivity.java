package com.example.flightbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.flightbooking.views.connection.NoConnectionFragment;

public class MainActivity extends AppCompatActivity implements NoConnectionFragment.RetryListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void retryClick() {
        //NoConnection fragment Retry button clicked
    }
}