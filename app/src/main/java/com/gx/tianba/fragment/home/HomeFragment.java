package com.gx.tianba.fragment.home;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx.tianba.R;
import com.gx.tianba.fragment.home.adapter.HomeMainAdapter;
import com.gx.tianba.fragment.home.bean.Banner;
import com.gx.tianba.fragment.home.bean.Home;
import com.gx.tianba.fragment.home.presenter.HomePresenter;
import com.gx.tianba.fragment.home.view.IHome;
import com.gx.tianba.util.net.HttpUrl;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IHome {

    private RecyclerView home_ryl;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //find view
        initView(view);
        HomePresenter homePresenter = new HomePresenter(this);
        homePresenter.setUrl(HttpUrl.BANNERSHOW,HttpUrl.HOMELISTDATA);
        return view;
    }

    private void initView(View view) {
        home_ryl = (RecyclerView) view.findViewById(R.id.home_ryl);
    }


    @Override
    public void onHomeSuccess(final Home home, final Banner banner) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                home_ryl.setLayoutManager(new LinearLayoutManager(getActivity()));
                Home.ResultBean result = home.getResult();
                List<Banner.ResultBean> result1 = banner.getResult();
                HomeMainAdapter homeMainAdapter = new HomeMainAdapter(getActivity(),result,result1);
                home_ryl.setAdapter(homeMainAdapter);
            }
        });
    }
}
