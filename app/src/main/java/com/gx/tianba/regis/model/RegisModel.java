package com.gx.tianba.regis.model;

import com.google.gson.Gson;
import com.gx.tianba.regis.bean.Regis;
import com.gx.tianba.util.callback.OkCallBackUtil;
import com.gx.tianba.util.callback.OkHttpListner;
import com.gx.tianba.util.net.HttpUrl;
import com.gx.tianba.util.net.OkHttpUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisModel {

    public void presenterModel(String username, String password, final CallBack callBack){
        Map<String,String> map=new HashMap<>();
        map.put("phone",username);
        map.put("pwd",password);
        OkHttpUtils.enqueuePosttwo(new OkCallBackUtil(new OkHttpListner() {
            @Override
            public void OnCallBackSuccess(String json) {
                Gson gson=new Gson();
                Regis regis = gson.fromJson(json, Regis.class);
                String message = regis.getMessage();
                callBack.backSuccessData(message,0000);
            }
            @Override
            public void OnCallBackFailer(String msg) {
                callBack.backFailerData(msg);
            }
        }),HttpUrl.REGISTER,map);
    }

    public interface CallBack{
        void backSuccessData(String msg,int code);
        void backFailerData(String msg);
    }
}
