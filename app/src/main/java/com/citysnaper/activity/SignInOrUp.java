package com.citysnaper.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.citysnaper.fragment.fragmentSignIn;
import com.citysnaper.fragment.fragmentSignUp;
import com.citysnaper.R;

public class SignInOrUp extends AppCompatActivity {
    String TAG = "SignInOrUp";

    ImageView imageView;
    LinearLayout l1;
    fragmentSignIn fsi;
    fragmentSignUp fsu;
    TextView changeState;
    ImageView backIv;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_sign_in_or_up);
        //getSupportActionBar().hide();

        imageView = findViewById(R.id.sign_in_up_iv);

        l1 =findViewById(R.id.sign_in_up_lly);
        backIv = findViewById(R.id.sign_up_in_back);

        changeState = findViewById(R.id.changeState);
        changeState.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线

        changeState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG," "+ count);
                count = (3-count)%2;
                transferEach();
            }
        });
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fsi = new fragmentSignIn();
        fsu = new fragmentSignUp();

        Intent intent = getIntent();
        count = intent.getIntExtra("type",0);
        transferEach();

    }
    /**
     *  登陆与注册之间的切换
     * */
    protected void  transferEach(){
        switch (count){
            case 0:
                imageView.setImageResource(R.drawable.good_morning_img);
                Log.d(TAG ,"登录");
                changeState.setText("去注册");
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    fsi.setEnterTransition(new Fade().setDuration(600));
//                }
                getSupportFragmentManager().beginTransaction().replace(R.id.sign_in_up_lly, fsi, "fragmentSignIn").commitAllowingStateLoss();
                break;
            case 1:
                Log.d(TAG ,"注册");
                imageView.setImageResource(R.drawable.good_night_img);
                changeState.setText("去登录");
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//                    fsu.setEnterTransition(new Fade().setDuration(600));
//                }
                getSupportFragmentManager().beginTransaction().replace(R.id.sign_in_up_lly, fsu, "fragmentSignUp").commitAllowingStateLoss();
                break;
            default:
                break;
        }
    }
}
