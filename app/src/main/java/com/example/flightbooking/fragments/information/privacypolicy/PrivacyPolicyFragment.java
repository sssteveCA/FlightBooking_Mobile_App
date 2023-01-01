package com.example.flightbooking.fragments.information.privacypolicy;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.interfaces.OnMainMenuItemClick;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PrivacyPolicyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrivacyPolicyFragment extends Fragment implements View.OnClickListener {

    private PrivacyPolicyView ppv;
    public FragmentChange fc = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PrivacyPolicyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrivacyPolicyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrivacyPolicyFragment newInstance(String param1, String param2) {
        PrivacyPolicyFragment fragment = new PrivacyPolicyFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.fc = (MainActivity)activity;
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
        View v = inflater.inflate(R.layout.fragment_privacy_policy, container, false);
        Button bt_back = v.findViewById(R.id.frag_privacyp_bt_back);
        this.ppv = new PrivacyPolicyView(bt_back);
        this.ppv.getBtBack().setOnClickListener(this);
        return v;
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_privacyp_bt_back:
                this.fc.fragmentChange(FragmentLabels.PRIVACY_POLICY.getLabelName(), FragmentLabels.HOME.getLabelName(), true,null);
                break;
        }
    }
}