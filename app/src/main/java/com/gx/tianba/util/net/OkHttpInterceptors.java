package com.gx.tianba.util.net;


import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.gx.tianba.App;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpInterceptors implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        /**
         * 得到保存的sessionId和userId
         */
        SharedPreferences login = App.TAG.getSharedPreferences("login", Context.MODE_PRIVATE);
        String sessionId = login.getString("sessionId", "");
        int userId = login.getInt("userId", 0);
        /**
         * 添加请求头
         */
        Request request = chain.request().newBuilder().
                addHeader("sessionId", sessionId)
                .addHeader("userId", "" + userId)
                .build();
        long t1 = System.nanoTime();
        Log.w("-------------:",""+request.url()+""+chain.connection()+""+request.headers());

        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        Log.w("-------------:",""+response.request().url()+""+(t2-t1)/1e6d+""+response.headers());
        return response;
    }
}
