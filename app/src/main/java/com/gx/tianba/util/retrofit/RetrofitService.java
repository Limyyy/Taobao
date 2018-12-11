package com.gx.tianba.util.retrofit;

import com.gx.tianba.fragment.home.homechildfragment.bean.HomeChildBean;
import com.gx.tianba.fragment.my.mychildfragment.myaddaddress.bean.MyAddAddress;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;
import com.gx.tianba.fragment.my.mychildfragment.myfooter.bean.MyFooter;
import com.gx.tianba.fragment.my.mychildfragment.mywallet.bean.MyWallet;
import com.gx.tianba.fragment.search.bean.MyCircle;
import com.gx.tianba.login.bean.Login;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitService {
    //登录
    @POST("user/v1/login")
    @FormUrlEncoded
    Observable<Login> RetrofitLogin(@Field("phone")String phone,@Field("pwd")String pwd);

    //根据关键词查询商品信息
    @GET("commodity/v1/findCommodityByKeyword")
    Observable<HomeChildBean> RetrofitSouSuo(@Query("keyword") String keyword, @Query("page")int page, @Query("count")int count);

    //我的足迹
    @GET("commodity/verify/v1/browseList")
    Observable<MyFooter> RetrofitMyFooter(@Header("userId")int userId,@Header("sessionId")String sessionId,@Query("page")int page,@Query("count")int count);

    //我的钱包
    @GET("user/verify/v1/findUserWallet")
    Observable<MyWallet> RetrofitMyWallet(@Header("userId")int userId,@Header("sessionId")String sessionId,@Query("page")int page,@Query("count")int count);

    //圈子列表
    @GET("circle/v1/findCircleList")
    Observable<MyCircle> RetrofitMyCircle(@Header("userId")int userId,@Header("sessionId")String sessionId,@Query("page")int page,@Query("count")int count);

    //我的收获地址列表
    @GET("user/verify/v1/receiveAddressList")
    Observable<MyAddress> RetrofitMyAddress(@Header("userId")String userId, @Header("sessionId")String sessionId);

    //新增收货地址
    @POST("user/verify/v1/addReceiveAddress")
    @FormUrlEncoded
    Observable<MyAddAddress>  RetrofitMyAddAddress(@Header("userId") int userId, @Header("sessionId")String sessionId, @Field("realName")String realName, @Field("phone")String phone, @Field("address")String address, @Field("zipCode")String zipCode);
}
