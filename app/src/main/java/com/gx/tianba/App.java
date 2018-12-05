package com.gx.tianba;

import android.app.Application;
import android.content.Context;

import com.gx.tianba.util.fresco.FrescoUtil;
import com.gx.tianba.util.net.OkHttpUtils;
import com.gx.tianba.util.sp.SpUtil;

import static com.gx.tianba.util.net.OkHttpUtils.init;

public class App extends Application {


    public static  Context TAG;

    @Override
    public void onCreate() {
        super.onCreate();
        TAG=this;
        //初始化OkHttp网络工具类
        OkHttpUtils.init();
        //初始化Fresco
        FrescoUtil.initFresco(this);
        //初始化Sp
        SpUtil.initSp();
    }
}
