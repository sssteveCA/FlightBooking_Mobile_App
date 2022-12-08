package com.example.flightbooking.fragments.noconnection;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NoConnectionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoConnectionFragment extends Fragment implements View.OnClickListener {

    private NoConnectionFragmentView ncfv;
    private String label; //Last menu item clicked

    public interface RetryListener{
        //When retry button is pressed
        public void retryClick(String label);
    }

    public RetryListener retryListener;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NoConnectionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param label The label of menu item clicked.
     * @return A new instance of fragment NoConnectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoConnectionFragment newInstance(String label) {
        NoConnectionFragment fragment = new NoConnectionFragment();
        Bundle args = new Bundle();
        args.putString("label", label);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.label = getArguments().getString("label");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_no_connection, container, false);
        Button bt_retry = view.findViewById(R.id.no_conn_frag_bt_retry);
        this.ncfv = new NoConnectionFragmentView(bt_retry);
        this.ncfv.getBtRetry().setOnClickListener(this);
        return view;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.retryListener = (MainActivity)activity;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id){
            case R.id.no_conn_frag_bt_retry:
                //Log.i("NoConnectionFragment","onClick label => "+this.label);
                this.retryListener.retryClick(this.label);
                break;
        }
    }
}