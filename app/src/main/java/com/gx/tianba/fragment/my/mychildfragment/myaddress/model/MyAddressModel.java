package com.gx.tianba.fragment.my.mychildfragment.myaddress.model;

import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;
import com.gx.tianba.util.sp.SpUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyAddressModel {

    private RetrofitService anInterface;

    public void setModAddressListUrl(String userId, String sessionId, final CallBack callBack){
        anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
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

    public void setPreAddressDefault(int id, final CallBackTwo callBackTwo){
        Login.ResultBean spData = SpUtil.getSpData();
        Observable<MyAddress> myAddressObservable = anInterface.RetrofitDefault(spData.getUserId(), spData.getSessionId(), id);
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
                        callBackTwo.onSuccess(myAddress);
                    }
                });
    }



    public interface CallBack{
        void onSuccess(MyAddress myAddress);
    }
    //设置默认地址
    public interface CallBackTwo{
        void onSuccess(MyAddress myAddress);
    }

    public void onDestroy(){
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
