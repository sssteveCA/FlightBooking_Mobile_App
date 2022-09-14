package com.example.flightbooking;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;

import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.fragments.mainmenu.notlogged.MainMenuNotLoggedFragment;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.mainactivity.MainActivityModel;
import com.example.flightbooking.mainactivity.MainActivityView;
import com.example.flightbooking.fragments.noconnection.NoConnectionFragment;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity implements NoConnectionFragment.RetryListener, MainMenuNotLoggedFragment.OnMainMenuItemClick, FragmentChange {

    private MainActivityModel mam;
    private MainActivityView mav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mam = new MainActivityModel(this);
        ProgressBar pb = findViewById(R.id.main_activity_pb);
        this.mav = new MainActivityView(this,pb);
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.setMenu();
        this.setFragments(FragmentLabels.HOME.getLabelName(),null);
    }

    @Override
    public void retryClick(String label) {
        //NoConnection fragment Retry button clicked
        //Log.i("MainActivity","retryClick");
        this.mav.removeFragment(R.id.main_activity_fragment_container);
        if(label.equalsIgnoreCase(FragmentLabels.FLIGHTS.getLabelName()) || label.equalsIgnoreCase(FragmentLabels.TICKET_PREVIEW.getLabelName())){
            label = FragmentLabels.HOME.getLabelName();
        }
        this.setFragments(label,null);
    }

    /**
     * Set the fragment after check the application status
     * @param label fragment label to insert
     * @param data optional data to pass within the fragment
     */
    public void setFragments(String label, Bundle data){
        String fragment_pkg = this.mam.getFragmentPackage(label);
        Log.d("MainActivity","setFragment fragment package "+fragment_pkg);
        try {
            //Create fragment instance by string package
            Class<?> fragment_clazz = Class.forName(fragment_pkg);
            Constructor<?> frag_constr = fragment_clazz.getConstructor();
            boolean require_connection = this.mam.isConnectionRequired(label);
            if(require_connection){
                Log.i("MainActivity","setFragments require connection");
                //For this item internet is required
                boolean connected = this.mam.getConnectionStatus();
                if(connected){
                    Log.i("MainActivity","setFragments connection connected");
                    this.mav.updateFragment(R.id.main_activity_fragment_container, (Fragment)frag_constr.newInstance(),data);
                }//if(connected){
                else{
                    Log.i("MainActivity","setFragments connection not connected");
                    if(label.equalsIgnoreCase(FragmentLabels.FLIGHTS.getLabelName()))
                        label = FragmentLabels.HOME.getLabelName();
                    this.mav.updateFragment(R.id.main_activity_fragment_container, (Fragment) NoConnectionFragment.newInstance(label),data);
                }
            }//if(require_connection){
            else{
                Log.i("MainActivity","setFragments not require connection");
                this.mav.updateFragment(R.id.main_activity_fragment_container, (Fragment)frag_constr.newInstance(),data);
            }//else di if(require_connection){
        } catch (ClassNotFoundException | NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    /**
     * Set the main menu (logged or not logged menu
     */
    public void setMenu(){
        boolean userLogged = this.mam.isUserLogged();
        if(userLogged){
            //User is logged with its account
        }//if(userLogged){
        else{
            this.mav.updateFragment(R.id.main_menu_fragment_container, new MainMenuNotLoggedFragment(),null);
        }
    }

    //MainMenuFragment.OnMainMenuItemClick
    @Override
    public void mainMenuItemClick(String label) {
        this.setFragments(label,null);
    }

    //FragmentChange
    @Override
    public void fragmentChange(String oldFragmentLabel, String newFragmentLabel, boolean success, Bundle data){
        String nfl = newFragmentLabel;
        this.setFragments(nfl,data);
    }
}