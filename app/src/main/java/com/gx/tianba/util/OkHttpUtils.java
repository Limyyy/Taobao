package com.gx.tianba.util;

import com.google.gson.Gson;
import com.gx.tianba.base.BaseRequest;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtils {
    public static final String MEDIE_TYPE="application/json;charset/utf-8";
    public static Gson gson=new Gson();
    public static final String METHOD_POST="POST";
    public static final String METHOD_GET="GET";
    private static OkHttpClient okHttpClient;

    public static void init(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.readTimeout(3000,TimeUnit.MILLISECONDS);
        builder.connectTimeout(3000,TimeUnit.MILLISECONDS);
        builder.writeTimeout(3000,TimeUnit.MILLISECONDS);
        okHttpClient = builder.build();
    }
    public static Request createRequest(String url, String method, BaseRequest baseRequest){
        RequestBody body=null;
        if (baseRequest!=null){
            String s = gson.toJson(baseRequest);
            MediaType parse = MediaType.parse(MEDIE_TYPE);
            body=RequestBody.create(parse,s);
        }
        Request.Builder builder=new Request.Builder().url(url);
        Request request=null;
        switch (method) {
            case METHOD_GET:
                request=builder.build();
                break;
            case METHOD_POST:
                request=builder.post(body).build();
                break;
        }
        return request;
    }
    public static void enqueueGet(Callback callback, String url){
        Request request = createRequest(url, "GET", null);
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
    public static void enqueuePost(Callback callback,String url,BaseRequest baseRequest){
        Request request = createRequest(url, "POST", baseRequest);
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }
    public static void excutePost(String url,BaseRequest baseRequest) throws IOException {
        Request request = createRequest(url, "POST", baseRequest);
        Call call = okHttpClient.newCall(request);
        call.execute();
    }
    public static void excuteGet(String url) throws IOException {
        Request request = createRequest(url, "GET", null);
        Call call = okHttpClient.newCall(request);
        call.execute();
    }
}
