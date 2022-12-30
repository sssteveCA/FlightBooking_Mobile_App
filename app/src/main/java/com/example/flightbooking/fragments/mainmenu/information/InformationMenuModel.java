package com.example.flightbooking.fragments.mainmenu.information;

import com.example.flightbooking.models.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InformationMenuModel {

    /**
     * Profile submenu item labels
     */
    public static final String[] items = {
      "Privacy policy", "Cookie policy", "Termini e condizioni"
    };

    private HashMap<String, List<MenuItem>> info_submenu_items;

    public InformationMenuModel(){
        this.info_submenu_items = new HashMap<>();
    }

    public HashMap<String, List<MenuItem>> getInfoSubmenuItems(){return this.info_submenu_items;}

    /**
     * Set the submenu information items list
     */
    private void setMenu(){
        List<MenuItem> elv_items = new ArrayList<>();
        for(String label: InformationMenuModel.items){
            MenuItem mi = new MenuItem(label);
            elv_items.add(mi);
        }
        this.info_submenu_items.put("Informativa",elv_items);
    }
}
