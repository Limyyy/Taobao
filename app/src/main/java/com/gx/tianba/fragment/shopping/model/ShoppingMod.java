package com.gx.tianba.fragment.shopping.model;

import com.gx.tianba.fragment.shopping.bean.Shopping;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShoppingMod {

    private RetrofitService anInterface;

    public void setModShoppingList(int userId, String sessionId, final CallBack callBack){
        anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
        Observable<Shopping> shoppingObservable = anInterface.RetrofitShoppingList(userId, sessionId);

        shoppingObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Shopping>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Shopping shopping) {
                        callBack.onSuccess(shopping);
                    }
                });
    }

    public interface CallBack{
        void onSuccess(Shopping shopping);
    }

    public void onDestroy(){
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
