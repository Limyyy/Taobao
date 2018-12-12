package com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.model;

import com.gx.tianba.fragment.my.mychildfragment.myaddaddress.bean.MyAddAddress;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.bean.MyAddressUpdate;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyAddressUpdateMod {

    private RetrofitService anInterface;

    public void setModMyAddressUpdate(int userId, String sessionId, String realName, String phone, String address, String zipCode, final CallBack callBack){
        anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
        Observable<MyAddressUpdate> myAddAddressObservable = anInterface.RetrofitMyAddressUpdate(userId, sessionId, realName, phone, address, zipCode);
        myAddAddressObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyAddressUpdate>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailer("请求失败");
                    }

                    @Override
                    public void onNext(MyAddressUpdate myAddressUpdate) {
                        callBack.onSuccess(myAddressUpdate);
                    }
                });
    }

    public interface CallBack{
        void onSuccess(MyAddressUpdate myAddressUpdate);
        void onFailer(String msg);
    }

    public void onDestroy(){
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
