package com.gx.tianba.login.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gx.tianba.MainActivity;
import com.gx.tianba.R;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.login.presenter.LoginPresenter;
import com.gx.tianba.login.view.ILoginView;
import com.gx.tianba.util.ButtonUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    private EditText edName;
    private EditText edPwd;
    private Button btnRegister;
    private Button btnLogin;
    private LoginPresenter loginPresenter;
    private ProgressBar bar;
    private SharedPreferences sp;
    private CheckBox remember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        sp();
        loginPresenter = new LoginPresenter(this);
    }

    private void sp() {
        //判断是否已经登录过
        sp = getSharedPreferences("login", MODE_PRIVATE);
        boolean islogin = sp.getBoolean("islogin", false);
        //登录过的话
        if (islogin) {
            //给账号和密码赋值
            remember.setChecked(islogin);
            String name = sp.getString("name", "");
            String password = sp.getString("password", "");
            edName.setText(name);
            edPwd.setText(password);
        }
        //如果没有登录过则去登录
        else {
            submit();
        }
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
        remember = (CheckBox) findViewById(R.id.remember);
        remember.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                break;
            case R.id.btnLogin:
                //如果不是快速点击按钮的话才登录
                if (!ButtonUtil.ifFastClick()) {
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
        //bar点击登录的时候显示
        loginPresenter.login(edName.getText().toString().trim(), edPwd.getText().toString().trim());
    }

    @Override
    public void showLoading() {
        //隐藏bar
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bar.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void hideLoading() {
        //隐藏bar
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onLoginSuccess(final Login login) {
        //隐藏bar
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                bar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onLoginFailer(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(LoginActivity.this, "" + msg, Toast.LENGTH_SHORT).show();
                //登录成功之后判断记住密码是否选中保存账号密码
                if (remember.isChecked()){
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("islogin", true);
                    edit.putString("name", edName.getText().toString().trim());
                    edit.putString("password", edPwd.getText().toString().trim());
                    edit.commit();
                }
                else {
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putBoolean("islogin", false);
                    edit.putString("name", "");
                    edit.putString("password", "");
                    edit.commit();
                }
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
