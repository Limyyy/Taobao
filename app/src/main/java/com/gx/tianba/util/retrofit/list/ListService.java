package com.gx.tianba.util.retrofit.list;

import com.gx.tianba.fragment.list.listchild.bean.ListBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

public interface ListService {
    @GET("order/verify/v1/findOrderListByStatus")
    Observable<ListBean> retrofitAllList(@Header("userId")int userId, @Header("sessionId")String sessionId, @Query("status")int status, @Query("page")int page, @Query("count")int count);
}
