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
    //圈子列表
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

            @Override
            public void onGreatSuccess(MyCircle myCircle) {

            }

            @Override
            public void onNoGreatSuccess(MyCircle myCircle) {

            }
        });
    }
    //点赞
    public void setPreGreatCircleUrl(int userId, String sessionId, int circleId){
        myCircleModel.setPreGreatCircleUrl(userId, sessionId, circleId, new MyCircleModel.CallBack() {
            @Override
            public void onSuccess(MyCircle myCircle) {

            }

            @Override
            public void onFailer(String msg) {

            }

            @Override
            public void onGreatSuccess(MyCircle myCircle) {
                iMyCircle.onGreatSuccess(myCircle);
            }

            @Override
            public void onNoGreatSuccess(MyCircle myCircle) {

            }
        });
    }
    //取消点赞
    public void setPreNoGreatCircleUrl(int userId, String sessionId, int circleId){
        myCircleModel.setPreGreatNoCircleUrl(userId, sessionId, circleId, new MyCircleModel.CallBack() {
            @Override
            public void onSuccess(MyCircle myCircle) {

            }

            @Override
            public void onFailer(String msg) {

            }

            @Override
            public void onGreatSuccess(MyCircle myCircle) {

            }

            @Override
            public void onNoGreatSuccess(MyCircle myCircle) {
                iMyCircle.onNoGreatSuccess(myCircle);
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
