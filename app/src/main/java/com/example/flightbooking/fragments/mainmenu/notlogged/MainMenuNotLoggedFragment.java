package com.example.flightbooking.fragments.mainmenu.notlogged;

import android.app.Activity;
import android.content.Context;
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
import com.example.flightbooking.fragments.mainmenu.information.InformationMenuAdapter;
import com.example.flightbooking.fragments.mainmenu.information.InformationMenuModel;
import com.example.flightbooking.fragments.mainmenu.information.InformationMenuView;
import com.example.flightbooking.interfaces.OnMainMenuItemClick;
import com.example.flightbooking.models.MenuItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuNotLoggedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuNotLoggedFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener, ExpandableListView.OnChildClickListener{

    private MainMenuNotLoggedFragmentModel mmnlfm;
    private MainMenuNotLoggedFragmentView mmnlfv;
    private InformationMenuModel imm;
    private InformationMenuView imv;
    private Context ctx;
    public OnMainMenuItemClick itemClickListener = null;

    public MainMenuNotLoggedFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainMenuFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainMenuNotLoggedFragment newInstance(String param1, String param2) {
        MainMenuNotLoggedFragment fragment = new MainMenuNotLoggedFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.ctx = context;
        this.mmnlfm = new MainMenuNotLoggedFragmentModel(this.ctx);
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu_not_logged, container, false);
        Button bt_1 = view.findViewById(R.id.main_menu_frag_bt_1);
        ListView lv_1 = view.findViewById(R.id.main_menu_frag_lv_1);
        this.mmnlfv = new MainMenuNotLoggedFragmentView(lv_1,bt_1);
        MainMenuNotLoggedFragmentMethods.setMenuItems(this.mmnlfm,this.mmnlfv,this.ctx);
        ExpandableListView elv_info = view.findViewById(R.id.main_menu_information_elv);
        this.imm = new InformationMenuModel();
        this.imv = new InformationMenuView(elv_info);
        MainMenuNotLoggedFragmentMethods.setMenuInfoItems(this.imm,this.imv,this.ctx,this);
        MainMenuNotLoggedFragmentMethods.setMenuItemsListeners(this.mmnlfv,this);
        return view;
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.main_menu_frag_bt_1:
                MainMenuNotLoggedFragmentMethods.changeMenuVisibility(this.mmnlfm,this.mmnlfv,this.imv);
                break;
            default:
                break;
        }
    }

    //AdapterView.OnItemClickListener
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MenuItem mi = (MenuItem) adapterView.getItemAtPosition(i);
        String label = mi.getLabel();
        MainMenuNotLoggedFragmentMethods.changeMenuVisibility(this.mmnlfm,this.mmnlfv,this.imv);
        this.mmnlfm.setLastLabelClicked(label);
        this.itemClickListener.mainMenuItemClick(label, null);
    }

    //ExpandableListView.OnChildClickListener
    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        MenuItem mi = (MenuItem) expandableListView.getExpandableListAdapter().getChild(i,i1);
        String label = mi.getLabel();
        if(Arrays.asList(InformationMenuModel.items).contains(label)){
            MainMenuNotLoggedFragmentMethods.changeMenuVisibility(this.mmnlfm,this.mmnlfv,this.imv);
            this.mmnlfm.setLastLabelClicked(label);
            this.itemClickListener.mainMenuItemClick(label, null);
        }
        return false;
    }
}