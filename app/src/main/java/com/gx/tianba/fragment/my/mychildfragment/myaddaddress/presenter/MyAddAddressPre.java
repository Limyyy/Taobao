package com.gx.tianba.fragment.my.mychildfragment.myaddaddress.presenter;

import com.gx.tianba.fragment.my.mychildfragment.myaddaddress.bean.MyAddAddress;
import com.gx.tianba.fragment.my.mychildfragment.myaddaddress.model.MyAddAddressMod;
import com.gx.tianba.fragment.my.mychildfragment.myaddaddress.view.IMyAddAddress;

public class MyAddAddressPre {
    MyAddAddressMod myAddAddressMod;
    IMyAddAddress iMyAddAddress;

    public MyAddAddressPre(IMyAddAddress iMyAddAddress) {
        this.iMyAddAddress = iMyAddAddress;
        myAddAddressMod=new MyAddAddressMod();
    }
    public void setPreMyAddAddressUrl(int userId,String sessionId,String realName,String phone,String address,String zipCode){
        myAddAddressMod.setModMyAddAddressUrl(userId, sessionId, realName, phone, address, zipCode, new MyAddAddressMod.CallBack() {
            @Override
            public void onSuccess(MyAddAddress myAddAddress) {
                iMyAddAddress.onSuccess(myAddAddress);
            }
        });
    }

    public void onDestroy(){
        if (myAddAddressMod!=null) {
            myAddAddressMod.onDestroy();
            myAddAddressMod=null;
        }
    }
}
