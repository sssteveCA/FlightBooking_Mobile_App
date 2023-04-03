package com.example.flightbooking.fragments.mainmenu.logged;

import android.content.Context;
import android.widget.Button;
import android.widget.ListView;

import com.example.flightbooking.R;
import com.example.flightbooking.fragments.mainmenu.notlogged.MainMenuNotLoggedFragmentAdapter;
import com.example.flightbooking.fragments.mainmenu.notlogged.MainMenuNotLoggedFragmentModel;
import com.example.flightbooking.fragments.mainmenu.notlogged.MainMenuNotLoggedFragmentView;

public class MainMenuLoggedFragmentMethods {

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
}
