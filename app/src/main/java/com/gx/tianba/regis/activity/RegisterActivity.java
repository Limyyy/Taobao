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
import android.widget.Toast;

import com.gx.tianba.R;
import com.gx.tianba.login.activity.LoginActivity;
import com.gx.tianba.regis.presenter.RegisPresenter;
import com.gx.tianba.regis.view.IRegis;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,IRegis {

    private ImageView regis_back;
    private EditText regis_edit_username;
    private EditText regis_edit_password;
    private EditText regis_edit_sex;
    private EditText regis_edit_age;
    private EditText regis_edit_mobile;
    private Button regis_button;
    private RegisPresenter regisPresenter;
    private String username;
    private String password;
    private String sex;
    private String age;
    private String mobile;
    private Button yesremeber;
    private Button noremeber;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        regisPresenter = new RegisPresenter(this);

    }

    private void initView() {
        regis_back = (ImageView) findViewById(R.id.regis_back);
        regis_edit_username = (EditText) findViewById(R.id.regis_edit_username);
        regis_edit_password = (EditText) findViewById(R.id.regis_edit_password);
        regis_edit_sex = (EditText) findViewById(R.id.regis_edit_sex);
        regis_edit_age = (EditText) findViewById(R.id.regis_edit_age);
        regis_edit_mobile = (EditText) findViewById(R.id.regis_edit_mobile);
        regis_button = (Button) findViewById(R.id.regis_button);
        regis_button.setOnClickListener(this);
        regis_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regis_button:
                submit();
                break;
            case R.id.regis_back:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
        }
    }

    private void submit() {
        // validate
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

        sex = regis_edit_sex.getText().toString().trim();
        if (TextUtils.isEmpty(sex)) {
            Toast.makeText(this, "请输入性别", Toast.LENGTH_SHORT).show();
            return;
        }

        age = regis_edit_age.getText().toString().trim();
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "请输入年龄", Toast.LENGTH_SHORT).show();
            return;
        }

        mobile = regis_edit_mobile.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }
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
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (code==0){
                    Toast.makeText(RegisterActivity.this,""+msg+",请重新注册",Toast.LENGTH_SHORT).show();
                }
                else {
                    //注册成功后弹出对话框
                    AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
                    View view = View.inflate(RegisterActivity.this, R.layout.regis_is_remeber_alert, null);
                    builder.setView(view);
                    alertDialog = builder.create();
                    alertDialog.show();
                    yesremeber = view.findViewById(R.id.yes_remeber);
                    noremeber = view.findViewById(R.id.no_remeber);
                    //记住的话给登录页面返回值
                }
            }
        });
        yesremeber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭弹框
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
            }
        });
    }
}
