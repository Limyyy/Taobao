package com.gx.tianba;

import android.app.Application;

import com.gx.tianba.util.fresco.FrescoUtil;

import static com.gx.tianba.util.net.OkHttpUtils.init;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
        FrescoUtil.initFresco(this);
    }
}
