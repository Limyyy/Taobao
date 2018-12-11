package com.gx.tianba.fragment.my.mychildfragment.myaddaddress.model;

import com.gx.tianba.fragment.my.mychildfragment.myaddaddress.bean.MyAddAddress;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyAddAddressMod {

    private RetrofitService anInterface;

    public void setModMyAddAddressUrl(int userId, String sessionId, String realName, String phone, String address, String zipCode, final CallBack callBack){
        anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
        Observable<MyAddAddress> myAddAddressObservable = anInterface.RetrofitMyAddAddress(userId, sessionId, realName, phone, address, zipCode);
        myAddAddressObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyAddAddress>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyAddAddress myAddAddress) {
                        callBack.onSuccess(myAddAddress);
                    }
                });
    }

    public interface CallBack{
        void onSuccess(MyAddAddress myAddAddress);
    }

    public void onDestroy(){
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
