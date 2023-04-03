package com.example.flightbooking.fragments.mainmenu.notlogged;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.flightbooking.R;
import com.example.flightbooking.fragments.mainmenu.information.InformationMenuAdapter;
import com.example.flightbooking.fragments.mainmenu.information.InformationMenuModel;
import com.example.flightbooking.fragments.mainmenu.information.InformationMenuView;
import com.example.flightbooking.models.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainMenuNotLoggedFragmentMethods {

    /**
     * Change menu visibility (show/hide) if orientation is portait
     * @param mmnlfm
     * @param mmnlfv
     * @param imv
     */
    public static void changeMenuVisibility(MainMenuNotLoggedFragmentModel mmnlfm, MainMenuNotLoggedFragmentView mmnlfv,InformationMenuView imv){
        int status = mmnlfm.getMenuStatus();
        if(status == MainMenuNotLoggedFragmentModel.MENU_HIDDEN){
            mmnlfm.setMenuStatus(MainMenuNotLoggedFragmentModel.MENU_SHOWN);
            mmnlfv.getMenu().setVisibility(View.VISIBLE);
            imv.getElvInfo().setVisibility(View.VISIBLE);
            mmnlfv.getShowHide().setText("Chiudi il menu");
        }//if(status == MainMenuModel.MENU_HIDDEN){
        else{
            mmnlfm.setMenuStatus(MainMenuNotLoggedFragmentModel.MENU_HIDDEN);
            mmnlfv.getMenu().setVisibility(View.GONE);
            imv.getElvInfo().setVisibility(View.GONE);
            mmnlfv.getShowHide().setText("Apri il menu");
        }
    }

    /**
     * Set the information expandable menu item
     * @param imm
     * @param imv
     * @param ctx
     * @param mmnlf
     */
    public static void setMenuInfoItems(InformationMenuModel imm, InformationMenuView imv, Context ctx, MainMenuNotLoggedFragment mmnlf){
        HashMap<String, List<MenuItem>> infoMenuMap = imm.getInfoSubmenuItems();
        List<String> infoMenuTitles = new ArrayList<String>(infoMenuMap.keySet());
        InformationMenuAdapter ima = new InformationMenuAdapter(ctx, infoMenuTitles, infoMenuMap);
        imv.getElvInfo().setAdapter(ima);
        imv.getElvInfo().setOnChildClickListener(mmnlf);
    }

    /**
     * Set the non information logged menu items part
     * @param mmnlfm
     * @param mmnlfv
     * @param ctx
     */
    public static void setMenuItems(MainMenuNotLoggedFragmentModel mmnlfm, MainMenuNotLoggedFragmentView mmnlfv, Context ctx){
        mmnlfm.setMenuStatus(MainMenuNotLoggedFragmentModel.MENU_HIDDEN);
        MainMenuNotLoggedFragmentAdapter mma = new MainMenuNotLoggedFragmentAdapter(ctx, R.layout.row,mmnlfm.getMenuItems());
        mmnlfv.getMenu().setAdapter(mma);
    }

    /**
     * Set listeners for menu item views
     * @param mmnlfv
     * @param mmnlf
     */
    public static void setMenuItemsListeners(MainMenuNotLoggedFragmentView mmnlfv, MainMenuNotLoggedFragment mmnlf){
        mmnlfv.getShowHide().setOnClickListener(mmnlf);
        mmnlfv.getMenu().setOnItemClickListener(mmnlf);
    }
}
