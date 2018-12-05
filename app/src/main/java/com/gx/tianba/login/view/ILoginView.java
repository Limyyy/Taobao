package com.gx.tianba.login.view;

import com.gx.tianba.login.bean.Login;

public interface ILoginView {
    void onLoginSuccess(Login login);
    void onLoginFailer(String msg);
}
