package com.gx.tianba.login.presenter;

import com.gx.tianba.login.bean.Login;
import com.gx.tianba.login.model.LoginModel;
import com.gx.tianba.login.view.ILoginView;

public class LoginPresenter {
   LoginModel model;
   ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
        model=new LoginModel();
    }

    public void login(String mobile,String password){
        model.login(mobile, password, new LoginModel.loginCallBack() {
            @Override
            public void onSuccess(Login login) {
                loginView.onLoginSuccess(login);
            }

            @Override
            public void onFailer(String msg) {
                loginView.onLoginFailer(msg);
            }
        });
    }
    //防止内存泄漏
    public void  onDestory(){
        if (loginView!=null){
            loginView=null;
        }
    }
}
