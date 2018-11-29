package com.gx.tianba.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gx.tianba.MainActivity;
import com.gx.tianba.R;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.login.presenter.LoginPresenter;
import com.gx.tianba.login.view.ILoginView;
import com.gx.tianba.fragment.seller.activity.SellerActivity;
import com.gx.tianba.util.ButtonUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    private EditText edName;
    private EditText edPwd;
    private Button btnRegister;
    private Button btnLogin;
    private LoginPresenter loginPresenter;
    private ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        loginPresenter = new LoginPresenter(this);
    }

    private void initView() {
        edName = (EditText) findViewById(R.id.edName);
        edPwd = (EditText) findViewById(R.id.edPwd);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        bar = (ProgressBar) findViewById(R.id.bar);
        bar.setOnClickListener(this);
        bar.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                break;
            case R.id.btnLogin:
                //如果不是快速点击按钮的话才登录
                if (!ButtonUtil.ifFastClick()){
                    submit();
                }
                break;
        }
    }

    private void submit() {
        // validate
        String edNameString = edName.getText().toString().trim();
        if (TextUtils.isEmpty(edNameString)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

        String edPwdString = edPwd.getText().toString().trim();
        if (TextUtils.isEmpty(edPwdString)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        loginPresenter.login(edName.getText().toString().trim(), edPwd.getText().toString().trim());
    }

    @Override
    public void showLoading() {
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onLoginSuccess(Login login) {
        bar.setVisibility(View.GONE);
    }

    @Override
    public void onLoginFailer(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this,""+msg,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    //防止内存泄漏
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        loginPresenter.onDestory();
    }
}
