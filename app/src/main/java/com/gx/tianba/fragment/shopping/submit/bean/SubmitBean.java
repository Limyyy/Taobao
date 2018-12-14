package com.gx.tianba.fragment.shopping.submit.bean;

import java.io.Serializable;

public class SubmitBean implements Serializable {
    private int commodityId;
    private int amount;

    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public SubmitBean(int commodityId, int amount) {
        this.commodityId = commodityId;
        this.amount = amount;
    }
    public SubmitBean(){

    }
}
