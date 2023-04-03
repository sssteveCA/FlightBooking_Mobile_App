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
        ExpandableListView elv_info = view.findViewById(R.id.main_menu_information_elv);
        this.setMenuItems(lv_1,bt_1);
        this.setMenuInfoItems(elv_info);
        this.setMenuItemsListeners();
        return view;
    }

    /**
     * Set the non information logged menu items part
     * @param lv_1
     * @param bt_1
     */
    private void setMenuItems(ListView lv_1, Button bt_1){
        this.mmnlfm = new MainMenuNotLoggedFragmentModel(this.ctx);
        this.mmnlfm.setMenuStatus(MainMenuNotLoggedFragmentModel.MENU_HIDDEN);
        MainMenuNotLoggedFragmentAdapter mma = new MainMenuNotLoggedFragmentAdapter(this.ctx,R.layout.row,this.mmnlfm.getMenuItems());
        this.mmnlfv = new MainMenuNotLoggedFragmentView(lv_1,bt_1);
        this.mmnlfv.getMenu().setAdapter(mma);
    }

    /**
     * Set the information expandable menu item
     * @param elv_info
     */
    private void setMenuInfoItems(ExpandableListView elv_info){
        this.imm = new InformationMenuModel();
        this.imv = new InformationMenuView(elv_info);
        HashMap<String, List<MenuItem>> infoMenuMap = this.imm.getInfoSubmenuItems();
        List<String> infoMenuTitles = new ArrayList<String>(infoMenuMap.keySet());
        InformationMenuAdapter ima = new InformationMenuAdapter(this.ctx, infoMenuTitles, infoMenuMap);
        this.imv.getElvInfo().setAdapter(ima);
        this.imv.getElvInfo().setOnChildClickListener(this);
    }

    /**
     * Set listeners for menu item views
     */
    private void setMenuItemsListeners(){
        this.mmnlfv.getShowHide().setOnClickListener(this);
        this.mmnlfv.getMenu().setOnItemClickListener(this);
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {

        switch(view.getId()){
            case R.id.main_menu_frag_bt_1:
                this.changeMenuVisibility();
                break;
            default:
                break;
        }
    }

    /**
     * Change menu visibility (show/hide) if orientation is portait
     */
    public void changeMenuVisibility(){
        int status = this.mmnlfm.getMenuStatus();
        if(status == MainMenuNotLoggedFragmentModel.MENU_HIDDEN){
            this.mmnlfm.setMenuStatus(MainMenuNotLoggedFragmentModel.MENU_SHOWN);
            this.mmnlfv.getMenu().setVisibility(View.VISIBLE);
            this.imv.getElvInfo().setVisibility(View.VISIBLE);
            this.mmnlfv.getShowHide().setText("Chiudi il menu");
        }//if(status == MainMenuModel.MENU_HIDDEN){
        else{
            this.mmnlfm.setMenuStatus(MainMenuNotLoggedFragmentModel.MENU_HIDDEN);
            this.mmnlfv.getMenu().setVisibility(View.GONE);
            this.imv.getElvInfo().setVisibility(View.GONE);
            this.mmnlfv.getShowHide().setText("Apri il menu");
        }
    }

    //AdapterView.OnItemClickListener
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MenuItem mi = (MenuItem) adapterView.getItemAtPosition(i);
        String label = mi.getLabel();
        this.changeMenuVisibility();
        this.mmnlfm.setLastLabelClicked(label);
        this.itemClickListener.mainMenuItemClick(label, null);
    }

    //ExpandableListView.OnChildClickListener
    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
        MenuItem mi = (MenuItem) expandableListView.getExpandableListAdapter().getChild(i,i1);
        String label = mi.getLabel();
        if(Arrays.asList(InformationMenuModel.items).contains(label)){
            this.changeMenuVisibility();
            this.mmnlfm.setLastLabelClicked(label);
            this.itemClickListener.mainMenuItemClick(label, null);
        }
        return false;
    }
}