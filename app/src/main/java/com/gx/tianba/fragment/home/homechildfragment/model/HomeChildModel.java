package com.gx.tianba.fragment.home.homechildfragment.model;

import com.google.gson.Gson;
import com.gx.tianba.fragment.home.homechildfragment.bean.HomeChildBean;
import com.gx.tianba.util.callback.OkCallBackUtil;
import com.gx.tianba.util.callback.OkHttpListner;
import com.gx.tianba.util.net.OkHttpUtils;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

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

    public void setModSouSuoData(String edit, int page, int count, final HomeChildMoldelSouSuo homeChildMoldelSouSuo) {
        RetrofitService anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
        Observable<HomeChildBean> homeChildSousuoObservable = anInterface.RetrofitSouSuo(edit, page, count);
        homeChildSousuoObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeChildBean>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        homeChildMoldelSouSuo.homeChildSouSuoFailer("请求失败");
                    }

                    @Override
                    public void onNext(HomeChildBean homeChildSousuo) {
                        homeChildMoldelSouSuo.homeChildSouSuo(homeChildSousuo);
                    }
                });
    }


    public interface HomeChildModelCallBack{
        void homeChildBackData(HomeChildBean homeChildBean);
        void homeChildBackFailer(String msg);
    }
    public interface HomeChildMoldelSouSuo{
        void homeChildSouSuo(HomeChildBean homeChildSousuo);
        void  homeChildSouSuoFailer(String msg);

    }
}
