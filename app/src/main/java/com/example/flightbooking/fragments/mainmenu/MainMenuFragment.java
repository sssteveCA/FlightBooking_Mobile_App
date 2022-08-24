package com.example.flightbooking.fragments.mainmenu;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.models.MenuItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{

    //When user click on main menu item
    public interface OnMainMenuItemClick{
        public void mainMenuItemClick(String label);
    }

    private MainMenuModel mmm;
    private MainMenuView mmv;
    public OnMainMenuItemClick itemClickListener = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MainMenuFragment() {
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
    public static MainMenuFragment newInstance(String param1, String param2) {
        MainMenuFragment fragment = new MainMenuFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);
        Button bt_1 = view.findViewById(R.id.main_menu_frag_bt_1);
        ListView lv_1 = view.findViewById(R.id.main_menu_frag_lv_1);
        this.mmm = new MainMenuModel(getActivity());
        this.mmm.setMenu(); //Set ArrayList to create the ListView
        this.mmm.setMenuStatus(MainMenuModel.MENU_HIDDEN);
        MainMenuAdapter mma = new MainMenuAdapter(getActivity(),R.layout.row,this.mmm.getMenuItems());
        this.mmv = new MainMenuView(lv_1,bt_1);
        this.mmv.getMenu().setAdapter(mma);
        this.mmv.getShowHide().setOnClickListener(this);
        this.mmv.getMenu().setOnItemClickListener(this);
        return view;
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
     * Change menu visibility (show/hide) on button click
     */
    public void changeMenuVisibility(){
        int status = this.mmm.getMenuStatus();
        if(status == MainMenuModel.MENU_HIDDEN){
            this.mmm.setMenuStatus(MainMenuModel.MENU_SHOWN);
            this.mmv.getMenu().setVisibility(View.VISIBLE);
            this.mmv.getShowHide().setText("Chiudi il menu");
        }//if(status == MainMenuModel.MENU_HIDDEN){
        else{
            this.mmm.setMenuStatus(MainMenuModel.MENU_HIDDEN);
            this.mmv.getMenu().setVisibility(View.GONE);
            this.mmv.getShowHide().setText("Apri il menu");
        }
    }

    //AdapterView.OnItemClickListener
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MenuItem mi = (MenuItem) adapterView.getItemAtPosition(i);
        String label = mi.getLabel();
        Log.i("MainMenuFragment","onItemClick label => "+label);
        this.itemClickListener.mainMenuItemClick(label);
    }
}