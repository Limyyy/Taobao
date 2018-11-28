package com.gx.tianba.login.view;

import com.gx.tianba.bean.Login;

public interface ILoginView {
    void showLoading();
    void hideLoading();
    void onLoginSuccess(Login login);
    void onLoginFailer(String msg);
}
