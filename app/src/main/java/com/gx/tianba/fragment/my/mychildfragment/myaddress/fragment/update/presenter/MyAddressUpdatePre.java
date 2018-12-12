package com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.presenter;

import com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.bean.MyAddressUpdate;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.model.MyAddressUpdateMod;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.view.IMyAddressUpdate;

public class MyAddressUpdatePre {
    IMyAddressUpdate iMyAddressUpdate;
    MyAddressUpdateMod myAddressUpdateMod;

    public MyAddressUpdatePre(IMyAddressUpdate iMyAddressUpdate) {
        this.iMyAddressUpdate = iMyAddressUpdate;
        myAddressUpdateMod=new MyAddressUpdateMod();
    }

    public void setPreMyAddressUpdate(int userId,String sessionId,String realName,String phone,String address,String zipCode){
        myAddressUpdateMod.setModMyAddressUpdate(userId, sessionId, realName, phone, address, zipCode, new MyAddressUpdateMod.CallBack() {
            @Override
            public void onSuccess(MyAddressUpdate myAddressUpdate) {
                iMyAddressUpdate.onSuccess(myAddressUpdate);
            }

            @Override
            public void onFailer(String msg) {
                iMyAddressUpdate.onFailer(msg);
            }
        });
    }

    public void onDestroy(){
        if (myAddressUpdateMod!=null) {
            myAddressUpdateMod.onDestroy();
            myAddressUpdateMod=null;
        }
    }
}
