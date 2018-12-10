package com.gx.tianba.fragment.my.mychildfragment.mywallet.model;

import com.gx.tianba.fragment.my.mychildfragment.mywallet.bean.MyWallet;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyWalletModel {

    private RetrofitService anInterface;

    public void setModMyWalletUrl(int userId, String sessionId, int page, int count, final CallBack callBack){
        anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
        Observable<MyWallet> myWalletObservable = anInterface.RetrofitMyWallet(userId, sessionId, page, count);

        myWalletObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyWallet>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyWallet myWallet) {
                        callBack.onSuccess(myWallet);
                    }
                });
    }

    public interface CallBack{
        void onSuccess(MyWallet myWallet);
    }

    public void onDestroy(){
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
