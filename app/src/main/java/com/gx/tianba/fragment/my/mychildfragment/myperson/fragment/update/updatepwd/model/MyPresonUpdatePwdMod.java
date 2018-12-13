package com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatepwd.model;

import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.bean.MyPersonUpdate;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyPresonUpdatePwdMod {

    private RetrofitService anInterface;

    public void setPreMyPersonUpdatePwdUrl(int userId, String sessionId,String editOld,String editNew, final CallBack callBack){
        anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
        Observable<MyPersonUpdate> myPersonUpdateObservable = anInterface.RetrofitMyPersonUpdatePwd(userId, sessionId, editOld,editNew);
        myPersonUpdateObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyPersonUpdate>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MyPersonUpdate myPersonUpdate) {
                        callBack.onSuccess(myPersonUpdate);
                    }
                });
    }

    public interface CallBack{
        void onSuccess(MyPersonUpdate myPersonUpdate);
    }

    public void onDestroy(){
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
