package com.gx.tianba.regis.presenter;

import com.gx.tianba.regis.model.RegisModel;
import com.gx.tianba.regis.view.IRegis;

public class RegisPresenter {
    IRegis iRegis;
    RegisModel regisModel;

    public RegisPresenter(IRegis iRegis) {
        this.iRegis = iRegis;
        regisModel=new RegisModel();
    }

    public void presenterRegister(String username,String password){
        regisModel.presenterModel(username, password, new RegisModel.CallBack() {
            @Override
            public void backSuccessData(String msg, int code) {
                iRegis.regisSuccess(msg,code);
            }

            @Override
            public void backFailerData(String msg) {
                iRegis.regisFailer(msg);
            }
        });
    }


    //防止内存泄漏
    public void destory(){
        if (iRegis!=null){
            iRegis=null;
        }
    }
}
