package com.gx.tianba.regis.model;

import com.google.gson.Gson;
import com.gx.tianba.regis.bean.Regis;
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
        OkHttpUtils.enqueuePosttwo(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code()==200){
                    String trim = response.body().string().trim();
                    Gson gson=new Gson();
                    Regis regis = gson.fromJson(trim, Regis.class);
                    String message = regis.getMessage();
                    if (regis.getStatus().equals("0000")) {
                        callBack.backSuccessData(message,0000);
                    }
                    else {
                        callBack.backFailerData(message);
                    }
                }

            }
        },HttpUrl.REGISTER,map);
    }

    public interface CallBack{
        void backSuccessData(String msg,int code);
        void backFailerData(String msg);
    }
}
