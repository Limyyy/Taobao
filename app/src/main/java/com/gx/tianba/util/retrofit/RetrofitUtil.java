package com.gx.tianba.util.retrofit;


import com.gx.tianba.util.net.HttpUrl;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    public static  RetrofitUtil instance;
    private final Retrofit retrofit;

    public RetrofitUtil() {
        retrofit = new Retrofit.Builder()
                .baseUrl(HttpUrl.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static RetrofitUtil getInstance(){
        if (instance==null) {
            instance=new RetrofitUtil();
        }
        return instance;
    }

    public <T> T  getInterface(Class<T> tClass){
        T t = retrofit.create(tClass);
        return t;
    }
}
