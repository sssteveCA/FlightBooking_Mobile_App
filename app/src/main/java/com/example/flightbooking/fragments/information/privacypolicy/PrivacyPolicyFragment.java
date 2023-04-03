package com.example.flightbooking.fragments.information.privacypolicy;

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
 * Use the {@link PrivacyPolicyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrivacyPolicyFragment extends Fragment implements View.OnClickListener {

    private PrivacyPolicyFragmentModel ppfm;
    private PrivacyPolicyFragmentView ppfv;
    public FragmentChange fc = null;

    public PrivacyPolicyFragment() {
        // Required empty public constructor
        this.ppfm = new PrivacyPolicyFragmentModel();
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
    public void onResume() {
        super.onResume();
        this.privacyPolicyContent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_privacy_policy, container, false);
        TextView tv_message = v.findViewById(R.id.frag_privacyp_tv_message);
        WebView wv_content = v.findViewById(R.id.frag_privacyp_wv_content);
        Button bt_back = v.findViewById(R.id.frag_privacyp_bt_back);
        ProgressBar pb = v.findViewById(R.id.frag_privacyp_pb);
        this.ppfv = new PrivacyPolicyFragmentView(tv_message,wv_content,bt_back,pb);
        this.ppfv.getBtBack().setOnClickListener(this);
        return v;
    }

    /**
     * Perform the HTTP request to get the privacy policy content and display it in a WebView
     */
    private void privacyPolicyContent(){
        PrivacyPolicyFragmentView this_ppfv = this.ppfv;
        this_ppfv.getPb().setVisibility(View.VISIBLE);
        this.ppfm.getPrivacyPolicyRequest(new PrivacyPolicyFragmentModel.GetPrivacyPolicyResponse() {
            @Override
            public void getPrivacyPolicySuccess(String privacy_policy) {
                this_ppfv.getPb().setVisibility(View.GONE);
                String encodedContent = Base64.encodeToString(privacy_policy.getBytes(),Base64.NO_PADDING);
                this_ppfv.getWvContent().loadData(encodedContent,"text/html","base64");
            }
            @Override
            public void getPrivacyPolicyError(String message) {
                this_ppfv.getPb().setVisibility(View.GONE);
                this_ppfv.getTvMessage().setText(message);
            }
        });
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