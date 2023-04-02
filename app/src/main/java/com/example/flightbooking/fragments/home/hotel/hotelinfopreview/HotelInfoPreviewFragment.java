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
import com.example.flightbooking.common.Connection;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.fragments.home.flights.hotel.BookHotelResponse;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.fragments.home.flights.hotel.HotelInfo;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.interfaces.LoginObserver;
import com.example.flightbooking.models.requests.hotel.BookHotelRequest;
import com.example.flightbooking.models.response.login.Auth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HotelInfoPreviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HotelInfoPreviewFragment extends Fragment implements View.OnClickListener {

    private Auth auth = null;
    private Context context;
    private FragmentChange fc = null;
    private HotelInfo hi;
    private LoginObserver lo = null;
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
        this.lo = (LoginObserver)activity;
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
            this.hi = (HotelInfo) getArguments().getSerializable("hotelInfo");
            this.auth = (Auth) getArguments().getSerializable(Globals.KEY_AUTH);
        }
        this.hipfm = new HotelInfoPreviewFragmentModel(this.hi, this.auth);
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
        HotelInfoPreviewFragmentMethods.setTable(this.context,this.hipfm,this.hipfv);
        this.hipfv.getBtBook().setOnClickListener(this);
        this.hipfv.getBtBack().setOnClickListener(this);
        return v;
    }

    /**
     * Execute the request to book the selected hotel rooms
     */
    public void bookHotelRequest(){
        if(this.hi != null && Connection.checkInternet(this.context)){
            BookHotelRequest bhr = new BookHotelRequest();
            bhr.sessionId = this.hi.response.session_id;
            Auth this_auth = this.auth;
            FragmentChange this_fc = this.fc;
            LoginObserver this_lo = this.lo;
            this.hipfm.bookHotelRequest(bhr, new HotelInfoPreviewFragmentModel.BookHotelResponseInterface() {
                @Override
                public void bookHotelResponse(BookHotelResponse bhr) {

                }
                @Override
                public void bookHotelError() {
                    Bundle bundle = new Bundle();
                    bundle.putString(Globals.KEY_OLDFRAGMENT, FragmentLabels.HOTELINFO_PREVIEW.getLabelName());
                    this_lo.onLogout(FragmentLabels.LOGIN.getLabelName(), bundle);
                }
            });
        }//if(this.hi != null && Connection.checkInternet(this.context)){
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_hiprev_bt_book:
                this.bookHotelRequest();
                break;
            case R.id.frag_hiprev_bt_back:
                this.fc.fragmentChange(FragmentLabels.HOTELINFO_PREVIEW.getLabelName(),FragmentLabels.HOME.getLabelName(), true,null);
                break;
        }
    }
}