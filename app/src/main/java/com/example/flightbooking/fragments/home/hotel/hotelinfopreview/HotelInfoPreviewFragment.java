package com.example.flightbooking.fragments.home.hotel.hotelinfopreview;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.models.response.hotel.Hotel;
import com.example.flightbooking.models.response.hotel.HotelInfo;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelInfoPreviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelInfoPreviewFragment extends Fragment implements View.OnClickListener {

    private Context context;
    private HotelInfo hi;
    private FragmentChange fc = null;
    private HotelInfoPreviewFragmentModel hipfm;
    private HotelInfoPreviewFragmentView hipfv;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HotelInfoPreviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HotelInfoPreviewFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HotelInfoPreviewFragment newInstance(String param1, String param2) {
        HotelInfoPreviewFragment fragment = new HotelInfoPreviewFragment();
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
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.hi = (HotelInfo) getArguments().getSerializable("hotelinfo");
            this.hipfm = new HotelInfoPreviewFragmentModel(this.hi);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_hotel_info_preview, container, false);
        LinearLayout ll_table = v.findViewById(R.id.frag_hiprev_ll_table);
        Button bt_book = v.findViewById(R.id.frag_hiprev_bt_book);
        Button bt_back = v.findViewById(R.id.frag_hiprev_bt_back);
        this.hipfv = new HotelInfoPreviewFragmentView(this.context,ll_table,bt_book,bt_back);
        this.hipfv.getBtBook().setOnClickListener(this);
        this.hipfv.getBtBack().setOnClickListener(this);
        return v;
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_hiprev_bt_book:
                break;
            case R.id.frag_hiprev_bt_back:
                this.fc.fragmentChange(FragmentLabels.HOTELINFO_PREVIEW.getLabelName(),FragmentLabels.HOTELS.getLabelName(), true,null);
                break;
        }
    }
}