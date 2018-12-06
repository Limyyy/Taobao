package com.gx.tianba.fragment.home.homechildfragment.model;

import com.google.gson.Gson;
import com.gx.tianba.fragment.home.homechildfragment.bean.HomeChildBean;
import com.gx.tianba.util.callback.OkCallBackUtil;
import com.gx.tianba.util.callback.OkHttpListner;
import com.gx.tianba.util.net.OkHttpUtils;

public class HomeChildModel {
    public void setModUrlGet(String url, int labelId, int page, int count, final HomeChildModelCallBack homeChildModelCallBack){
        OkHttpUtils.enqueueGet(new OkCallBackUtil(new OkHttpListner() {
            @Override
            public void OnCallBackSuccess(String json) {
                Gson gson=new Gson();
                HomeChildBean homeChildBean = gson.fromJson(json, HomeChildBean.class);
                homeChildModelCallBack.homeChildBackData(homeChildBean);
            }

            @Override
            public void OnCallBackFailer(String msg) {
                homeChildModelCallBack.homeChildBackFailer(msg);
            }
        }),url+labelId+"&page="+page+"&count="+count);
    }

    public interface HomeChildModelCallBack{
        void homeChildBackData(HomeChildBean homeChildBean);
        void homeChildBackFailer(String msg);
    }
}
