package com.gx.tianba.fragment.list.listchild.bean;

import java.util.List;

public class ListBean {

    /**
     * orderList : [{"detailList":[{"commentStatus":1,"commodityCount":3,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/5.jpg,","commodityPrice":39,"orderDetailId":65},{"commentStatus":1,"commodityCount":4,"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/5.jpg,","commodityPrice":39,"orderDetailId":66}],"expressCompName":"京东快递","expressSn":"1001","orderId":"2018121508264455863","orderStatus":1,"payAmount":273,"payMethod":1,"userId":63},{"detailList":[{"commentStatus":1,"commodityCount":3,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/5.jpg,","commodityPrice":39,"orderDetailId":55},{"commentStatus":1,"commodityCount":4,"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/5.jpg,","commodityPrice":39,"orderDetailId":56}],"expressCompName":"京东快递","expressSn":"1001","orderId":"2018121420212358663","orderStatus":1,"payAmount":273,"payMethod":1,"userId":63}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<OrderListBean> orderList;

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

    public List<OrderListBean> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderListBean> orderList) {
        this.orderList = orderList;
    }

    public static class OrderListBean {
        /**
         * detailList : [{"commentStatus":1,"commodityCount":3,"commodityId":5,"commodityName":"双头两用修容笔","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/5.jpg,","commodityPrice":39,"orderDetailId":65},{"commentStatus":1,"commodityCount":4,"commodityId":6,"commodityName":"轻柔系自然裸妆假睫毛","commodityPic":"http://172.17.8.100/images/small/commodity/mzhf/cz/4/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/4/5.jpg,","commodityPrice":39,"orderDetailId":66}]
         * expressCompName : 京东快递
         * expressSn : 1001
         * orderId : 2018121508264455863
         * orderStatus : 1
         * payAmount : 273
         * payMethod : 1
         * userId : 63
         */

        private String expressCompName;
        private String expressSn;
        private String orderId;
        private int orderStatus;
        private int payAmount;
        private int payMethod;
        private int userId;
        private List<DetailListBean> detailList;

        public String getExpressCompName() {
            return expressCompName;
        }

        public void setExpressCompName(String expressCompName) {
            this.expressCompName = expressCompName;
        }

        public String getExpressSn() {
            return expressSn;
        }

        public void setExpressSn(String expressSn) {
            this.expressSn = expressSn;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

        public int getPayAmount() {
            return payAmount;
        }

        public void setPayAmount(int payAmount) {
            this.payAmount = payAmount;
        }

        public int getPayMethod() {
            return payMethod;
        }

        public void setPayMethod(int payMethod) {
            this.payMethod = payMethod;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public List<DetailListBean> getDetailList() {
            return detailList;
        }

        public void setDetailList(List<DetailListBean> detailList) {
            this.detailList = detailList;
        }

        public static class DetailListBean {
            /**
             * commentStatus : 1
             * commodityCount : 3
             * commodityId : 5
             * commodityName : 双头两用修容笔
             * commodityPic : http://172.17.8.100/images/small/commodity/mzhf/cz/3/1.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/2.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/3.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/4.jpg,http://172.17.8.100/images/small/commodity/mzhf/cz/3/5.jpg,
             * commodityPrice : 39
             * orderDetailId : 65
             */

            private int commentStatus;
            private int commodityCount;
            private int commodityId;
            private String commodityName;
            private String commodityPic;
            private int commodityPrice;
            private int orderDetailId;

            public int getCommentStatus() {
                return commentStatus;
            }

            public void setCommentStatus(int commentStatus) {
                this.commentStatus = commentStatus;
            }

            public int getCommodityCount() {
                return commodityCount;
            }

            public void setCommodityCount(int commodityCount) {
                this.commodityCount = commodityCount;
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

            public String getCommodityPic() {
                return commodityPic;
            }

            public void setCommodityPic(String commodityPic) {
                this.commodityPic = commodityPic;
            }

            public int getCommodityPrice() {
                return commodityPrice;
            }

            public void setCommodityPrice(int commodityPrice) {
                this.commodityPrice = commodityPrice;
            }

            public int getOrderDetailId() {
                return orderDetailId;
            }

            public void setOrderDetailId(int orderDetailId) {
                this.orderDetailId = orderDetailId;
            }
        }
    }
}
