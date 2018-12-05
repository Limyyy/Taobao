package com.gx.tianba.fragment.home;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.home.adapter.HomeMainAdapter;
import com.gx.tianba.fragment.home.bean.Banner;
import com.gx.tianba.fragment.home.bean.Home;
import com.gx.tianba.fragment.home.myview.SearchView;
import com.gx.tianba.fragment.home.presenter.HomePresenter;
import com.gx.tianba.fragment.home.view.IHome;
import com.gx.tianba.util.net.HttpUrl;

import java.util.List;

public class HomeFragment extends Fragment implements IHome {

    public RecyclerView home_ryl;
    public SearchView my_seach_view;
    boolean flag=true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //find view
        ImageView menuiamge = view.findViewById(R.id.menu_iamge);
        home_ryl = (RecyclerView) view.findViewById(R.id.home_ryl);

        my_seach_view = view.findViewById(R.id.search_view);

        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.setUrl(HttpUrl.BANNERSHOW, HttpUrl.HOMELISTDATA);

        //找到自定义控件的view并设置点击事件
        ImageView imageView = my_seach_view.GetView();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    //显示
                    my_seach_view.setShowAnimation();
                    flag=false;
                }
                else {
                    //隐藏
                    my_seach_view.setHideAnimation();
                    flag=true;
                }
            }
        });
        return view;
    }


    @Override
    public void onHomeSuccess(final Home home, final Banner banner) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                home_ryl.setLayoutManager(new LinearLayoutManager(getActivity()));
                Home.ResultBean result = home.getResult();
                List<Banner.ResultBean> result1 = banner.getResult();
                HomeMainAdapter homeMainAdapter = new HomeMainAdapter(getActivity(), result, result1);
                home_ryl.setAdapter(homeMainAdapter);
            }
        });
    }
}
