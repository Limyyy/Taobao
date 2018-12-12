package com.gx.tianba.fragment.my.mychildfragment.mygroup.presenter;

import com.gx.tianba.fragment.my.mychildfragment.mygroup.bean.MyGroup;
import com.gx.tianba.fragment.my.mychildfragment.mygroup.model.MyGroupModel;
import com.gx.tianba.fragment.my.mychildfragment.mygroup.view.IMyGroup;

import retrofit2.http.PUT;

public class MyGroupPresenter {

    IMyGroup iMyGroup;
    MyGroupModel myGroupModel;

    public MyGroupPresenter(IMyGroup iMyGroup) {
        this.iMyGroup = iMyGroup;
        myGroupModel=new MyGroupModel();
    }

    //我的圈子列表
    public void  setPreMyGroup(int userId,String sessionId,int page,int count){
        myGroupModel.setModMyGroup(userId, sessionId, page, count, new MyGroupModel.CallBack() {
            @Override
            public void onSuccess(MyGroup myGroup) {
                iMyGroup.onSuccess(myGroup);
            }

            @Override
            public void onFailer(String msg) {
                iMyGroup.onFailer(msg);
            }

            @Override
            public void onDeleteSuccess(MyGroup myGroup) {

            }
        });
    }

    //删除我的圈子
    public void setPreMyGroupDelete(int userId,String sessionId,String ids){
        myGroupModel.setModMyGroupDelete(userId, sessionId, ids, new MyGroupModel.CallBack() {
            @Override
            public void onSuccess(MyGroup myGroup) {

            }

            @Override
            public void onFailer(String msg) {

            }

            @Override
            public void onDeleteSuccess(MyGroup myGroup) {
                iMyGroup.onDeleteSuccess(myGroup);
            }
        });
    }

    public void onDestroy(){
        if (myGroupModel!=null) {
            myGroupModel.onDestroy();
            myGroupModel=null;
        }
    }
}
