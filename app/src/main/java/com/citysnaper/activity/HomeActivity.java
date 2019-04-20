package com.citysnaper.activity;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bumptech.glide.Glide;
import com.citysnaper.R;
import com.citysnaper.fragment.FragmentController;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class HomeActivity extends AppCompatActivity implements BottomNavigationBar.OnTabSelectedListener{

    @InjectView(R.id.bottom_navigation_bar)
    BottomNavigationBar mBottomNavigationBar;

    private  FragmentController fragmentController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.inject(this);

        //getSupportActionBar().setDisplayShowTitleEnabled(false);





        InitNavigationBar();


        fragmentController = FragmentController.getInstance(this, R.id.mainlayout);
        fragmentController.showFragment(0);




    }
    private void InitNavigationBar() {
        mBottomNavigationBar.setTabSelectedListener(this);
        mBottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        mBottomNavigationBar.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);
        mBottomNavigationBar
                .addItem(new BottomNavigationItem(R.drawable.ic_home_black_24dp, "主页").setActiveColorResource(R.color.colorPrimaryDark))
                .addItem(new BottomNavigationItem(R.drawable.ic_search_black_24dp, "搜索").setActiveColorResource(R.color.colorPrimaryDark))
                .addItem(new BottomNavigationItem(R.drawable.ic_favorite_border_black_24dp, "收藏").setActiveColorResource(R.color.colorPrimaryDark))
                .addItem(new BottomNavigationItem(R.drawable.ic_person_black_24dp, "我的").setActiveColorResource(R.color.colorPrimaryDark))
                .setFirstSelectedPosition(0)
                .initialise();

    }

/**
 * 底部NaV监听
 *
 * @param position Fragment位置
 */
    @Override
    public void onTabSelected(int position) {
        fragmentController.hideFragments();//先隐藏所有frag
        fragmentController.showFragment(position);
    }

    @Override
    public void onTabUnselected(int position) {

    }

    @Override
    public void onTabReselected(int position) {

    }
}
