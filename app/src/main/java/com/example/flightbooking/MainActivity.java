package com.example.flightbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.flightbooking.fragments.mainmenu.MainMenuFragment;
import com.example.flightbooking.mainactivity.MainActivityModel;
import com.example.flightbooking.mainactivity.MainActivityView;
import com.example.flightbooking.fragments.noconnection.NoConnectionFragment;

public class MainActivity extends AppCompatActivity implements NoConnectionFragment.RetryListener {

    private MainActivityModel mam;
    private MainActivityView mav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mam = new MainActivityModel(this);
        ProgressBar pb = findViewById(R.id.main_activity_pb);
        this.mav = new MainActivityView(this,pb);
        this.mav.getPb().setVisibility(View.VISIBLE);
        this.setView();
    }

    @Override
    public void retryClick() {
        //NoConnection fragment Retry button clicked
        //Log.i("MainActivity","retryClick");
        this.mav.removeFragment(R.id.main_activity_fragment_container);
        this.mav.getPb().setVisibility(View.VISIBLE);
        this.setView();
    }

    /**
     * Set the fragment after check the application status
     */
    public void setView(){
        boolean connected = this.mam.getConnectionStatus();
        //Log.d("MainActivityController","setView conncted => "+connected);
        this.mav.getPb().setVisibility(View.GONE);
        if(connected){

        }//if(connected){
        else{
            this.mav.updateFragment(R.id.main_menu_fragment_container, new MainMenuFragment());
            this.mav.updateFragment(R.id.main_activity_fragment_container, new NoConnectionFragment());
        }

    }
}