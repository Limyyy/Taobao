package com.gx.tianba.fragment.list.listchild.alllist.presenter;

import com.gx.tianba.fragment.list.listchild.alllist.model.AlllistModel;
import com.gx.tianba.fragment.list.listchild.alllist.view.IAlllistView;
import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

public class AlllistPresenter {

    IAlllistView iAlllistView;
    AlllistModel alllistModel;

    public AlllistPresenter(IAlllistView iAlllistView) {
        this.iAlllistView = iAlllistView;
        alllistModel=new AlllistModel();
    }

    public void setPreAllListUrl(int userId, String sessionId, int status, int page, int count){
        alllistModel.setModAllListUrl(userId, sessionId, status, page, count, new AlllistModel.CallBack() {
            @Override
            public void alllistSuccess(ListBean listBean) {
                iAlllistView.alllistSuccess(listBean);
            }
        });
    }

    public void onDestroy(){
        if (alllistModel!=null) {
            alllistModel.onDestroy();
            alllistModel=null;
        }
    }
}
