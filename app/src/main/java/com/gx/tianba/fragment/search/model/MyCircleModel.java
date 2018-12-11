package com.gx.tianba.fragment.search.model;

import com.gx.tianba.fragment.search.bean.MyCircle;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyCircleModel {


    private RetrofitService anInterface;

    public void setModMyCircleUrl(int userId, String sessionId, int page, int count, final CallBack callBack){
        anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
        Observable<MyCircle> myCircleObservable = anInterface.RetrofitMyCircle(userId, sessionId, page, count);
        myCircleObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyCircle>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailer("请求失败");
                    }

                    @Override
                    public void onNext(MyCircle myCircle) {
                        callBack.onSuccess(myCircle);
                    }
                });
    }

    public interface CallBack{
        void onSuccess(MyCircle myCircle);
        void onFailer(String msg);
    }

    public void onDestroy() {
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
