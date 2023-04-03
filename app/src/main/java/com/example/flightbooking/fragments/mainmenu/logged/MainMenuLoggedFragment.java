package com.example.flightbooking.fragments.mainmenu.logged;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.fragments.mainmenu.information.InformationMenuAdapter;
import com.example.flightbooking.fragments.mainmenu.information.InformationMenuModel;
import com.example.flightbooking.fragments.mainmenu.information.InformationMenuView;
import com.example.flightbooking.fragments.mainmenu.logged.profile.MainMenuLoggedProfileAdapter;
import com.example.flightbooking.fragments.mainmenu.logged.profile.MainMenuLoggedProfileModel;
import com.example.flightbooking.fragments.mainmenu.logged.profile.MainMenuLoggedProfileView;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.interfaces.LoginObserver;
import com.example.flightbooking.interfaces.OnMainMenuItemClick;
import com.example.flightbooking.models.MenuItem;
import com.example.flightbooking.models.response.login.Auth;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuLoggedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuLoggedFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, ExpandableListView.OnChildClickListener {

    private MainMenuLoggedFragmentModel mmlfm;
    private MainMenuLoggedFragmentView mmlfv;
    private MainMenuLoggedProfileModel mmlpm;
    private MainMenuLoggedProfileView mmlpv;
    private InformationMenuModel imm;
    private InformationMenuView imv;
    private Context ctx;
    private Auth auth = null;
    public OnMainMenuItemClick itemClickListener = null;
    public LoginObserver lo = null;

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
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.ctx = context;
        this.mmlfm = new MainMenuLoggedFragmentModel(this.ctx);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.itemClickListener = (MainActivity)activity;
        this.lo = (MainActivity)activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.auth = (Auth) getArguments().getSerializable(Globals.KEY_AUTH);
            //Log.d("MainMenuLoggedFragment", "auth status => "+auth.status);
            //Log.d("MainMenuLoggedFragment", "auth user name => "+auth.user.name);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu_logged, container, false);
        Button bt_1 = view.findViewById(R.id.main_menu_logged_frag_bt_1);
        ListView lv_1 = view.findViewById(R.id.main_menu_logged_frag_lv_1);
        ExpandableListView elv_profile = view.findViewById(R.id.main_menu_logged_frag_profile_elv);
        ExpandableListView elv_info = view.findViewById(R.id.main_menu_logged_information_elv);
        this.mmlfv = new MainMenuLoggedFragmentView(lv_1,bt_1);
        MainMenuLoggedFragmentMethods.setMenuItems(this.mmlfm,this.mmlfv,this.ctx);
        this.mmlpm = new MainMenuLoggedProfileModel(this.auth);
        this.mmlpv = new MainMenuLoggedProfileView(elv_profile);
        MainMenuLoggedFragmentMethods.setMenuProfileItems(this.mmlpm,this.mmlpv,this.ctx,this);
        this.imm = new InformationMenuModel();
        this.imv = new InformationMenuView(elv_info);
        MainMenuLoggedFragmentMethods.setMenuInfoItems(this.imm,this.imv,this.ctx,this);
        MainMenuLoggedFragmentMethods.setMenuItemsListeners(this.mmlfv,this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.main_menu_logged_frag_bt_1:
                MainMenuLoggedFragmentMethods.changeMenuVisibility(this.mmlfm,this.mmlfv,this.mmlpv,this.imv);
                break;
        }
    }

    //AdapterView.OnItemClickListener
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MenuItem mi = (MenuItem) adapterView.getItemAtPosition(i);
        String label = mi.getLabel();
        MainMenuLoggedFragmentMethods.changeMenuVisibility(this.mmlfm,this.mmlfv,this.mmlpv,this.imv);
        this.mmlfm.setLastLabelClicked(label);
        this.itemClickListener.mainMenuItemClick(label, null);
    }

    //ExpandableListView.OnChildClickListener
    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        MainMenuLoggedFragment this_mmlf = this;
        MenuItem mi = (MenuItem) expandableListView.getExpandableListAdapter().getChild(i,i1);
        String label = mi.getLabel();
        //Log.d("MainMenuLoggedFragment", "on child click label => "+label);
        if(label.equals(MainMenuLoggedProfileModel.items[2])){{
            //Logout menu item click
            ConfirmDialog cd = new ConfirmDialog(this_mmlf.ctx,"Esci dalla sessione","Sei sicuro di voler chiudere la sessione?");
            cd.setDialog(new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    Bundle data = new Bundle();
                    data.putSerializable(Globals.KEY_AUTH,this_mmlf.auth);
                    this_mmlf.lo.onLogout(FragmentLabels.HOME.getLabelName(), data);
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        }}//if(label.equals(MainMenuLoggedProfileModel.items[2])){{
        else if(Arrays.asList(InformationMenuModel.items).contains(label)){
            MainMenuLoggedFragmentMethods.changeMenuVisibility(this.mmlfm,this.mmlfv,this.mmlpv,this.imv);
            this.mmlfm.setLastLabelClicked(label);
            this.itemClickListener.mainMenuItemClick(label, null);
        }
        return false;
    }
}