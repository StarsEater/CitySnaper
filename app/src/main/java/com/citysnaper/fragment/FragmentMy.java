package com.citysnaper.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.citysnaper.R;
import com.citysnaper.activity.MyEditPage;
import com.citysnaper.activity.SignInOrUp;

import butterknife.OnClick;

public class FragmentMy extends Fragment  {
    ImageView profile_image,addPeople,setting;
    TextView myName;
    Button myEdit;
    String m1Adress = "https://img1.doubanio.com/view/celebrity/s_ratio_celebrity/public/p1949.webp";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        profile_image = view.findViewById(R.id.profile_image);
        myName = view.findViewById(R.id.my_name);
        myEdit = view.findViewById(R.id.my_edit);
        addPeople = view.findViewById(R.id.my_add_people);
        setting = view.findViewById(R.id.my_setting);

        Glide.with(this).load(m1Adress).into(profile_image);
        myEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(getActivity(), MyEditPage.class);
                startActivity(mIntent);
            }
        });
        addPeople.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 startActivity(new Intent(getActivity(), SignInOrUp.class));
            }
        });


        return view;
    }

}
