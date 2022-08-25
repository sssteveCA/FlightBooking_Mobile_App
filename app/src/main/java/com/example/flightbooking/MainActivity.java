package com.example.flightbooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.example.flightbooking.fragments.mainmenu.notlogged.MainMenuNotLoggedFragment;
import com.example.flightbooking.mainactivity.MainActivityModel;
import com.example.flightbooking.mainactivity.MainActivityView;
import com.example.flightbooking.fragments.noconnection.NoConnectionFragment;

public class MainActivity extends AppCompatActivity implements NoConnectionFragment.RetryListener, MainMenuNotLoggedFragment.OnMainMenuItemClick {

    private MainActivityModel mam;
    private MainActivityView mav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mam = new MainActivityModel(this);
        ProgressBar pb = findViewById(R.id.main_activity_pb);
        this.mav = new MainActivityView(this,pb);
        this.setFragments("Home");
    }

    @Override
    public void retryClick() {
        //NoConnection fragment Retry button clicked
        //Log.i("MainActivity","retryClick");
        this.mav.removeFragment(R.id.main_activity_fragment_container);
        this.setFragments("Home");
    }

    /**
     * Set the fragment after check the application status
     */
    public void setFragments(String label){
        boolean userLogged = this.mam.isUserLogged();
        if(userLogged){
            //User is logged with its account
        }//if(userLogged){
        {
            this.mav.updateFragment(R.id.main_menu_fragment_container, new MainMenuNotLoggedFragment());
        }
        boolean require_connection = this.mam.isConnectionRequired(label);
        if(require_connection){
            //For this item internet is required
            boolean connected = this.mam.getConnectionStatus();
            if(connected){

            }//if(connected){
            else{

                this.mav.updateFragment(R.id.main_activity_fragment_container, new NoConnectionFragment());
            }
        }//if(require_connection){
        else{

        }//else di if(require_connection){



    }

    //MainMenuFragment.OnMainMenuItemClick
    @Override
    public void mainMenuItemClick(String label) {

    }
}