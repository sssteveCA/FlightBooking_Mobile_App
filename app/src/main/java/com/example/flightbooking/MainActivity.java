package com.example.flightbooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.example.flightbooking.activities.close.CloseActivity;
import com.example.flightbooking.dialogs.ConfirmDialog;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.fragments.mainmenu.logged.MainMenuLoggedFragment;
import com.example.flightbooking.fragments.mainmenu.notlogged.MainMenuNotLoggedFragment;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.interfaces.Globals;
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
    public void onBackPressed() {
        ConfirmDialog cd = new ConfirmDialog(this,"Esci dall'applicazione","Sei sicuro di voler chiudere l'applicazione?");
        cd.setDialog(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Intent exitIntent = new Intent(MainActivity.this, CloseActivity.class);
                exitIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(exitIntent);
            }
        }, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
    }

    @Override
    public void retryClick(String label) {
        //NoConnection fragment Retry button clicked
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
        try {
            //Create fragment instance by string package
            Class<?> fragment_clazz = Class.forName(fragment_pkg);
            Constructor<?> frag_constr = fragment_clazz.getConstructor();
            boolean require_connection = this.mam.isConnectionRequired(label);
            if(require_connection){
                //For this item internet is required
                boolean connected = this.mam.getConnectionStatus();
                if(connected){
                    this.mav.updateFragment(R.id.main_activity_fragment_container, (Fragment)frag_constr.newInstance(),data);
                }//if(connected){
                else{
                    if(label.equalsIgnoreCase(FragmentLabels.FLIGHTS.getLabelName()))
                        label = FragmentLabels.HOME.getLabelName();
                        Bundle noconnBund = new Bundle();
                        noconnBund.putString("label",label);
                        NoConnectionFragment ncf = new NoConnectionFragment();
                    this.mav.updateFragment(R.id.main_activity_fragment_container, ncf, data);
                }
            }//if(require_connection){
            else{
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
        if(data != null){
            data.putSerializable(Globals.KEY_AUTH,this.auth);
            this.setFragments(nfl,data);
        }
        else{
            Bundle bundle = new Bundle();
            bundle.putSerializable(Globals.KEY_AUTH,this.auth);
            this.setFragments(nfl,bundle);
        }

    }

    @Override
    public void onLogin(String label, Bundle auth_data) {
        this.auth = (Auth) auth_data.getSerializable(Globals.KEY_AUTH);
        this.auth_data = auth_data;
        this.mam.setUserLogged(true);
        this.setMenu();
        this.setFragments(label,auth_data);
    }

    @Override
    public void onLogout(String label, Bundle auth_data) {
        MainActivity this_ma = this;
        this.auth = (Auth)auth_data.getSerializable(Globals.KEY_AUTH);
        this.auth_data = auth_data;
        String token = this.auth != null ? this.auth.token : "";
        LogoutModel lm = new LogoutModel(token);
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