package com.example.flightbooking.fragments.mainmenu.logged;

import android.content.Context;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.flightbooking.R;
import com.example.flightbooking.fragments.mainmenu.information.InformationMenuAdapter;
import com.example.flightbooking.fragments.mainmenu.information.InformationMenuModel;
import com.example.flightbooking.fragments.mainmenu.information.InformationMenuView;
import com.example.flightbooking.fragments.mainmenu.logged.profile.MainMenuLoggedProfileAdapter;
import com.example.flightbooking.fragments.mainmenu.logged.profile.MainMenuLoggedProfileModel;
import com.example.flightbooking.fragments.mainmenu.logged.profile.MainMenuLoggedProfileView;
import com.example.flightbooking.fragments.mainmenu.notlogged.MainMenuNotLoggedFragmentAdapter;
import com.example.flightbooking.fragments.mainmenu.notlogged.MainMenuNotLoggedFragmentModel;
import com.example.flightbooking.fragments.mainmenu.notlogged.MainMenuNotLoggedFragmentView;
import com.example.flightbooking.models.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainMenuLoggedFragmentMethods {

    /**
     * Set the information expandable menu item
     * @param imm
     * @param imv
     * @param ctx
     * @param mmlf
     */
    public static void setMenuInfoItems(InformationMenuModel imm, InformationMenuView imv, Context ctx, MainMenuLoggedFragment mmlf){

        HashMap<String, List<MenuItem>> infoMenuMap = imm.getInfoSubmenuItems();
        List<String> infoMenuTitles = new ArrayList<String>(infoMenuMap.keySet());
        InformationMenuAdapter ima = new InformationMenuAdapter(ctx, infoMenuTitles, infoMenuMap);
        imv.getElvInfo().setAdapter(ima);
        imv.getElvInfo().setOnChildClickListener(mmlf);
    }

    /**
     * Set the non profile/non information logged menu items part
     * @param lv_1
     * @param bt_1
     */
    public static void setMenuItems(ListView lv_1, Button bt_1, MainMenuLoggedFragmentModel mmlfm, MainMenuLoggedFragmentView mmlfv, Context ctx){
        mmlfm.setMenuStatus(MainMenuLoggedFragmentModel.MENU_HIDDEN);
        MainMenuLoggedFragmentAdapter mmla = new MainMenuLoggedFragmentAdapter(ctx, R.layout.row_logged,mmlfm.getMenuItems());
        mmlfv = new MainMenuLoggedFragmentView(lv_1,bt_1);
        mmlfv.getMenu().setAdapter(mmla);
    }

    /**
     * Set listeners for menu item views
     */
    public static void setMenuItemsListeners(MainMenuLoggedFragmentView mmlfv, MainMenuLoggedFragment mmlf){
        mmlfv.getShowHide().setOnClickListener(mmlf);
        mmlfv.getMenu().setOnItemClickListener(mmlf);
    }

    /**
     * Set the profile logged menu items part
     * @param mmlpm
     * @param mmlpv
     * @param ctx
     * @param mmlf
     */
    public static void setMenuProfileItems(MainMenuLoggedProfileModel mmlpm, MainMenuLoggedProfileView mmlpv, Context ctx, MainMenuLoggedFragment mmlf){
        HashMap<String, List<MenuItem>> profileMenuMap = mmlpm.getProfileSubmenuItems();
        List<String> profileMenuTitles = new ArrayList<String>(profileMenuMap.keySet());
        MainMenuLoggedProfileAdapter mmlpa = new MainMenuLoggedProfileAdapter(ctx, profileMenuTitles, profileMenuMap);
        mmlpv.getElvProfile().setAdapter(mmlpa);
        mmlpv.getElvProfile().setOnChildClickListener(mmlf);
    }
}
