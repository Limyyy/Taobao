package com.gx.tianba.util.retrofit;

import com.gx.tianba.fragment.home.homechildfragment.bean.HomeChildBean;
import com.gx.tianba.fragment.home.homeshowfragment.bean.HomeShow;
import com.gx.tianba.fragment.home.homeshowfragment.bean.HomeShowEvaluate;
import com.gx.tianba.fragment.my.mychildfragment.myaddaddress.bean.MyAddAddress;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.bean.MyAddressUpdate;
import com.gx.tianba.fragment.my.mychildfragment.myfooter.bean.MyFooter;
import com.gx.tianba.fragment.my.mychildfragment.mygroup.bean.MyGroup;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.up.bean.MyPersonUpImage;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.bean.MyPersonUpdate;
import com.gx.tianba.fragment.my.mychildfragment.mywallet.bean.MyWallet;
import com.gx.tianba.fragment.search.bean.MyCircle;
import com.gx.tianba.fragment.shopping.bean.Shopping;
import com.gx.tianba.login.bean.Login;

import java.io.File;

import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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

    //修改默认收货地址
    @POST("user/verify/v1/setDefaultReceiveAddress")
    @FormUrlEncoded
    Observable<MyAddress> RetrofitDefault(@Header("userId") int userId, @Header("sessionId")String sessionId,@Field("id")int id);

    //修改收货地址
    @PUT("user/verify/v1/changeReceiveAddress")
    @FormUrlEncoded
    Observable<MyAddressUpdate>  RetrofitMyAddressUpdate(@Header("userId") int userId, @Header("sessionId")String sessionId, @Field("realName")String realName, @Field("phone")String phone, @Field("address")String address, @Field("zipCode")String zipCode);

    //我的圈子列表
    @GET("circle/verify/v1/findMyCircleById")
    Observable<MyGroup> RetrofitMyGroup(@Header("userId")int userId, @Header("sessionId")String sessionId,@Query("page")int page, @Query("count")int count);

    //删除我的圈子
    @DELETE("circle/verify/v1/deleteCircle")
    Observable<MyGroup> RetrofitMyGroupDelete(@Header("userId")int userId, @Header("sessionId")String sessionId,@Query("circleId")String circleId);

    //圈子列表点赞
    @POST("circle/verify/v1/addCircleGreat")
    @FormUrlEncoded
    Observable<MyCircle> RetrofitMyCircleGreat(@Header("userId")int userId, @Header("sessionId")String sessionId,@Field("circleId")int circleId);

    //圈子列表取消点赞
    @DELETE("circle/verify/v1/cancelCircleGreat")
    Observable<MyCircle> RetrofitMyCircleNoGreat(@Header("userId")int userId, @Header("sessionId")String sessionId,@Query("circleId")int circleId);

    //修改昵称
    @PUT("user/verify/v1/modifyUserNick")
    Observable<MyPersonUpdate> RetrofitMyPersonUpdateName(@Header("userId")int userId, @Header("sessionId")String sessionId,@Query("nickName")String nickName);

    //修改密码
    @PUT("user/verify/v1/modifyUserPwd")
    Observable<MyPersonUpdate> RetrofitMyPersonUpdatePwd(@Header("userId")int userId, @Header("sessionId")String sessionId,@Query("oldPwd")String editOld,@Query("newPwd")String editNew);

    //上传头像
    @Multipart
    @POST("user/verify/v1/modifyHeadPic")
    Observable<MyPersonUpImage> RetrofitMyPersonUpImage(@Header("userId")int userId, @Header("sessionId")String sessionId,  @Part MultipartBody.Part image);

    //商品详情
    @GET("commodity/v1/findCommodityDetailsById")
    Observable<HomeShow> RetrofitHomeShow(@Header("userId")int userId, @Header("sessionId")String sessionId,@Query("commodityId")int commodityId);

    //商品评论列表
    @GET("commodity/v1/CommodityCommentList")
    Observable<HomeShowEvaluate> RetrofitHomeShowEvaluate(@Query("page")int page,@Query("count")int count,@Query("commodityId")int commodityId);

    //购物车列表
    @GET("order/verify/v1/findShoppingCart")
    Observable<Shopping> RetrofitShoppingList(@Header("userId")int userId, @Header("sessionId")String sessionId);
}
