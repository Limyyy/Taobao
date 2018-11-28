package com.gx.tianba.login.model;

import com.google.gson.Gson;
import com.gx.tianba.base.BaseRequest;
import com.gx.tianba.bean.Login;
import com.gx.tianba.util.HttpUrl;
import com.gx.tianba.util.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class LoginModel {
    public void login(String mobile,String password,final loginCallBack loginCallBack){
        BaseRequest baseRequest=new BaseRequest();
        baseRequest.mobile=mobile;
        baseRequest.password=password;
        OkHttpUtils.enqueuePost(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                int code = response.code();
                if (code==200){
                    Gson gson=new Gson();
                    Login login = gson.fromJson(response.body().string(),Login.class);
                    if (login.getCode().equals("0")){
                        loginCallBack.onSuccess(login);
                    }
                    else {
                        String msg = login.getMsg();
                        loginCallBack.onFailer(msg);
                    }
                }
                else {

                }
            }
        },HttpUrl.LOGIN_URL,baseRequest);
    }

    public interface loginCallBack{
        void onSuccess(Login login);
        void onFailer(String msg);
    }
}
