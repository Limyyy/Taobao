package com.gx.tianba.fragment.my.mychildfragment.myaddress.model;

import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyAddressModel {

    public void setModAddressListUrl(String userId, String sessionId, final CallBack callBack){
        RetrofitService anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
        Observable<MyAddress> myAddressObservable = anInterface.RetrofitMyAddress(userId, sessionId);
        myAddressObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyAddress>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyAddress myAddress) {
                        callBack.onSuccess(myAddress);
                    }
                });
    }

    public interface CallBack{
        void onSuccess(MyAddress myAddress);
    }

    public void onDestroy(){

    }
}
