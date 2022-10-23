package com.example.flightbooking.fragments.contacts;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.flightbooking.R;
import com.example.flightbooking.dialogs.MessageDialog;
import com.example.flightbooking.models.response.Message;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContactsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactsFragment extends Fragment implements View.OnClickListener {

    ContactsFragmentModel cfm;
    ContactsFragmentView cfv;
    private Context context;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContactsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactsFragment newInstance(String param1, String param2) {
        ContactsFragment fragment = new ContactsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        EditText et_name = view.findViewById(R.id.frag_cont_et_name);
        EditText et_from = view.findViewById(R.id.frag_cont_et_from);
        EditText et_subject = view.findViewById(R.id.frag_cont_et_subject);
        EditText et_message = view.findViewById(R.id.frag_cont_et_message);
        Button bt_send = view.findViewById(R.id.frag_cont_bt_send);
        Button bt_reset = view.findViewById(R.id.frag_cont_bt_reset);
        this.cfv = new ContactsFragmentView(et_name,et_from,et_subject,et_message,bt_send,bt_reset);
        this.cfv.getBtSend().setOnClickListener(this);
        this.cfv.getBtReset().setOnClickListener(this);
        return view;
    }

    /**
     * Set the contacts body data for request
     * @return the Map with the contacts body data
     */
    private Map<String, String> contactsBody(){
        Map<String, String> body = Stream.of(new String[][]{
                {"name", this.cfv.getEtName().getText().toString()},
                {"email", this.cfv.getEtFrom().getText().toString()},
                {"subject", this.cfv.getEtSubject().getText().toString()},
                {"message", this.cfv.getEtMessage().getText().toString()}
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
        return body;
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        ContactsFragment this_cf = this;
        switch(view.getId()){
            case R.id.frag_cont_bt_send:
                Map<String, String> contactsData = this.contactsBody();
                this_cf.cfm.contactsRequest(contactsData, new ContactsFragmentModel.ContactsResponse() {
                    @Override
                    public void emailSuccess(Message message) {
                        MessageDialog md = new MessageDialog(this_cf.context,"Assistenza",message.message);
                    }
                    @Override
                    public void emailError(String message) {
                        MessageDialog md = new MessageDialog(this_cf.context,"Assistenza",message);
                    }
                });
                break;
            case R.id.frag_cont_bt_reset:
                this.cfv.resetAll();
                break;
        }
    }
}