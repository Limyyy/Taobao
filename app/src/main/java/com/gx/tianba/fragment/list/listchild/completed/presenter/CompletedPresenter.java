package com.gx.tianba.fragment.list.listchild.completed.presenter;

import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.fragment.list.listchild.completed.model.CompletedModel;
import com.gx.tianba.fragment.list.listchild.completed.view.ICompletedView;

public class CompletedPresenter {
    ICompletedView iCompletedView;
    CompletedModel completedModel;

    public CompletedPresenter(ICompletedView iCompletedView) {
        this.iCompletedView = iCompletedView;
        completedModel=new CompletedModel();
    }

    public void setPreCompletedList(int userId, String sessionId, int status, int page, int count){
        completedModel.setModCompletedList(userId, sessionId, status, page, count, new CompletedModel.ListCallBack() {
            @Override
            public void onCompletedSuccess(ListBean listBean) {
                iCompletedView.onCompletedSuccess(listBean);
            }
        });
    }

    public void onDestroy(){
        if (completedModel!=null) {
            completedModel.onDestroy();
            completedModel=null;
        }
    }
}
