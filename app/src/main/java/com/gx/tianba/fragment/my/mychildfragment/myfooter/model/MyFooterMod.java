package com.gx.tianba.fragment.my.mychildfragment.myfooter.model;

import com.gx.tianba.fragment.my.mychildfragment.myfooter.bean.MyFooter;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyFooterMod {


    private RetrofitService anInterface;

    public void setFooterModUrl(int userId, String sessionId, int page, int count, final myFooterCallBack myFooterCallBack) {
        anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
        Observable<MyFooter> myFooterObservable = anInterface.RetrofitMyFooter(userId, sessionId, page, count);
        myFooterObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyFooter>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        myFooterCallBack.failerMyFooter("请求失败");
                    }

                    @Override
                    public void onNext(MyFooter myFooter) {
                        myFooterCallBack.successMyFooter(myFooter);
                    }
                });
    }
    public interface myFooterCallBack{
        void successMyFooter(MyFooter myFooter);
        void failerMyFooter(String msg);
    }

    public void onDestroy(){
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
