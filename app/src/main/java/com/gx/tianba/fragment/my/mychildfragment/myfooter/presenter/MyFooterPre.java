package com.gx.tianba.fragment.my.mychildfragment.myfooter.presenter;

import com.gx.tianba.fragment.my.mychildfragment.myfooter.bean.MyFooter;
import com.gx.tianba.fragment.my.mychildfragment.myfooter.model.MyFooterMod;
import com.gx.tianba.fragment.my.mychildfragment.myfooter.view.IMyFooter;

public class MyFooterPre {

    IMyFooter iMyFooter;
    MyFooterMod myFooterMod;

    public MyFooterPre(IMyFooter iMyFooter) {
        this.iMyFooter = iMyFooter;
        myFooterMod=new MyFooterMod();
    }

    public void setFooterPreUrl(int userId, String sessionId, int page, int count) {
        myFooterMod.setFooterModUrl(userId, sessionId, page, count, new MyFooterMod.myFooterCallBack() {
            @Override
            public void successMyFooter(MyFooter myFooter) {
                iMyFooter.myFooterSuccess(myFooter);
            }

            @Override
            public void failerMyFooter(String msg) {
                iMyFooter.myFooterFailer(msg);
            }
        });
    }

    public void  onDestroy(){
        if (myFooterMod!=null) {
            myFooterMod.onDestroy();
            myFooterMod=null;
        }
    }
}
