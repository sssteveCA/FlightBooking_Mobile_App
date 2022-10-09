package com.example.flightbooking.fragments.mainmenu.logged;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.dialogs.ConfirmDialog;
import com.example.flightbooking.fragments.mainmenu.logged.profile.MainMenuLoggedProfileAdapter;
import com.example.flightbooking.fragments.mainmenu.logged.profile.MainMenuLoggedProfileModel;
import com.example.flightbooking.fragments.mainmenu.logged.profile.MainMenuLoggedProfileView;
import com.example.flightbooking.interfaces.OnMainMenuItemClick;
import com.example.flightbooking.models.MenuItem;
import com.example.flightbooking.models.login.Auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuLoggedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuLoggedFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, ExpandableListView.OnChildClickListener {

    private MainMenuLoggedModel mmlm;
    private MainMenuLoggedView mmlv;
    private MainMenuLoggedProfileModel mmlpm;
    private MainMenuLoggedProfileView mmlpv;
    private Context ctx;
    private Auth auth = null;
    public OnMainMenuItemClick itemClickListener = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenuLoggedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuLoggedFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuLoggedFragment newInstance(String param1, String param2) {
        MainMenuLoggedFragment fragment = new MainMenuLoggedFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.ctx = context;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.itemClickListener = (MainActivity)activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.auth = (Auth) getArguments().getSerializable("auth");
            Log.d("MainMenuLoggedFragment", "auth status => "+auth.status);
            Log.d("MainMenuLoggedFragment", "auth user name => "+auth.user.name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu_logged, container, false);
        Button bt_1 = view.findViewById(R.id.main_menu_logged_frag_bt_1);
        ListView lv_1 = view.findViewById(R.id.main_menu_logged_frag_lv_1);
        ExpandableListView elv_1 = view.findViewById(R.id.main_menu_logged_frag_profile_elv);
        this.setMenuItems(lv_1,bt_1);
        this.setMenuProfileItems(elv_1);
        this.setMenuItemsListeners();
        return view;
    }

    /**
     * Set the non profile logged menu items part
     * @param lv_1
     * @param bt_1
     */
    private void setMenuItems(ListView lv_1, Button bt_1){
        this.mmlm = new MainMenuLoggedModel(this.ctx);
        this.mmlm.setMenuStatus(MainMenuLoggedModel.MENU_HIDDEN);
        MainMenuLoggedAdapter mmla = new MainMenuLoggedAdapter(this.ctx, R.layout.row_logged,this.mmlm.getMenuItems());
        this.mmlv = new MainMenuLoggedView(lv_1,bt_1);
        this.mmlv.getMenu().setAdapter(mmla);
    }

    /**
     * Set the profile logged menu items part
     * @param elv_1
     */
    private void setMenuProfileItems(ExpandableListView elv_1){
        this.mmlpm = new MainMenuLoggedProfileModel(this.auth);
        this.mmlpv = new MainMenuLoggedProfileView(elv_1);
        HashMap<String, List<MenuItem>> profileMenuMap = this.mmlpm.getProfileSubmenuItems();
        List<String> profileMenuTitles = new ArrayList<String>(this.mmlpm.getProfileSubmenuItems().keySet());
        MainMenuLoggedProfileAdapter mmlpa = new MainMenuLoggedProfileAdapter(this.ctx, profileMenuTitles, profileMenuMap);
        this.mmlpv.getElvProfile().setAdapter(mmlpa);
        this.mmlpv.getElvProfile().setOnChildClickListener(this);
    }

    /**
     * Set listeners for menu item views
     */
    private void setMenuItemsListeners(){
        this.mmlv.getShowHide().setOnClickListener(this);
        this.mmlv.getMenu().setOnItemClickListener(this);
    }

    /**
     * Change menu visibility (show/hide)
     */
    public void changeMenuVisibility(){
        int status = this.mmlm.getMenuStatus();
        if(status == MainMenuLoggedModel.MENU_HIDDEN){
            this.mmlm.setMenuStatus(MainMenuLoggedModel.MENU_SHOWN);
            this.mmlv.getMenu().setVisibility(View.VISIBLE);
            this.mmlpv.getElvProfile().setVisibility(View.VISIBLE);
            this.mmlv.getShowHide().setText("Chiudi il menu");
        }//if(status == MainMenuModel.MENU_HIDDEN){
        else{
            this.mmlm.setMenuStatus(MainMenuLoggedModel.MENU_HIDDEN);
            this.mmlv.getMenu().setVisibility(View.GONE);
            this.mmlpv.getElvProfile().setVisibility(View.GONE);
            this.mmlv.getShowHide().setText("Apri il menu");
        }
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.main_menu_logged_frag_bt_1:
                this.changeMenuVisibility();
                break;
        }
    }

    //AdapterView.OnItemClickListener
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MenuItem mi = (MenuItem) adapterView.getItemAtPosition(i);
        String label = mi.getLabel();
        this.changeMenuVisibility();
        this.mmlm.setLastLabelClicked(label);
        this.itemClickListener.mainMenuItemClick(label, null);
    }

    //ExpandableListView.OnChildClickListener
    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        MenuItem mi = (MenuItem) this.mmlpm.getProfileSubmenuItems().get(i).get(i1);
        String label = mi.getLabel();
        Log.d("MainMenuLoggedFragment", "on child click label => "+label);
        if(label.equals(MainMenuLoggedProfileModel.items[2])){{
            //Logout menu item click
            ConfirmDialog cd = new ConfirmDialog(this.ctx,"Esci dalla sessione","Sei sicuro di voler chiudere la sessione?");
            cd.setDialog(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        }}
        return false;
    }
}