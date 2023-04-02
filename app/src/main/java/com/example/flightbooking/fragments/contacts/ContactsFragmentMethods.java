package com.example.flightbooking.fragments.contacts;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.flightbooking.R;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ContactsFragmentMethods {

    /**
     * Set the contacts body data for request
     * @return the Map with the contacts body data
     */
    public static Map<String, String> contactsBody(ContactsFragmentView cfv){
        Map<String, String> body = Stream.of(new String[][]{
                {"name", cfv.getEtName().getText().toString()},
                {"email", cfv.getEtFrom().getText().toString()},
                {"subject", cfv.getEtSubject().getText().toString()},
                {"message", cfv.getEtMessage().getText().toString()}
        }).collect(Collectors.toMap(data -> data[0], data -> data[1]));
        return body;
    }

    /**
     * Create a Map with the view References
     * @param view the fragment layout that contains the views
     * @return
     */
    public static Map<String, View> setViews(View view){
        return Map.ofEntries(
                new AbstractMap.SimpleImmutableEntry<>("et_name",(EditText)view.findViewById(R.id.frag_cont_et_name)),
                new AbstractMap.SimpleImmutableEntry<>("et_from",(EditText)view.findViewById(R.id.frag_cont_et_from)),
                new AbstractMap.SimpleImmutableEntry<>("et_subject",(EditText)view.findViewById(R.id.frag_cont_et_subject)),
                new AbstractMap.SimpleImmutableEntry<>("et_message",(EditText)view.findViewById(R.id.frag_cont_et_message)),
                new AbstractMap.SimpleImmutableEntry<>("bt_send",(Button)view.findViewById(R.id.frag_cont_bt_send)),
                new AbstractMap.SimpleImmutableEntry<>("bt_reset",(Button)view.findViewById(R.id.frag_cont_bt_reset)),
                new AbstractMap.SimpleImmutableEntry<>("bt_back",(Button)view.findViewById(R.id.frag_cont_bt_back)),
                new AbstractMap.SimpleImmutableEntry<>("pb",(ProgressBar)view.findViewById(R.id.frag_cont_pb))
        );
    }
}
