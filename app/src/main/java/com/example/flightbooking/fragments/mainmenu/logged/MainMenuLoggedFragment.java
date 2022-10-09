package com.example.flightbooking.fragments.mainmenu.logged;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.interfaces.OnMainMenuItemClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainMenuLoggedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainMenuLoggedFragment extends Fragment implements View.OnClickListener {

    private MainMenuLoggedModel mmlm;
    private MainMenuLoggedView mmlv;
    private Context ctx;
    public OnMainMenuItemClick itemClickListener = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_menu_logged, container, false);
        Button bt_1 = view.findViewById(R.id.main_menu_logged_frag_bt_1);
        ListView lv_1 = view.findViewById(R.id.main_menu_logged_frag_lv_1);
        this.mmlm = new MainMenuLoggedModel(this.ctx);
        this.mmlv = new MainMenuLoggedView(lv_1,bt_1);
        this.mmlv.getShowHide().setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.main_menu_logged_frag_bt_1:
                break;
        }
    }
}