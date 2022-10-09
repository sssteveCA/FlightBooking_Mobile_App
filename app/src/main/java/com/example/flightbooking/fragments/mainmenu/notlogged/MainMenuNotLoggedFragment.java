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
import android.widget.ListView;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.interfaces.OnMainMenuItemClick;
import com.example.flightbooking.models.MenuItem;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuNotLoggedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuNotLoggedFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemClickListener{

    private MainMenuNotLoggedModel mmnlm;
    private MainMenuNotLoggedView mmnlv;
    private Context ctx;
    public OnMainMenuItemClick itemClickListener = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.ctx = ctx;
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
        View view = inflater.inflate(R.layout.fragment_main_menu_not_logged, container, false);
        Button bt_1 = view.findViewById(R.id.main_menu_frag_bt_1);
        ListView lv_1 = view.findViewById(R.id.main_menu_frag_lv_1);
        this.mmnlm = new MainMenuNotLoggedModel(this.ctx);
        this.mmnlm.setMenu(); //Set ArrayList to create the ListView
        this.mmnlm.setMenuStatus(MainMenuNotLoggedModel.MENU_HIDDEN);
        MainMenuNotLoggedAdapter mma = new MainMenuNotLoggedAdapter(this.ctx,R.layout.row,this.mmnlm.getMenuItems());
        this.mmnlv = new MainMenuNotLoggedView(lv_1,bt_1);
        this.mmnlv.getMenu().setAdapter(mma);
        this.mmnlv.getShowHide().setOnClickListener(this);
        this.mmnlv.getMenu().setOnItemClickListener(this);
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
     * Change menu visibility (show/hide)
     */
    public void changeMenuVisibility(){
        int status = this.mmnlm.getMenuStatus();
        if(status == MainMenuNotLoggedModel.MENU_HIDDEN){
            this.mmnlm.setMenuStatus(MainMenuNotLoggedModel.MENU_SHOWN);
            this.mmnlv.getMenu().setVisibility(View.VISIBLE);
            this.mmnlv.getShowHide().setText("Chiudi il menu");
        }//if(status == MainMenuModel.MENU_HIDDEN){
        else{
            this.mmnlm.setMenuStatus(MainMenuNotLoggedModel.MENU_HIDDEN);
            this.mmnlv.getMenu().setVisibility(View.GONE);
            this.mmnlv.getShowHide().setText("Apri il menu");
        }
    }

    //AdapterView.OnItemClickListener
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        MenuItem mi = (MenuItem) adapterView.getItemAtPosition(i);
        String label = mi.getLabel();
        this.changeMenuVisibility();
        this.mmnlm.setLastLabelClicked(label);
        this.itemClickListener.mainMenuItemClick(label);
    }
}