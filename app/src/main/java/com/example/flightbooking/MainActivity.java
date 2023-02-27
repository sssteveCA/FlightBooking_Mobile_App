package com.example.flightbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.fragments.mainmenu.logged.MainMenuLoggedFragment;
import com.example.flightbooking.fragments.mainmenu.notlogged.MainMenuNotLoggedFragment;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.interfaces.LoginObserver;
import com.example.flightbooking.interfaces.OnMainMenuItemClick;
import com.example.flightbooking.fragments.noconnection.NoConnectionFragment;
import com.example.flightbooking.models.response.Message;
import com.example.flightbooking.models.response.login.Auth;
import com.example.flightbooking.requests.logout.LogoutModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MainActivity extends AppCompatActivity implements NoConnectionFragment.RetryListener, OnMainMenuItemClick, FragmentChange, LoginObserver {

    private MainActivityModel mam;
    private MainActivityView mav;
    private Bundle auth_data;
    private Auth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mam = new MainActivityModel(this);
        this.mav = new MainActivityView(this);
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
        if(label != null){
            if(label.equalsIgnoreCase(FragmentLabels.FLIGHTS.getLabelName()) || label.equalsIgnoreCase(FragmentLabels.TICKET_PREVIEW.getLabelName())){
                label = FragmentLabels.HOME.getLabelName();
            }
        }
        else label = FragmentLabels.HOME.getLabelName();
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
                //Log.i("MainActivity","setFragments require connection");
                //For this item internet is required
                boolean connected = this.mam.getConnectionStatus();
                if(connected){
                    //Log.i("MainActivity","setFragments connection connected");
                    this.mav.updateFragment(R.id.main_activity_fragment_container, (Fragment)frag_constr.newInstance(),data);
                }//if(connected){
                else{
                    //Log.i("MainActivity","setFragments connection not connected");
                    if(label.equalsIgnoreCase(FragmentLabels.FLIGHTS.getLabelName()))
                        label = FragmentLabels.HOME.getLabelName();
                        Bundle noconnBund = new Bundle();
                        noconnBund.putString("label",label);
                        NoConnectionFragment ncf = new NoConnectionFragment();
                    this.mav.updateFragment(R.id.main_activity_fragment_container, ncf, data);
                }
            }//if(require_connection){
            else{
                //Log.i("MainActivity","setFragments not require connection");
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
            this.mav.updateFragment(R.id.main_menu_fragment_container, new MainMenuLoggedFragment(), this.auth_data);
        }//if(userLogged){
        else{
            this.mav.updateFragment(R.id.main_menu_fragment_container, new MainMenuNotLoggedFragment(),null);
        }
    }

    //FragmentChange
    @Override
    public void fragmentChange(String oldFragmentLabel, String newFragmentLabel, boolean success, Bundle data){
        String nfl = newFragmentLabel;
        this.setFragments(nfl,data);
    }

    @Override
    public void onLogin(String label, Bundle auth_data) {
        this.auth = (Auth) auth_data.getSerializable("auth");
        /*Log.d("MainActivity", "auth status => "+auth.status);
        Log.d("MainActivity", "auth user name => "+auth.user.name);*/
        this.auth_data = auth_data;
        this.mam.setUserLogged(true);
        this.setMenu();
        this.setFragments(label,auth_data);
    }

    @Override
    public void onLogout(String label, Bundle auth_data) {
        MainActivity this_ma = this;
        this.auth = (Auth)auth_data.getSerializable("auth");
        this.auth_data = auth_data;
        //Log.d("MainActivity", "onLogout token => "+this.auth.token);
        LogoutModel lm = new LogoutModel(this.auth.token);
        lm.logoutRequest(new LogoutModel.LogoutResponse() {
            @Override
            public void logoutResponse(Message message) {
                this_ma.mam.setUserLogged(false);
                this_ma.auth_data = null;
                this_ma.auth = null;
                this_ma.setMenu();
                this_ma.setFragments(label,null);
            }
            @Override
            public void logoutError(String message) {
                //MessageDialog md = new MessageDialog(this_ma,"Logout",message);
                //Temporary (logout request problem)
                this_ma.mam.setUserLogged(false);
                this_ma.auth_data = null;
                this_ma.auth = null;
                this_ma.setMenu();
                this_ma.setFragments(label,null);
            }
        });

    }

    //OnMainMenuItemClick
    @Override
    public void mainMenuItemClick(String label, Bundle data) {this.setFragments(label,data);}
}