package com.gx.tianba;

import android.app.Application;
import android.content.Context;

import com.gx.tianba.util.fresco.FrescoUtil;

import static com.gx.tianba.util.net.OkHttpUtils.init;

public class App extends Application {


    public static  Context TAG;

    @Override
    public void onCreate() {
        super.onCreate();
        init();
        TAG=this;
        FrescoUtil.initFresco(this);
    }
}
