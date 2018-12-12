package com.gx.tianba.fragment.my.mychildfragment.mygroup.model;

import com.gx.tianba.fragment.my.mychildfragment.mygroup.bean.MyGroup;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MyGroupModel {

    private RetrofitService anInterface;

    //我的圈子列表
    public void  setModMyGroup(int userId, String sessionId, int page, int count, final CallBack callBack){
        anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
        Observable<MyGroup> myGroupObservable = anInterface.RetrofitMyGroup(userId, sessionId, page, count);
        myGroupObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyGroup>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailer("请求失败");
                    }

                    @Override
                    public void onNext(MyGroup myGroup) {
                        callBack.onSuccess(myGroup);
                    }
                });
    }
    //删除我的圈子
    public void setModMyGroupDelete(int userId, String sessionId, String ids, final CallBack callBack){
        Observable<MyGroup> myGroupObservable = anInterface.RetrofitMyGroupDelete(userId, sessionId, ids);
        myGroupObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MyGroup>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFailer("请求失败");
                    }

                    @Override
                    public void onNext(MyGroup myGroup) {
                        callBack.onDeleteSuccess(myGroup);
                    }
                });
    }

    public interface CallBack{
        void onSuccess(MyGroup myGroup);
        void onFailer(String msg);
        void onDeleteSuccess(MyGroup myGroup);
    }
    public void onDestroy(){
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
