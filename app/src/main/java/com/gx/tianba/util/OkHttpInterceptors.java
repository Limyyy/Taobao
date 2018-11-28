package com.gx.tianba.util;


import android.util.Log;

import java.io.IOException;
import java.util.logging.Logger;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpInterceptors implements Interceptor {

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request  request= chain.request();
        long t1 = System.nanoTime();
        Log.w("-------------:",""+request.url()+""+chain.connection()+""+request.headers());
        Response response = chain.proceed(request);
        long t2 = System.nanoTime();
        Log.w("-------------:",""+response.request().url()+""+(t2-t1)/1e6d+""+response.headers());
        return response;
    }
}
