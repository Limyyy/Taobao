package com.gx.tianba.fragment.list.listchild.alllist.model;

import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AlllistModel {

    private RetrofitService anInterface;

    public void setModAllListUrl(int userId, String sessionId, int status, int page, int count, final CallBack callBack){
        anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
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
                        callBack.alllistSuccess(listBean);
                    }
                });
    }

    public interface CallBack{
        void alllistSuccess(ListBean listBean);
    }

    public void onDestroy(){
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
