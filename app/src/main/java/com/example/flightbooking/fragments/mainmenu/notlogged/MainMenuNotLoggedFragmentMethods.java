package com.example.flightbooking.fragments.mainmenu.notlogged;

import android.content.Context;
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
}
