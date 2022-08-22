package com.example.flightbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.flightbooking.mainactivity.MainActivityController;
import com.example.flightbooking.mainactivity.MainActivityModel;
import com.example.flightbooking.mainactivity.MainActivityView;
import com.example.flightbooking.views.connection.NoConnectionFragment;

public class MainActivity extends AppCompatActivity implements NoConnectionFragment.RetryListener {

    private MainActivityController mac;
    private MainActivityModel mam;
    private MainActivityView mav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.run();
    }

    @Override
    public void retryClick() {
        //NoConnection fragment Retry button clicked
        //Log.i("MainActivity","retryClick");
        this.mav.removeFragment();
        this.mav.setProgressBar(View.VISIBLE);
        this.mac.setView();
    }

    /***
     *
     */

    /***
     * Execute MainActivity MVC classes
      */
    private void run(){
        this.mam = new MainActivityModel(this);
        this.mav = new MainActivityView(this);
        this.mav.setProgressBar(View.VISIBLE);
        this.mac = new MainActivityController(this.mam,this.mav);
        this.mac.setView();
    }
}