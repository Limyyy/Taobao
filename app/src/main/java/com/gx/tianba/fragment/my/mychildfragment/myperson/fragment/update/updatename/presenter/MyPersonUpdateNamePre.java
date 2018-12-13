package com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatename.presenter;

import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.bean.MyPersonUpdate;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatename.model.MyPersonUpdateNameMod;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatename.view.IMyPersonUpdateNameView;

public class MyPersonUpdateNamePre{
    IMyPersonUpdateNameView iMyPersonUpdateNameView;
    MyPersonUpdateNameMod myPersonUpdateNameMod;

    public MyPersonUpdateNamePre(IMyPersonUpdateNameView iMyPersonUpdateNameView) {
        this.iMyPersonUpdateNameView = iMyPersonUpdateNameView;
        myPersonUpdateNameMod=new MyPersonUpdateNameMod();
    }

    public void setPreMyPersonUpdateNameUrl(int userId,String sessionId,String nickName){
        myPersonUpdateNameMod.setModMyPersonUpdateNameUrl(userId, sessionId, nickName, new MyPersonUpdateNameMod.CallBack() {
            @Override
            public void onSuccess(MyPersonUpdate myPersonUpdate) {
                iMyPersonUpdateNameView.onSuccess(myPersonUpdate);
            }
        });
    }

    public void onDestroy(){
        if (myPersonUpdateNameMod!=null) {
            myPersonUpdateNameMod.onDestroy();
            myPersonUpdateNameMod=null;
        }
    }
}
