package com.gx.tianba.login.presenter;

import com.google.gson.Gson;
import com.gx.tianba.bean.Login;
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
        loginView.showLoading();
        model.login(mobile, password, new LoginModel.loginCallBack() {
            @Override
            public void onSuccess(Login login) {
                loginView.hideLoading();
                loginView.onLoginSuccess(login);
            }

            @Override
            public void onFailer(String msg) {
                loginView.hideLoading();
                loginView.onLoginFailer(msg);
            }

            @Override
            public void onConnectionFailer(String msg) {
                loginView.hideLoading();
                loginView.onLoginFailer(msg);
            }
        });
    }
    public void  onDestory(){
        if (loginView!=null){
            loginView=null;
        }
    }
}
