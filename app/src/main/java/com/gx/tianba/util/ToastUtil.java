package com.gx.tianba.util;

import android.app.Application;
import android.os.Handler;
import android.widget.Toast;

import com.gx.tianba.App;

/**
 * Toast工具类
 */
public class ToastUtil {
    public static void Toast(final String msg){
        if (msg!=null) {
            Toast.makeText(App.TAG,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
