package com.gx.tianba.login.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gx.tianba.MainActivity;
import com.gx.tianba.R;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.login.presenter.LoginPresenter;
import com.gx.tianba.login.view.ILoginView;
import com.gx.tianba.regis.activity.RegisterActivity;
import com.gx.tianba.util.ButtonUtil;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, ILoginView {

    private EditText edName;
    private EditText edPwd;
    private TextView btnRegister;
    private Button btnLogin;
    private LoginPresenter loginPresenter;
    private SharedPreferences sp;
    private CheckBox remember;
    private ImageView iseyes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        sp();
        loginPresenter = new LoginPresenter(this);
    }


    @SuppressLint("ClickableViewAccessibility")
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

        //小眼睛是否显示密码
        iseyes.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        //Log.w("----","按下");
                        edPwd.setInputType(InputType.TYPE_CLASS_NUMBER);
                        break;
                    case MotionEvent.ACTION_UP:
                        //Log.w("----","抬起");
                        edPwd.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;
                }
                return false;
            }
        });
    }



    private void initView() {
        edName = (EditText) findViewById(R.id.edName);
        edPwd = (EditText) findViewById(R.id.edPwd);
        btnRegister =  findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
        remember = (CheckBox) findViewById(R.id.remember);
        remember.setOnClickListener(this);
        iseyes = findViewById(R.id.is_eyes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                //跳转到注册页面
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivityForResult(intent,1);
                break;
            case R.id.btnLogin:
                //如果不是快速点击按钮的话才登录
                if (!ButtonUtil.ifFastClick()) {
                    submit();
                }
                break;
        }
    }
    //登录
    private void submit() {
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
    public void onLoginSuccess(final Login login) {
        Toast.makeText(LoginActivity.this, "" + login.getMessage(), Toast.LENGTH_SHORT).show();
        //登录成功之后判断记住密码是否选中保存账号数据
        if (remember.isChecked()){
            SpUtil.putSpData(login,true,edName.getText().toString().trim(),edPwd.getText().toString().trim());
        }
            //保存之后跳转
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    //登录失败的Toast
    @Override
    public void onLoginFailer(final String msg) {
        ToastUtil.Toast(msg);
    }

    //设置注册之后是否要展示的用户名和密码
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1){
            if (resultCode==2){
                boolean regisischeck = data.getBooleanExtra("regisischeck", false);
                String username = data.getStringExtra("username");
                String password = data.getStringExtra("password");
                remember.setChecked(regisischeck);
                edName.setText(username);
                edPwd.setText(password);
            }
        }
    }

    //防止内存泄漏
    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        loginPresenter.onDestory();
    }
}
