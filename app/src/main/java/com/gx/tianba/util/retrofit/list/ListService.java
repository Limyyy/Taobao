package com.gx.tianba.util.retrofit.list;

import com.gx.tianba.fragment.list.listchild.bean.ListBean;

import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

public interface ListService {

    //根据订单状态查询订单信息
    @GET("order/verify/v1/findOrderListByStatus")
    Observable<ListBean> retrofitAllList(@Header("userId")int userId, @Header("sessionId")String sessionId, @Query("status")int status, @Query("page")int page, @Query("count")int count);

    //删除订单
    @DELETE("order/verify/v1/deleteOrder")
    Observable<ListBean> retrofitDeleteList(@Header("userId")int userId, @Header("sessionId")String sessionId, @Query("orderId")String orderId);
}
