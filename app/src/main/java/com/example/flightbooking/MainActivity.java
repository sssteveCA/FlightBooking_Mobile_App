package com.example.flightbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.flightbooking.fragments.mainmenu.MainMenuFragment;
import com.example.flightbooking.mainactivity.MainActivityModel;
import com.example.flightbooking.mainactivity.MainActivityView;
import com.example.flightbooking.fragments.noconnection.NoConnectionFragment;

public class MainActivity extends AppCompatActivity implements NoConnectionFragment.RetryListener, MainMenuFragment.OnMainMenuItemClick {

    private MainActivityModel mam;
    private MainActivityView mav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mam = new MainActivityModel(this);
        ProgressBar pb = findViewById(R.id.main_activity_pb);
        this.mav = new MainActivityView(this,pb);
        this.setFragment(null);
    }

    @Override
    public void retryClick() {
        //NoConnection fragment Retry button clicked
        //Log.i("MainActivity","retryClick");
        this.mav.removeFragment(R.id.main_activity_fragment_container);
        this.setFragment(null);
    }

    /**
     * Set the fragment after check the application status
     */
    public void setFragment(String label){
        boolean require_connection = this.mam.isConnectionRequired(label);
        boolean connected = this.mam.getConnectionStatus();
        //Log.d("MainActivityController","setView conncted => "+connected);
        if(connected){

        }//if(connected){
        else{
            this.mav.updateFragment(R.id.main_menu_fragment_container, new MainMenuFragment());
            this.mav.updateFragment(R.id.main_activity_fragment_container, new NoConnectionFragment());
        }

    }

    //MainMenuFragment.OnMainMenuItemClick
    @Override
    public void mainMenuItemClick(String label) {

    }
}