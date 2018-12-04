package com.gx.tianba.fragment.home.model;

import com.google.gson.Gson;
import com.gx.tianba.fragment.home.bean.Banner;
import com.gx.tianba.fragment.home.bean.Home;
import com.gx.tianba.regis.model.RegisModel;
import com.gx.tianba.util.net.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeModel {

    public void setTwoUrl(String bannershow, final String homelistdata, final CallBack callBack) {
        OkHttpUtils.enqueueGet(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.code()==200){
                    String trim = response.body().string().trim();
                    Gson gson=new Gson();
                    final Banner banner = gson.fromJson(trim, Banner.class);
                    //解析完banner后再去请求home
                    OkHttpUtils.enqueueGet(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {

                        }
                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            if (response.code()==200){
                                String trim = response.body().string().trim();
                                Gson gson=new Gson();
                                Home home = gson.fromJson(trim, Home.class);
                                callBack.backData(banner,home);
                            }
                        }
                    },homelistdata);
                }
            }
        },bannershow);

    }

    public interface CallBack{
        void backData(Banner banner, Home home);
    }
}
