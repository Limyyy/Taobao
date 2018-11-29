package com.gx.tianba.fragment.seller.modle;

import com.gx.tianba.util.net.OkHttpUtils;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SellerModel {
    public void showAllGoods(String url, final CallBack callBack){
        OkHttpUtils okHttpUtils=new OkHttpUtils();
        okHttpUtils.enqueueGet(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                callBack.getData(string);
            }
        },url);
    }
    public interface CallBack{
        void getData(String data);
    }
}
