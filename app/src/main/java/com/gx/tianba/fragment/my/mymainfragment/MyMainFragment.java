package com.gx.tianba.fragment.my.mymainfragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.myfooter.fragment.MyFooterFragment;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.MyPersonFragment;
import com.gx.tianba.fragment.my.mychildfragment.mywallet.fragment.MyWalletFragment;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.fresco.FrescoUtil;
import com.gx.tianba.util.sp.SpUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyMainFragment extends Fragment {

    private TextView my_text_content;
    private TextView myPerson;
    private LinearLayout my_personal_data;
    private TextView myCircle;
    private LinearLayout my_circle;
    private TextView myFoot;
    private LinearLayout my_footprint;
    private TextView myMoney;
    private LinearLayout my_wallet;
    private TextView myAddress;
    private LinearLayout my_harvesting_address;
    private LinearLayout relb;
    private SimpleDraweeView my_circle_ImageView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_main, container, false);
        initView(view);

        //得到保存的数据
        Login.ResultBean spData = SpUtil.getSpData();
        //设置头像
        FrescoUtil.load(spData.getHeadPic(),my_circle_ImageView);
        //点击个人资料转换fragment
        my_personal_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.my_framelayout,new MyPersonFragment());
                fragmentTransaction.commit();
            }
        });
        //点击我的足迹转换fragment
        my_footprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.my_framelayout,new MyFooterFragment());
                fragmentTransaction.commit();
            }
        });
        my_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.my_framelayout,new MyWalletFragment());
                fragmentTransaction.commit();
            }
        });



        return view;
    }

    private void initView(View view) {
        my_text_content = (TextView) view.findViewById(R.id.my_text_content);
        myPerson = (TextView) view.findViewById(R.id.myPerson);
        my_personal_data = (LinearLayout) view.findViewById(R.id.my_personal_data);
        myCircle = (TextView) view.findViewById(R.id.myCircle);
        my_circle = (LinearLayout) view.findViewById(R.id.my_circle);
        myFoot = (TextView) view.findViewById(R.id.myFoot);
        my_footprint = (LinearLayout) view.findViewById(R.id.my_footprint);
        myMoney = (TextView) view.findViewById(R.id.myMoney);
        my_wallet = (LinearLayout) view.findViewById(R.id.my_wallet);
        myAddress = (TextView) view.findViewById(R.id.myAddress);
        my_harvesting_address = (LinearLayout) view.findViewById(R.id.my_harvesting_address);
        relb = (LinearLayout) view.findViewById(R.id.relb);
        my_circle_ImageView = (SimpleDraweeView) view.findViewById(R.id.my_circle_ImageView);
    }
}
