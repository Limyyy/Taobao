package com.gx.tianba.fragment.list.listchild.waitreceive.presenter;

import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.fragment.list.listchild.waitreceive.model.WaitReceiveModel;
import com.gx.tianba.fragment.list.listchild.waitreceive.view.IWaitReceiveView;

public class WaitReceivePresenter {
    IWaitReceiveView iWaitReceiveView;
    WaitReceiveModel waitReceiveModel;

    public WaitReceivePresenter(IWaitReceiveView iWaitReceiveView) {
        this.iWaitReceiveView = iWaitReceiveView;
        waitReceiveModel=new WaitReceiveModel();
    }

    public void setPreWaitReceiveUrl(int userId, String sessionId, int status, int page, int count){
        waitReceiveModel.setModWaitReceiveUrl(userId, sessionId, status, page, count, new WaitReceiveModel.CallBack() {
            @Override
            public void waitReceiveSuccess(ListBean listBean) {
                iWaitReceiveView.waitReceiveSuccess(listBean);
            }
        });
    }

    public void onDestroy(){
        if (waitReceiveModel!=null) {
            waitReceiveModel.onDestroy();
            waitReceiveModel=null;
        }
    }
}
