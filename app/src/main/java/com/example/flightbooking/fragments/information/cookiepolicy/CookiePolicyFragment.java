package com.example.flightbooking.fragments.information.cookiepolicy;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.interfaces.FragmentChange;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CookiePolicyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CookiePolicyFragment extends Fragment implements View.OnClickListener {

    private CookiePolicyFragmentView cpv;
    public FragmentChange fc = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CookiePolicyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CookiePolicyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CookiePolicyFragment newInstance(String param1, String param2) {
        CookiePolicyFragment fragment = new CookiePolicyFragment();
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
        View v = inflater.inflate(R.layout.fragment_cookie_policy, container, false);
        WebView wv_content = v.findViewById(R.id.frag_cookiep_wv_content);
        Button bt_back = v.findViewById(R.id.frag_cookiep_bt_back);
        this.cpv = new CookiePolicyFragmentView(wv_content,bt_back);
        this.cpv.getBtBack().setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_cookiep_bt_back:
                this.fc.fragmentChange(FragmentLabels.COOKIE_POLICY.getLabelName(), FragmentLabels.HOME.getLabelName(), true,null);
                break;
        }
    }
}