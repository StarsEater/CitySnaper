package com.citysnaper.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.bumptech.glide.Glide;
import com.citysnaper.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyEditPage extends AppCompatActivity implements View.OnClickListener{

    String edm1Adress = "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1949.webp";
    private String TAG = "MyEditPage";

    @InjectView(R.id.my_edit_close)
    ImageView myEditClose;
    @InjectView(R.id.my_edit_ok)
    ImageView myEditOk;
    @InjectView(R.id.edit_profile_image)
    ImageView editProfileImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_edit_page);
        ButterKnife.inject(this);

        Glide.with(this).load(edm1Adress).into(editProfileImage);



    }
    @OnClick({R.id.my_edit_close,R.id.my_edit_ok}) //放置监听事件
    @Override
    public void onClick(View v) {
          switch (v.getId()){
              case R.id.my_edit_close:
                  Log.d(TAG,"你点击了查查");
                  onBackPressed();
                  break;
              case R.id.my_edit_ok:
                  break;
          }
    }
}
