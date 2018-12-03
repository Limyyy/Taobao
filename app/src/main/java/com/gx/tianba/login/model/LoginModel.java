package com.gx.tianba.login.model;

import android.util.Log;

import com.google.gson.Gson;
import com.gx.tianba.base.BaseRequest;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.net.HttpUrl;
import com.gx.tianba.util.net.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginModel {
    public void login(String mobile,String password,final loginCallBack loginCallBack){
        String url = "http://172.17.8.100/small/user/v1/login?phone="+mobile+"&pwd="+password;
        BaseRequest request=new BaseRequest();
        request.phone=mobile;
        request.pwd=password;
        OkHttpUtils.enqueuePost(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                if (code==200){
                    Gson gson=new Gson();
                    Login login = gson.fromJson(response.body().string(), Login.class);
                    if (login.getStatus().equals("0000")){
                        loginCallBack.onSuccess(login);
                    }
                    else {
                        String msg = login.getMessage();
                        loginCallBack.onFailer(msg);
                    }
                }
            }
        },url,new BaseRequest());
    }

    public interface loginCallBack{
        void onSuccess(Login login);
        void onFailer(String msg);
        void onConnectionFailer(String msg);
    }
}
