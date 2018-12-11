package com.gx.tianba.fragment.my.mychildfragment.myaddress.presenter;

import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.model.MyAddressModel;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.view.IMyAddress;

public class MyAddressPresenter {
    IMyAddress iMyAddress;
    MyAddressModel myAddressModel;

    public MyAddressPresenter(IMyAddress iMyAddress) {
        this.iMyAddress = iMyAddress;
        myAddressModel=new MyAddressModel();
    }

    public void setPreAddressListUrl(String userId,String sessionId){
        myAddressModel.setModAddressListUrl(userId, sessionId, new MyAddressModel.CallBack() {
            @Override
            public void onSuccess(MyAddress myAddress) {
                iMyAddress.onSuccess(myAddress);
            }
        });
    }

    public void onDestroy(){
        if (myAddressModel!=null) {
            myAddressModel.onDestroy();
            myAddressModel=null;
        }
    }
}
