package com.gx.tianba.regis.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gx.tianba.R;
import com.gx.tianba.login.activity.LoginActivity;
import com.gx.tianba.regis.presenter.RegisPresenter;
import com.gx.tianba.regis.view.IRegis;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,IRegis {

    private TextView regis_back;
    private EditText regis_edit_username;
    private EditText regis_edit_password;
    private Button regis_button;
    private RegisPresenter regisPresenter;
    private String username;
    private String password;
    private Button yesremeber;
    private Button noremeber;
    private AlertDialog alertDialog;
    private EditText regis_edit_yzm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        //绑定p层和activity层
        regisPresenter = new RegisPresenter(this);

    }
    //find view
    private void initView() {
        regis_back = (TextView) findViewById(R.id.regis_back);
        regis_edit_username = (EditText) findViewById(R.id.regis_edit_username);
        regis_edit_password = (EditText) findViewById(R.id.regis_edit_password);
        regis_button=findViewById(R.id.regis_button);
        regis_edit_yzm = findViewById(R.id.regis_edit_yzm);
        regis_button.setOnClickListener(this);
        regis_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regis_button:
                //注册
                submit();
                break;
            case R.id.regis_back:
                //取消注册返回登录界面
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
        }
    }

    private void submit() {
        //非空验证
        username = regis_edit_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        password = regis_edit_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }
        //去请求数据注册
        regisPresenter.presenterRegister(username,password);
    }

    //解决mvp内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        regisPresenter.destory();
    }

    @Override
    public void regisSuccess(final String msg, final int code) {
        //Toast.makeText(RegisterActivity.this,""+msg,Toast.LENGTH_SHORT).show();
        //注册成功后弹出对话框
        AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
        View view = View.inflate(RegisterActivity.this, R.layout.regis_is_remeber_alert, null);
        builder.setView(view);
        alertDialog = builder.create();
        alertDialog.show();
        yesremeber = view.findViewById(R.id.yes_remeber);
        noremeber = view.findViewById(R.id.no_remeber);
        //记住的话给登录页面返回值
        yesremeber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//关闭弹框
                alertDialog.dismiss();
                Intent intent = new Intent();
                intent.putExtra("username",username);
                intent.putExtra("password",password);
                intent.putExtra("regisischeck",true);
                setResult(2,intent);
                finish(); //结束当前的activity
            }
            });
        //不记住的话直接关闭弹框
        noremeber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }});
    }

    @Override
    public void regisFailer(final String msg) {
        Toast.makeText(RegisterActivity.this,""+msg,Toast.LENGTH_SHORT).show();
    }
}
