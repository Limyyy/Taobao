package com.gx.tianba.util.callback;

public interface OkHttpListner {
    void OnCallBackSuccess(String json);
    void OnCallBackFailer(String msg);
}
