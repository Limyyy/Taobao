package com.gx.tianba.util.callback;

import android.os.Handler;

import com.google.gson.Gson;
import com.gx.tianba.util.ToastUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 封装OkHttp的CallBack网络工具类
 */
public class OkCallBackUtil implements Callback {

    private Handler handler=new Handler();
    private Gson gson=new Gson();
    private OkHttpListner okHttpListner;

    public OkCallBackUtil(OkHttpListner okHttpListner) {
        this.okHttpListner = okHttpListner;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        failure("请检查网线或者网址是否正确");
    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        int code = response.code();
        switch (code){
            case 200:
                //请求成功
                String trim = response.body().string().trim();
                DataBean dataBean = gson.fromJson(trim, DataBean.class);
                String status = dataBean.getStatus();
                //判断得到的code
                switch (status){
                    //成功
                    case "0000":
                       okHttpListner.OnCallBackSuccess(trim);
                        break;
                    //失败
                    case "1001":
                        okHttpListner.OnCallBackFailer(dataBean.getMessage());
                        break;
                }
                break;
            case 400:
                fail400("客户端请求错误");
                break;
            case 404:
                fail404("资源不存在");
                break;
            case 500:
                fail500("服务端请求错误");
                break;
                default:
                    failDefault("请求错误");
                    break;
        }
    }
    public void fail200(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtil.Toast(msg);
            }
        });
    }
    public void fail400(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtil.Toast(msg);
            }
        });
    }
    public void fail404(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtil.Toast(msg);
            }
        });
    }
    public void fail500(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtil.Toast(msg);
            }
        });
    }
    public void failDefault(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtil.Toast(msg);
            }
        });
    }
    public void failure(final String msg){
        handler.post(new Runnable() {
            @Override
            public void run() {
                ToastUtil.Toast(msg);
            }
        });
    }
}
