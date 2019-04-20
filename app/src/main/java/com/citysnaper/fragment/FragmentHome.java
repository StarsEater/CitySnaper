package com.citysnaper.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.citysnaper.R;
import com.citysnaper.adapter.ArticlePostAdapter;
import com.citysnaper.bean.ArticlePost;
import com.citysnaper.view.GloriousRecyclerView;

import java.util.ArrayList;

public class FragmentHome extends Fragment {
    static String p1Adress = "https://cdn.dribbble.com/users/627451/screenshots/4461821/big_city_life.png";
    static String p2Adress = "https://cdn.dribbble.com/users/25514/screenshots/4362453/illustrated_cards_ramotion.png";
    static String p3Adress = "https://cdn.dribbble.com/users/1627881/screenshots/4834764/assistant_1.jpg";
    static String p4Adress = "https://cdn.dribbble.com/users/1518862/screenshots/5862796/safari_2x.png";

    ImageView city_choose,tourism_strategy,tourism_helper,business_travel_services;
    Toolbar toolbar;
    SwipeRefreshLayout swipeRefreshLayout;
    GloriousRecyclerView recyclerView;

    ArticlePostAdapter articlePostAdapter;


    private ArrayList<ArticlePost> articlePostsList= new ArrayList<>();
    private String TAG = "FragmentHome";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar = view.findViewById(R.id.toolbar);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh);

        recyclerView = view.findViewById(R.id.fragment_home_rl);
        GridLayoutManager layoutManger = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(layoutManger);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        initData();
        articlePostAdapter = new ArticlePostAdapter(articlePostsList);

        View header = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home_headerview, recyclerView, false);
        city_choose = header.findViewById(R.id.city_choose);
        tourism_strategy = header.findViewById(R.id.tourism_strategy);
        tourism_helper = header.findViewById(R.id.tourism_helper);
        business_travel_services = header.findViewById(R.id.business_travel_services);

        Glide.with(this).load(p1Adress).into(city_choose);
        Glide.with(this).load(p2Adress).into(tourism_strategy);
        Glide.with(this).load(p3Adress).into(tourism_helper);
        Glide.with(this).load(p4Adress).into(business_travel_services);


        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);


        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(false);
            }
        });




        //View footer = LayoutInflater.from(this).inflate(R.layout.layout_footer, recyclerView, false);

        //View empty = LayoutInflater.from(this).inflate(R.layout.layout_empty, recyclerView, false);
        recyclerView.setAdapter(articlePostAdapter);
        recyclerView.addHeaderView(header);
        //recyclerView.addFooterView(footer);
        //recyclerView.setEmptyView(empty);


        return view;
    }
    void initData(){
       for (int i = 0; i < 20; i++){
           ArticlePost tmp = new ArticlePost();
           tmp.avatarImageUrl = p1Adress;
           tmp.travelPictureUrl = p2Adress;
           articlePostsList.add(tmp);
       }


    }

}
