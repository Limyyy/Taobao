package com.gx.tianba.fragment.my.mychildfragment.mywallet.presenter;

import com.gx.tianba.fragment.my.mychildfragment.mywallet.bean.MyWallet;
import com.gx.tianba.fragment.my.mychildfragment.mywallet.model.MyWalletModel;
import com.gx.tianba.fragment.my.mychildfragment.mywallet.view.IMyWallet;

public class MyWalletPresenter {
    IMyWallet iMyWallet;
    MyWalletModel myWalletModel;

    public MyWalletPresenter(IMyWallet iMyWallet) {
        this.iMyWallet = iMyWallet;
        myWalletModel=new MyWalletModel();
    }

    public void setPreMyWalletUrl(int userId,String sessionId,int page,int count){
        myWalletModel.setModMyWalletUrl(userId, sessionId, page, count, new MyWalletModel.CallBack() {
            @Override
            public void onSuccess(MyWallet myWallet) {
                iMyWallet.onSuccess(myWallet);
            }
        });
    }

    public void onDestory(){
        if (myWalletModel!=null) {
            myWalletModel.onDestroy();
            myWalletModel=null;
        }
    }
}
