package com.example.flightbooking.fragments.subscribe;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.flightbooking.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubscribeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubscribeFragment extends Fragment implements View.OnClickListener {

    private SubscribeFragmentModel sfm;
    private SubscribeFragmentView sfv;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SubscribeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SubscribeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubscribeFragment newInstance(String param1, String param2) {
        SubscribeFragment fragment = new SubscribeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        View view = inflater.inflate(R.layout.fragment_subscribe, container, false);
        EditText et_username = view.findViewById(R.id.frag_subsc_et_username);
        EditText et_email_address = view.findViewById(R.id.frag_subsc_et_email_address);
        EditText et_email_address_conf = view.findViewById(R.id.frag_subsc_et_email_address_conf);
        EditText et_password = view.findViewById(R.id.frag_subsc_et_password);
        EditText et_password_conf = view.findViewById(R.id.frag_subsc_et_password);
        CheckBox cb_show_pass = view.findViewById(R.id.frag_subsc_et_password_conf);
        Button bt_subscribe = view.findViewById(R.id.frag_subsc_bt_subscribe);
        Button bt_reset = view.findViewById(R.id.frag_subsc_bt_reset);
        this.sfv = new SubscribeFragmentView(et_username,et_email_address,et_email_address_conf,et_password,et_password_conf,cb_show_pass,bt_subscribe,bt_reset);
        this.sfv.getBtSubscribe().setOnClickListener(this);
        this.sfv.getBtReset().setOnClickListener(this);
        return view;
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_subsc_bt_subscribe:
                break;
            case R.id.frag_subsc_bt_reset:
                break;
        }
    }
}