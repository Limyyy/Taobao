package com.gx.tianba.fragment.shopping.bean;

import java.io.Serializable;
import java.util.List;

public class Shopping implements Serializable{

    /**
     * result : [{"commodityId":8,"commodityName":"Lara style蜜颊润泽腮红","count":3,"pic":"http://172.17.8.100/images/small/commodity/mzhf/cz/6/1.jpg","price":19}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

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

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable {
        /**
         * commodityId : 8
         * commodityName : Lara style蜜颊润泽腮红
         * count : 3
         * pic : http://172.17.8.100/images/small/commodity/mzhf/cz/6/1.jpg
         * price : 19
         */

        private int commodityId;
        private String commodityName;
        private int count;
        private String pic;
        private int price;
        private boolean isChecked;

        public boolean isChecked() {
            return isChecked;
        }

        public void setChecked(boolean checked) {
            isChecked = checked;
        }

        public int getCommodityId() {
            return commodityId;
        }

        public void setCommodityId(int commodityId) {
            this.commodityId = commodityId;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }
}
