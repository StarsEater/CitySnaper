package com.citysnaper.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.citysnaper.R;

import java.util.ArrayList;
import java.util.List;

public class fragmentSignIn extends Fragment implements TabLayout.OnTabSelectedListener {
    public ArrayList<Fragment> fragments;
    private String TAG = "fragment_order";
    private fragmentSignInPhone fsip;
    private fragmentSignInUser fsiu;
    private MyViewPagerAdapter viewPagerAdapter;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] titles = {"账号登录", "短信快捷登录",};


    public void onCreateOptionsMenu(final Menu menu, MenuInflater inflater) {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.viewPager);
        //设置TabLayout标签的显示方式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        //循环注入标签
        for (String tab : titles) {
            tabLayout.addTab(tabLayout.newTab().setText(tab));
        }
        //设置TabLayout点击事件
        tabLayout.setOnTabSelectedListener(this);
        RefreshLayout(0);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RefreshLayout(0);
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    /**
     * 加载视图
     */
    public void RefreshLayout(int item) {
        fragments = new ArrayList<>();
        fsiu = new fragmentSignInUser();
        fsip = new fragmentSignInPhone();
        fragments = new ArrayList<>();
        fragments.add(fsiu);
        fragments.add(fsip);

        /**tab标签和内容viewpager*/
        viewPagerAdapter = new MyViewPagerAdapter(getChildFragmentManager(), titles, fragments);
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(item);
    }

    /**
     * 内容页的适配器
     */
    public class MyViewPagerAdapter extends FragmentPagerAdapter {
        private List<Fragment> fragments;
        private String[] titles;

        public MyViewPagerAdapter(FragmentManager fm, String[] titles, List<Fragment> fragments) {
            super(fm);
            this.titles = titles;
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int arg0) {
            return fragments.get(arg0);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public int getCount() {
            return fragments.size();
        }


    }

}