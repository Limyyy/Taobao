package com.gx.tianba.fragment.list.listchild.waitpay.presenter;

import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.fragment.list.listchild.waitpay.model.WaitPayModel;
import com.gx.tianba.fragment.list.listchild.waitpay.view.IWaitPayView;

public class WaitPayPresenter {
    IWaitPayView iWaitPayView;
    WaitPayModel waitPayModel;

    public WaitPayPresenter(IWaitPayView iWaitPayView) {
        this.iWaitPayView = iWaitPayView;
        waitPayModel=new WaitPayModel();
    }
    public void setPreWaitPayUrl(int userId, String sessionId, int status, int page, int count){
        waitPayModel.setModWaitPayUrl(userId, sessionId, status, page, count, new WaitPayModel.CallBack() {

            @Override
            public void waitPaySuccess(ListBean listBean) {
                iWaitPayView.waitPaySuccess(listBean);
            }
        });
    }


    public void onDestroy(){
        if (waitPayModel!=null) {
            waitPayModel.onDestroy();
            waitPayModel=null;
        }
    }
}
