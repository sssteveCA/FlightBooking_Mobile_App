package com.example.flightbooking.fragments.login;

import android.content.Context;
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

import com.example.flightbooking.R;
import com.example.flightbooking.dialogs.MessageDialog;
import com.example.flightbooking.models.login.Auth;

import java.util.AbstractMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private LoginFragmentModel lfm;
    private LoginFragmentView lfv;
    private Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
        this.lfm = new LoginFragmentModel();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        EditText et_username = view.findViewById(R.id.frag_login_et_username);
        EditText et_password = view.findViewById(R.id.frag_login_et_password);
        CheckBox cb_show_pass = view.findViewById(R.id.frag_login_cb_show_pass);
        Button bt_login = view.findViewById(R.id.frag_login_bt_login);
        Button bt_reset = view.findViewById(R.id.frag_login_bt_reset);
        ProgressBar pb = view.findViewById(R.id.frag_login_pb);
        this.lfv = new LoginFragmentView(et_username,et_password,cb_show_pass,bt_login,bt_reset,pb);
        this.lfv.getBtLogin().setOnClickListener(this);
        this.lfv.getBtReset().setOnClickListener(this);
        this.lfv.getCbShowPass().setOnCheckedChangeListener(this);
        return view;
    }

    /**
     * Set the login body data
     * @return the Map with the login data
     */
    private Map<String, String> setLoginBody(){
        Map<String, String> loginBody = Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<String, String>("username", this.lfv.getEtUsername().getText().toString()),
                new AbstractMap.SimpleImmutableEntry<String, String>("password", this.lfv.getEtPassword().getText().toString())
        );
        return loginBody;
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        LoginFragment this_lf = this;
        switch(view.getId()){
            case R.id.frag_login_bt_login:
                Map<String, String> loginData = this.setLoginBody();
                this_lf.lfv.getPb().setVisibility(View.VISIBLE);
                this_lf.lfm.loginRequest(loginData, new LoginFragmentModel.LoginResponse() {
                    @Override
                    public void loginResponse(Auth auth) {
                        this_lf.lfv.getPb().setVisibility(View.GONE);
                        Log.i("LoginFragment", "onClick loginResponse => "+auth.status);
                    }
                    @Override
                    public void loginError(String message) {
                        this_lf.lfv.getPb().setVisibility(View.GONE);
                        MessageDialog md = new MessageDialog(this_lf.context,"Login",message);
                    }
                });
                break;
            case R.id.frag_login_bt_reset:
                this.lfv.resetAll();
                break;
        }
    }

    //CompoundButton.OnCheckedChangeListener
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch(compoundButton.getId()){
            case R.id.frag_login_cb_show_pass:
                if(compoundButton.isChecked()) this.lfv.getEtPassword().setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_NORMAL);
                else this.lfv.getEtPassword().setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
                break;
        }
    }
}