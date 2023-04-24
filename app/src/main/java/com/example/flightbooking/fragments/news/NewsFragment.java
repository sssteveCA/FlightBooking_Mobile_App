package com.example.flightbooking.fragments.news;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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
import com.example.flightbooking.models.response.login.Auth;
import com.example.flightbooking.models.response.news.GetPosts;
import com.example.flightbooking.models.response.news.Post;

import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment implements  View.OnClickListener, NewsFragmentAdapter.PostItemClickListener {

    private NewsFragmentModel nfm;
    private NewsFragmentView nfv;
    private Context context;
    public FragmentChange fc = null;

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
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_news, container, false);
        Map<String,View> views = NewsFragmentMethods.newsItems(v);
        this.nfv = new NewsFragmentView(views);
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
                        NewsFragmentAdapter nfa = new NewsFragmentAdapter(posts.posts,this_nf);
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

    //NewsFragmentAdapter.PostItemClickListener
    @Override
    public void click(Post post) {
        Bundle data = new Bundle();
        data.putSerializable("post",post);
        this.fc.fragmentChange(FragmentLabels.NEWS.getLabelName(), FragmentLabels.POST.getLabelName(), true,data);
    }
}