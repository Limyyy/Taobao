package com.gx.tianba.fragment.list.listchild.waitevaluate.presenter;

import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.fragment.list.listchild.waitevaluate.model.WaitEvalueteModel;
import com.gx.tianba.fragment.list.listchild.waitevaluate.view.IWaitEvalueteView;

public class WaitEvaluetePresenter {
    IWaitEvalueteView iWaitEvalueteView;
    WaitEvalueteModel waitEvalueteModel;

    public WaitEvaluetePresenter(IWaitEvalueteView iWaitEvalueteView) {
        this.iWaitEvalueteView = iWaitEvalueteView;
        waitEvalueteModel=new WaitEvalueteModel();
    }

    public void setPreWaitEvalueteUrl(int userId, String sessionId, int status, int page, int count){
        waitEvalueteModel.setModWaitEvalueteUrl(userId, sessionId, status, page, count, new WaitEvalueteModel.CallBack() {
            @Override
            public void waitEvalueteSuccess(ListBean listBean) {
                iWaitEvalueteView.waitEvalueteSuccess(listBean);
            }
        });
    }

    public void onDestroy(){
        if (waitEvalueteModel!=null) {
            waitEvalueteModel.onDestroy();
            waitEvalueteModel=null;
        }
    }
}
