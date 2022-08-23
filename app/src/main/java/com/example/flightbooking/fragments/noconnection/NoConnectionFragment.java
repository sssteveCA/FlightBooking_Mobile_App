package com.example.flightbooking.fragments.noconnection;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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

    public interface RetryListener{
        //When retry button is pressed
        public void retryClick();
    }

    public RetryListener retryListener;
    private TextView tv_1;
    private Button bt_1;

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
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoConnectionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoConnectionFragment newInstance(String param1, String param2) {
        NoConnectionFragment fragment = new NoConnectionFragment();
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
        View view = inflater.inflate(R.layout.fragment_no_connection, container, false);
        tv_1 = view.findViewById(R.id.no_conn_frag_tv_1);
        bt_1 = view.findViewById(R.id.no_conn_frag_bt_1);
        bt_1.setOnClickListener(this);
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
            case R.id.no_conn_frag_bt_1:
                this.retryListener.retryClick();
                break;
            default:
                break;
        }
    }
}