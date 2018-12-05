package com.gx.tianba.login.model;

import com.google.gson.Gson;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.callback.OkCallBackUtil;
import com.gx.tianba.util.callback.OkHttpListner;
import com.gx.tianba.util.net.HttpUrl;
import com.gx.tianba.util.net.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

public class LoginModel {
    public void login(String mobile,String password,final loginCallBack loginCallBack){
        Map<String,String> map=new HashMap<>();
        map.put("phone",mobile);
        map.put("pwd",password);
        OkHttpUtils.enqueuePosttwo(new OkCallBackUtil(new OkHttpListner() {
            @Override
            public void OnCallBackSuccess(String json) {
                Gson gson=new Gson();
                Login login = gson.fromJson(json, Login.class);
                loginCallBack.onSuccess(login);
            }

            @Override
            public void OnCallBackFailer(String msg) {
                loginCallBack.onFailer(msg);
            }
        }),HttpUrl.LOGIN_URL,map);
    }

    public interface loginCallBack{
        void onSuccess(Login login);
        void onFailer(String msg);
    }
}
