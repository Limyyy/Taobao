package com.gx.tianba.fragment.home;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx.tianba.R;
import com.gx.tianba.fragment.home.bean.Home;
import com.gx.tianba.fragment.home.homechildfragment.HomeChildFragment;
import com.gx.tianba.fragment.home.homemainfragment.HomeMainFragment;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class HomeFragment extends Fragment {

    private HomeChildFragment homeChildFragment;
    private HomeMainFragment homeMainFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        //首先加载第一个主页面
        FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
        homeMainFragment = new HomeMainFragment();
        homeChildFragment = new HomeChildFragment();
        fragmentTransaction.add(R.id.home_framelayout, homeMainFragment);
        fragmentTransaction.add(R.id.home_framelayout,homeChildFragment);
        fragmentTransaction.show(homeMainFragment);
        fragmentTransaction.hide(homeChildFragment);
        fragmentTransaction.commit();

        homeMainFragment.JumpHomeChildGetData(new HomeMainFragment.JumpHomeChildSetData() {
            @Override
            public void SetData(int type, Home.ResultBean result) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.show(homeChildFragment);
                fragmentTransaction.hide(homeMainFragment);
                fragmentTransaction.commit();
                switch (type){
                    case 1:
                        //热销新品的数据
                        List<Home.ResultBean.RxxpBean> rxxp = result.getRxxp();
                        EventBus.getDefault().post(rxxp);
                        break;
                    case 2:
                        //魔力时尚的数据
                        List<Home.ResultBean.MlssBean> mlss = result.getMlss();
                        EventBus.getDefault().post(mlss);
                        break;
                    case 3:
                        //品质生活的数据
                        List<Home.ResultBean.PzshBean> pzsh = result.getPzsh();
                        EventBus.getDefault().post(pzsh);
                        break;
                }

            }
        });
        homeChildFragment.SetShowHomeMainFragment(new HomeChildFragment.ShowHomeMainFragment() {
            @Override
            public void SetShow() {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.hide(homeChildFragment);
                fragmentTransaction.show(homeMainFragment);
                fragmentTransaction.commit();
            }
        });

        return view;
    }

}
