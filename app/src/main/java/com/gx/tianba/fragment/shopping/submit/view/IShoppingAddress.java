package com.gx.tianba.fragment.shopping.submit.view;

import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;
import com.gx.tianba.fragment.shopping.submit.bean.ShoppingSubmit;

public interface IShoppingAddress {
    void onShoppingAddressSuccess(MyAddress myAddress);
    void onShoppingSubmit(ShoppingSubmit shoppingSubmit);
}
