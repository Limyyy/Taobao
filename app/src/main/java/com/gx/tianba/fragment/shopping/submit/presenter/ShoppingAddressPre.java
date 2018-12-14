package com.gx.tianba.fragment.shopping.submit.presenter;

import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;
import com.gx.tianba.fragment.shopping.submit.bean.ShoppingSubmit;
import com.gx.tianba.fragment.shopping.submit.model.ShoppingAddressMod;
import com.gx.tianba.fragment.shopping.submit.view.IShoppingAddress;

public class ShoppingAddressPre {
    IShoppingAddress iShoppingAddress;
    ShoppingAddressMod shoppingAddressMod;

    public ShoppingAddressPre(IShoppingAddress iShoppingAddress) {
        this.iShoppingAddress = iShoppingAddress;
        shoppingAddressMod=new ShoppingAddressMod();
    }

    public void setPreShoppingAddressList(String userId,String sessionId){
        shoppingAddressMod.setModShoppingAddressList(userId, sessionId, new ShoppingAddressMod.CallBack() {
            @Override
            public void onShoppingAddressSuccess(MyAddress myAddress) {
                iShoppingAddress.onShoppingAddressSuccess(myAddress);
            }
        });
    }
    public void setPreShoppingSubmit(int userId,String sessionId,double totalPrice,int addressId,String orderInfo){
        shoppingAddressMod.setModShoppingSubmit(userId, sessionId, totalPrice, addressId, orderInfo, new ShoppingAddressMod.CallBackSubmit() {
            @Override
            public void onShoppingSubmit(ShoppingSubmit shoppingSubmit) {
                iShoppingAddress.onShoppingSubmit(shoppingSubmit);
            }
        });
    }

    public void onDestroy(){
        if (shoppingAddressMod!=null) {
            shoppingAddressMod.onDestroy();
            shoppingAddressMod=null;
        }
    }
}
