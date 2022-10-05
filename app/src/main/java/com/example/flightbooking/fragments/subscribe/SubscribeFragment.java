package com.example.flightbooking.fragments.subscribe;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.example.flightbooking.R;
import com.example.flightbooking.dialogs.ConfirmDialog;
import com.example.flightbooking.dialogs.MessageDialog;
import com.example.flightbooking.interfaces.Globals;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubscribeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubscribeFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

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
        this.sfm = new SubscribeFragmentModel();
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
        CheckBox cb_show_pass = view.findViewById(R.id.frag_subsc_cb_show_pass);
        Button bt_subscribe = view.findViewById(R.id.frag_subsc_bt_subscribe);
        Button bt_reset = view.findViewById(R.id.frag_subsc_bt_reset);
        this.sfv = new SubscribeFragmentView(et_username,et_email_address,et_email_address_conf,et_password,et_password_conf,cb_show_pass,bt_subscribe,bt_reset);
        this.sfv.getBtSubscribe().setOnClickListener(this);
        this.sfv.getBtReset().setOnClickListener(this);
        return view;
    }

    /**
     * Check if subscribe inputs has valid pattern
     * @return true if data are all valid, false otherwise
     */
    private boolean validateSubscribe(){
        Map<String, String> subscribeValues = Stream.of(new Object[][]{
                {"username", this.sfv.getEtUsername().getText().toString()},
                {"email", this.sfv.getEtEmailAddress().getText().toString()},
                {"conf_email", this.sfv.getEtEmailAddressConf().getText().toString()},
                {"password", this.sfv.getEtPassword().getText().toString()},
                {"conf_password", this.sfv.getEtPasswordConf().getText().toString()}
        }).collect(Collectors.toMap(data -> (String) data[0], data -> (String) data[1]));
        return this.sfm.validateData(subscribeValues);
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_subsc_bt_subscribe:
                SubscribeFragment sf_this = this;
                ConfirmDialog cd = new ConfirmDialog(getActivity(),"Registrazione", "Vuoi creare un nuovo accoutn con i dati inseriti?");
                cd.setDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Yes button pressed
                        Log.i("SubscribeFragment"," Yes button pressed");
                        if(sf_this.validateSubscribe()){

                        }//if(sf_this.validateSubscribe()){
                        else{
                            MessageDialog md = new MessageDialog(getActivity(),"Registrazione", Globals.ERR_INVALID_DATA_FORMAT);
                        }
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //No button pressed
                        dialogInterface.dismiss();
                    }
                });
                break;
            case R.id.frag_subsc_bt_reset:
                this.sfv.resetAll();
                break;
        }
    }

    //CompoundButton.OnCheckedChangeListener
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch(compoundButton.getId()){
            case R.id.frag_subsc_cb_show_pass:
                break;
        }
    }
}