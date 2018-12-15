package com.gx.tianba.fragment.list.listchild.waitpay.model;

import com.gx.tianba.fragment.list.listchild.alllist.model.AlllistModel;
import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;
import com.gx.tianba.util.retrofit.list.ListService;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class WaitPayModel {

    private ListService anInterface;

    public void setModWaitPayUrl(int userId, String sessionId, int status, int page, int count,final CallBack callBack){
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
                        callBack.waitPaySuccess(listBean);
                    }
                });
    }

    public void setModDeleteListUrl(int userId, String sessionId, String orderId,final DeleteCallBack deleteCallBack) {
        Observable<ListBean> listBeanObservable = anInterface.retrofitDeleteList(userId, sessionId, orderId);
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
                        deleteCallBack.deleteListSuccess(listBean);
                    }
                });
    }

    public interface DeleteCallBack{
        void deleteListSuccess(ListBean listBean);
    }
    public interface CallBack{
        void waitPaySuccess(ListBean listBean);
    }

    public void onDestroy(){
        if (anInterface!=null) {
            anInterface=null;
        }
    }
}
