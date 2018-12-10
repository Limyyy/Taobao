package com.gx.tianba.login.model;

import com.google.gson.Gson;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.callback.OkCallBackUtil;
import com.gx.tianba.util.callback.OkHttpListner;
import com.gx.tianba.util.net.HttpUrl;
import com.gx.tianba.util.net.OkHttpUtils;
import com.gx.tianba.util.retrofit.RetrofitService;
import com.gx.tianba.util.retrofit.RetrofitUtil;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginModel {
    public void login(String mobile,String password,final loginCallBack loginCallBack){
       /* Map<String,String> map=new HashMap<>();
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
        }),HttpUrl.LOGIN_URL,map);*/
        RetrofitService anInterface = RetrofitUtil.getInstance().getInterface(RetrofitService.class);
        Observable<Login> loginObservable = anInterface.RetrofitLogin(mobile, password);
        loginObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Login>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        loginCallBack.onFailer("登录失败");
                    }

                    @Override
                    public void onNext(Login login) {
                        loginCallBack.onSuccess(login);
                    }
                });

    }

    public interface loginCallBack{
        void onSuccess(Login login);
        void onFailer(String msg);
    }
}
