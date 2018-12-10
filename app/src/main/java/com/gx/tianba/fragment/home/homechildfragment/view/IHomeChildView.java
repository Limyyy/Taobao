package com.gx.tianba.fragment.home.homechildfragment.view;

import com.gx.tianba.fragment.home.homechildfragment.bean.HomeChildBean;

public interface IHomeChildView {
    void OnSuccess(HomeChildBean homeChildBean);
    void OnFailer(String msg);
    void OnHomeChildSousuoSuccess(HomeChildBean homeChildSousuo);
}
