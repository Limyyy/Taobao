package com.gx.tianba.fragment.list.listchild.waitreceive.model;

import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;
import com.gx.tianba.util.retrofit.list.ListService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WaitReceiveModel {

    private ListService anInterface;

    public void setModWaitReceiveUrl(int userId, String sessionId, int status, int page, int count, final CallBack callBack){
        anInterface = RetrofitUtil.getInstance().getInterface(ListService.class);
        Observable<ListBean> listBeanObservable = anInterface.retrofitAllList(userId, sessionId, status, page, count);
        listBeanObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ListBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        callBack.waitReceiveSuccess(listBean);
                    }
                });
    }

    public interface CallBack{
        void waitReceiveSuccess(ListBean listBean);
    }

    public void onDestroy() {
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
