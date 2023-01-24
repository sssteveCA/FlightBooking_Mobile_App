package com.example.flightbooking.fragments.news;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.flightbooking.MainActivity;
import com.example.flightbooking.R;
import com.example.flightbooking.enums.FragmentLabels;
import com.example.flightbooking.interfaces.FragmentChange;
import com.example.flightbooking.interfaces.Globals;
import com.example.flightbooking.models.response.news.GetPosts;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment implements  View.OnClickListener {

    private NewsFragmentModel nfm;
    private NewsFragmentView nfv;
    private Context context;
    public FragmentChange fc = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsFragment() {
        // Required empty public constructor
        this.nfm = new NewsFragmentModel();
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsFragment newInstance(String param1, String param2) {
        NewsFragment fragment = new NewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Activity activity) {
        super.onAttach(activity);
        this.fc = (MainActivity) activity;
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
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        LinearLayout ll_news_list = v.findViewById(R.id.frag_news_ll_news_list);
        TextView tv_message = ll_news_list.findViewById(R.id.frag_news_tv_message);
        RecyclerView rv_posts = ll_news_list.findViewById(R.id.frag_news_rv_posts);
        ProgressBar pb = ll_news_list.findViewById(R.id.frag_news_pb);
        Button bt_back = v.findViewById(R.id.frag_news_bt_back);
        this.nfv = new NewsFragmentView(ll_news_list,tv_message,rv_posts,pb,bt_back);
        this.nfv.getBtBack().setOnClickListener(this);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getPostsRequest();

    }

    /**
     * Perform the HTTP request to get the posts list
     */
    private void getPostsRequest(){
        NewsFragment this_nf = this;
        NewsFragmentView this_nfv = this.nfv;
        this_nfv.getPb().setVisibility(View.VISIBLE);
        this.nfm.postsRequest(new NewsFragmentModel.GetPostsResponse() {
            @Override
            public void getPostsSuccess(GetPosts posts) {
                try{
                    if(posts.done == true && posts.empty == false){
                        this_nfv.getPb().setVisibility(View.GONE);
                        NewsFragmentAdapter nfa = new NewsFragmentAdapter(posts.posts);
                        this_nfv.getRvPosts().setAdapter(nfa);
                        this_nfv.getRvPosts().setHasFixedSize(true);
                        this_nfv.getRvPosts().setLayoutManager(new LinearLayoutManager(this_nf.context));
                    }//if(posts.done == true && posts.empty == false){
                    else{
                        this_nfv.getPb().setVisibility(View.GONE);
                        this_nfv.getTvMessage().setText(posts.message);
                    }
                }
                catch(Exception e){
                    this_nfv.getPb().setVisibility(View.GONE);
                    this_nfv.getTvMessage().setText(Globals.ERR_NEWS);
                }
            }
            @Override
            public void getPostsError(String message) {
                this_nfv.getPb().setVisibility(View.GONE);
                this_nfv.getTvMessage().setText(message);
            }
        });
    }

    //View.OnClickListener
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.frag_news_bt_back:
                this.fc.fragmentChange(FragmentLabels.NEWS.getLabelName(), FragmentLabels.HOME.getLabelName(), true,null);
                break;
        }
    }
}