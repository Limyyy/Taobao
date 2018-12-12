package com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.view;

import com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.bean.MyAddressUpdate;

public interface IMyAddressUpdate {
    void onSuccess(MyAddressUpdate myAddressUpdate);
    void onFailer(String msg);
}
