package com.example.flightbooking.fragments.subscribe.verifyaccount;

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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VerifyAccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VerifyAccountFragment extends Fragment implements View.OnClickListener{

    private VerifyAccountFragmentView vafv;
    public FragmentChange fc;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public VerifyAccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VerifyAccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VerifyAccountFragment newInstance(String param1, String param2) {
        VerifyAccountFragment fragment = new VerifyAccountFragment();
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
        View view = inflater.inflate(R.layout.fragment_verify_account, container, false);
        Button bt_back = view.findViewById(R.id.frag_verAcc_bt_back);
        this.vafv = new VerifyAccountFragmentView(bt_back);
        this.vafv.getBtBack().setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.equals(this.vafv.getBtBack())){
            this.fc.fragmentChange(FragmentLabels.VERIFY.getLabelName(), FragmentLabels.HOME.getLabelName(), true, null);
        }
    }
}