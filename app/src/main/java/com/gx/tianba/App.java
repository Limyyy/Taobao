package com.gx.tianba;

import android.app.Application;

import static com.gx.tianba.util.OkHttpUtils.init;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }
}
