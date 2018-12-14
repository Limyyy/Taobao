package com.gx.tianba.fragment.shopping.submit.model;

import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;
import com.gx.tianba.fragment.shopping.submit.bean.ShoppingSubmit;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ShoppingAddressMod {

    private RetrofitService anInterface;

    public void setModShoppingAddressList(String userId, String sessionId, final CallBack callBack){
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
                        callBack.onShoppingAddressSuccess(myAddress);
                    }
                });
    }
    public void setModShoppingSubmit(int userId, String sessionId, double totalPrice, int addressId, String orderInfo, final CallBackSubmit callBackSubmit){
        Observable<ShoppingSubmit> shoppingSubmitObservable = anInterface.retrofitShoppingSubmit(userId, sessionId, totalPrice, addressId, orderInfo);

        shoppingSubmitObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ShoppingSubmit>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ShoppingSubmit shoppingSubmit) {
                        callBackSubmit.onShoppingSubmit(shoppingSubmit);
                    }
                });
    }

    public interface CallBackSubmit{
        void onShoppingSubmit(ShoppingSubmit shoppingSubmit);
    }

    public interface CallBack{
        void onShoppingAddressSuccess(MyAddress myAddress);
    }

    public void onDestroy(){
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
