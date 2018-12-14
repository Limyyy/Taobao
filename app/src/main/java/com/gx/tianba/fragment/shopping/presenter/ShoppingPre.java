package com.gx.tianba.fragment.shopping.presenter;

import com.gx.tianba.fragment.shopping.bean.Shopping;
import com.gx.tianba.fragment.shopping.model.ShoppingMod;
import com.gx.tianba.fragment.shopping.view.IShoppingView;

public class ShoppingPre {
    IShoppingView iShoppingView;
    ShoppingMod shoppingMod;

    public ShoppingPre(IShoppingView iShoppingView) {
        this.iShoppingView = iShoppingView;
        shoppingMod=new ShoppingMod();
    }

    public void setPreShoppingList(int userId,String sessionId){
        shoppingMod.setModShoppingList(userId, sessionId, new ShoppingMod.CallBack() {
            @Override
            public void onSuccess(Shopping shopping) {
                iShoppingView.onSuccess(shopping);
            }
        });
    }

    public void onDestroy(){
        if (shoppingMod!=null) {
            shoppingMod.onDestroy();
            shoppingMod=null;
        }
    }
}
