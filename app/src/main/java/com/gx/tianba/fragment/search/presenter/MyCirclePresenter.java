package com.gx.tianba.fragment.search.presenter;

import com.gx.tianba.fragment.search.bean.MyCircle;
import com.gx.tianba.fragment.search.model.MyCircleModel;
import com.gx.tianba.fragment.search.view.IMyCircle;

public class MyCirclePresenter {
    IMyCircle iMyCircle;
    MyCircleModel myCircleModel;

    public MyCirclePresenter(IMyCircle iMyCircle) {
        this.iMyCircle = iMyCircle;
        myCircleModel=new MyCircleModel();
    }

    public void setPreMyCircleUrl(int userId, String sessionId, int page, int count){
        myCircleModel.setModMyCircleUrl(userId, sessionId, page, count, new MyCircleModel.CallBack() {
            @Override
            public void onSuccess(MyCircle myCircle) {
                iMyCircle.onSuccess(myCircle);
            }

            @Override
            public void onFailer(String msg) {
                iMyCircle.onFailer(msg);
            }
        });
    }


    public void onDestroy(){
        if (myCircleModel!=null) {
            myCircleModel.onDestroy();
            myCircleModel=null;
        }
    }
}
