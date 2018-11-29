package com.gx.tianba.fragment.seller.presenter;

import com.gx.tianba.fragment.seller.modle.SellerModel;
import com.gx.tianba.fragment.seller.view.ISeller;

public class SellerPresenter {
    ISeller iSeller;
    SellerModel sellerModel;

    public SellerPresenter(ISeller iSeller) {
        this.iSeller = iSeller;
        sellerModel=new SellerModel();
    }

    public void showAllGoods(String url){
        sellerModel.showAllGoods(url, new SellerModel.CallBack() {
            @Override
            public void getData(String data) {
                iSeller.backData(data);
            }
        });
    }
}
