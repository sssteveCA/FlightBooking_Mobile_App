package com.example.flightbooking.fragments.information.terms;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.interfaces.FragmentChange;

import java.nio.charset.StandardCharsets;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TermsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TermsFragment extends Fragment implements View.OnClickListener {

    private TermsFragmentModel tfm;
    private TermsFragmentView tfv;
    public FragmentChange fc = null;

    public TermsFragment() {
        // Required empty public constructor
        this.tfm = new TermsFragmentModel();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TermsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TermsFragment newInstance(String param1, String param2) {
        TermsFragment fragment = new TermsFragment();
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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_terms, container, false);
        TextView tv_message = v.findViewById(R.id.frag_terms_tv_message);
        WebView wv_content = v.findViewById(R.id.frag_terms_wv_content);
        Button bt_back = v.findViewById(R.id.frag_terms_bt_back);
        ProgressBar pb = v.findViewById(R.id.frag_terms_pb);
        this.tfv = new TermsFragmentView(tv_message,wv_content,bt_back,pb);
        this.tfv.getBtBack().setOnClickListener(this);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.termsContent();
    }



    /**
     * Perform the HTTP request to get the terms and condition content and display it in a WebView
     */
    private void termsContent(){
        TermsFragmentView this_tfv  = this.tfv;
        this_tfv.getPb().setVisibility(View.VISIBLE);
        this.tfm.getTermsRequest(new TermsFragmentModel.GetTermsResponse() {
            @Override
            public void getTermsSuccess(String terms) {
                this_tfv.getPb().setVisibility(View.GONE);
                String encodedContent = Base64.encodeToString(terms.getBytes(),Base64.NO_PADDING);
                this_tfv.getWvContent().loadData(encodedContent,"text/html","base64");
            }
            @Override
            public void getTermsError(String message) {
                this_tfv.getPb().setVisibility(View.GONE);
                this_tfv.getTvMessage().setText(message);
            }
        });
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_terms_bt_back:
                this.fc.fragmentChange(FragmentLabels.TERMS.getLabelName(), FragmentLabels.HOME.getLabelName(), true, null);
                break;
        }
    }
}