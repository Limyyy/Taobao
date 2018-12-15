package com.gx.tianba.fragment.list.listchild.completed.model;

import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.util.retrofit.RetrofitUtil;
import com.gx.tianba.util.retrofit.list.ListService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class CompletedModel {
    private ListService anInterface;

    public void setModCompletedList(int userId, String sessionId, int status, int page, int count, final ListCallBack listCallBack){
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
                        listCallBack.onCompletedSuccess(listBean);
                    }
                });
    }

    public interface ListCallBack{
        void onCompletedSuccess(ListBean listBean);
    }

    public interface DeleteCallBack{
        void onDeleteSuccess(ListBean listBean);
    }

    public void onDestroy() {
        if (anInterface!=null) {
            anInterface=null;
        }
    }

}
