package com.example.flightbooking.fragments.subscribe;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.dialogs.ConfirmDialog;
import com.example.flightbooking.dialogs.MessageDialog;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.models.response.subscribe.SubscribeFormResponse;

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
    private Context context;
    public FragmentChange fc = null;

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
        return fragment;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("et_username",this.sfv.getEtUsername().getText().toString());
        outState.putString("et_email_address",this.sfv.getEtEmailAddress().getText().toString());
        outState.putString("et_email_address_conf",this.sfv.getEtEmailAddressConf().getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.fc = (MainActivity)activity;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subscribe, container, false);
        Map<String, View> items = SubscribeFragmentMethods.subscribeItems(view);
        this.sfv = new SubscribeFragmentView(items);
        if(savedInstanceState != null){
            this.sfv.getEtUsername().setText(savedInstanceState.getString("et_username"));
            this.sfv.getEtEmailAddress().setText(savedInstanceState.getString("et_email_address"));
            this.sfv.getEtEmailAddressConf().setText(savedInstanceState.getString("et_email_address_conf"));
        }
        this.sfv.getBtSubscribe().setOnClickListener(this);
        this.sfv.getBtReset().setOnClickListener(this);
        this.sfv.getBtBack().setOnClickListener(this);
        this.sfv.getCbShowPass().setOnCheckedChangeListener(this);
        return view;
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_subsc_bt_subscribe:
                SubscribeFragment sf_this = this;
                ConfirmDialog cd = new ConfirmDialog(sf_this.context,"Registrazione", "Vuoi creare un nuovo account con i dati inseriti?");
                cd.setDialog(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Yes button pressed
                        Map<String, String> post_data = SubscribeFragmentMethods.setSubscribeBody(sf_this.sfv);
                        sf_this.sfv.getPb().setVisibility(View.VISIBLE);
                        sf_this.sfm.subscribeRequest(post_data, new SubscribeFragmentModel.SubscribeResponse() {
                            @Override
                            public void subscribeResponse(SubscribeFormResponse response) {
                                sf_this.sfv.getPb().setVisibility(View.GONE);
                                if(response.status.equals("OK")) fc.fragmentChange(FragmentLabels.SUBSCRIBE.getLabelName(),FragmentLabels.VERIFY.getLabelName(),true,null);
                                else{
                                    MessageDialog md = new MessageDialog(sf_this.context,"Registrazione",response.message);
                                }
                            }
                            @Override
                            public void subscribeError(String message) {
                                sf_this.sfv.getPb().setVisibility(View.GONE);
                                MessageDialog md = new MessageDialog(sf_this.context,"Registrazione",message);
                            }
                        });
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
            case R.id.frag_subsc_bt_back:
                fc.fragmentChange(FragmentLabels.SUBSCRIBE.getLabelName(), FragmentLabels.HOME.getLabelName(), true,null);
                break;
        }
    }

    //CompoundButton.OnCheckedChangeListener
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch(compoundButton.getId()){
            case R.id.frag_subsc_cb_show_pass:
                if(compoundButton.isChecked()){
                    this.sfv.getEtPassword().setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
                    this.sfv.getEtPasswordConf().setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
                }
                else{
                    this.sfv.getEtPassword().setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    this.sfv.getEtPasswordConf().setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                break;
        }
    }
}