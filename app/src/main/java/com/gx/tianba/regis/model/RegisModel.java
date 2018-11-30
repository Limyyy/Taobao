package com.gx.tianba.regis.model;

import com.gx.tianba.util.net.HttpUrl;
import com.gx.tianba.util.net.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class RegisModel {

    public void presenterModel(String username,String password,CallBack callBack){
        OkHttpUtils.enqueueGet(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        },HttpUrl.REGISTER);
    }

    public interface CallBack{
        void backData(String msg);
    }
}
