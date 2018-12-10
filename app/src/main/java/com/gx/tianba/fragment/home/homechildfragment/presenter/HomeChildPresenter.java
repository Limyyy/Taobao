package com.gx.tianba.fragment.home.homechildfragment.presenter;

import com.gx.tianba.fragment.home.homechildfragment.bean.HomeChildBean;
import com.gx.tianba.fragment.home.homechildfragment.model.HomeChildModel;
import com.gx.tianba.fragment.home.homechildfragment.view.IHomeChildView;

public class HomeChildPresenter {

    IHomeChildView iHomeChildView;
    HomeChildModel homeChildModel;

    public HomeChildPresenter(IHomeChildView iHomeChildView) {
        this.iHomeChildView = iHomeChildView;
        homeChildModel=new HomeChildModel();
    }
    public void setPreUrlGet(String url, int labelId, int page, int count) {
        homeChildModel.setModUrlGet(url, labelId, page, count, new HomeChildModel.HomeChildModelCallBack() {
            @Override
            public void homeChildBackData(HomeChildBean homeChildBean) {
                iHomeChildView.OnSuccess(homeChildBean);
            }

            @Override
            public void homeChildBackFailer(String msg) {
                iHomeChildView.OnFailer(msg);
            }
        });
    }

    //根据关键词查询商品信息
    public void setPreSouSuoData(String edit, int page, int count) {
        homeChildModel.setModSouSuoData(edit,page,count, new HomeChildModel.HomeChildMoldelSouSuo() {
            @Override
            public void homeChildSouSuo(HomeChildBean homeChildSousuo) {
                iHomeChildView.OnHomeChildSousuoSuccess(homeChildSousuo);
            }

            @Override
            public void homeChildSouSuoFailer(String msg) {
                iHomeChildView.OnFailer(msg);
            }
        });
    }
}
