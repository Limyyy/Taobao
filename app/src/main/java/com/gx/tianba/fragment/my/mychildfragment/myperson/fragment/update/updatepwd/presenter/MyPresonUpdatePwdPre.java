package com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatepwd.presenter;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.bean.MyPersonUpdate;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatepwd.model.MyPresonUpdatePwdMod;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatepwd.view.IMyPresonUpdatePwdView;

public class MyPresonUpdatePwdPre {
    IMyPresonUpdatePwdView iMyPresonUpdatePwdView;
    MyPresonUpdatePwdMod myPresonUpdatePwdMod;

    public MyPresonUpdatePwdPre(IMyPresonUpdatePwdView iMyPresonUpdatePwdView) {
        this.iMyPresonUpdatePwdView = iMyPresonUpdatePwdView;
        myPresonUpdatePwdMod=new MyPresonUpdatePwdMod();
    }

    public void setPreMyPersonUpdatePwdUrl(int userId,String sessionId,String editOld,String editNew){
        myPresonUpdatePwdMod.setPreMyPersonUpdatePwdUrl(userId, sessionId, editOld,editNew, new MyPresonUpdatePwdMod.CallBack() {
            @Override
            public void onSuccess(MyPersonUpdate myPersonUpdate) {
                iMyPresonUpdatePwdView.onSuccess(myPersonUpdate);
            }
        });
    }

    public void onDestroy(){
        if (myPresonUpdatePwdMod!=null) {
            myPresonUpdatePwdMod.onDestroy();
            myPresonUpdatePwdMod=null;
        }
    }
}
