package com.gx.tianba.fragment.home.presenter;

import com.gx.tianba.fragment.home.bean.Banner;
import com.gx.tianba.fragment.home.bean.Home;
import com.gx.tianba.fragment.home.model.HomeModel;
import com.gx.tianba.fragment.home.view.IHome;

public class HomePresenter {

    IHome iHome;
    HomeModel homeModel;

    public HomePresenter(IHome iHome) {
        this.iHome = iHome;
        homeModel=new HomeModel();
    }

    public void setUrl(String bannershow, String homelistdata) {
        homeModel.setTwoUrl(bannershow, homelistdata, new HomeModel.CallBack() {
            @Override
            public void backData(Banner banner, Home home) {
                iHome.onHomeSuccess(home,banner);
            }
        });
    }
}
