package com.example.flightbooking.fragments.information.cookiepolicy;

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
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

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

    private CookiePolicyFragmentModel cpfm;
    private CookiePolicyFragmentView cpfv;
    public FragmentChange fc = null;

    public CookiePolicyFragment() {
        // Required empty public constructor
        this.cpfm = new CookiePolicyFragmentModel();
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
        this.cookiePolicyContent();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_cookie_policy, container, false);
        TextView tv_message = v.findViewById(R.id.frag_cookiep_tv_message);
        WebView wv_content = v.findViewById(R.id.frag_cookiep_wv_content);
        Button bt_back = v.findViewById(R.id.frag_cookiep_bt_back);
        ProgressBar pb = v.findViewById(R.id.frag_cookiep_pb);
        this.cpfv = new CookiePolicyFragmentView(tv_message,wv_content,bt_back,pb);
        this.cpfv.getBtBack().setOnClickListener(this);
        return v;
    }

    /**
     * Perform the HTTP request to get the cookie policy content and display it in a WebView
     */
    private void cookiePolicyContent(){
        CookiePolicyFragmentView this_cpfv = this.cpfv;
        this_cpfv.getPb().setVisibility(View.VISIBLE);
        this.cpfm.getCookiePolicyRequest(new CookiePolicyFragmentModel.GetCookiePolicyResponse() {
            @Override
            public void getCookiePolicySuccess(String cookie_policy) {
                this_cpfv.getPb().setVisibility(View.GONE);
                String encodedContent = Base64.encodeToString(cookie_policy.getBytes(),Base64.NO_PADDING);
                this_cpfv.getWvContent().loadData(encodedContent,"text/html","base64");
            }
            @Override
            public void getCookiePolicyError(String message) {
                this_cpfv.getPb().setVisibility(View.GONE);
                this_cpfv.getTvMessage().setText(message);
            }
        });
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