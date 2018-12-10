package com.gx.tianba.fragment.my.mychildfragment.mywallet.bean;

import java.util.List;

public class MyWallet {

    /**
     * result : {"balance":99999999,"detailList":[]}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultBean {
        /**
         * balance : 99999999
         * detailList : []
         */

        private int balance;
        private List<?> detailList;

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public List<?> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<?> detailList) {
            this.detailList = detailList;
        }
    }
}
