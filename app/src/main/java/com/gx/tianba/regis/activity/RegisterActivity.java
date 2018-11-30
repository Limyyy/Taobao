package com.gx.tianba.regis.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gx.tianba.R;
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.regis_button:

                break;
        }
    }

    private void submit() {
        // validate
        String username = regis_edit_username.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "请输入用户名", Toast.LENGTH_SHORT).show();
            return;
        }

        String password = regis_edit_password.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
            return;
        }

        String sex = regis_edit_sex.getText().toString().trim();
        if (TextUtils.isEmpty(sex)) {
            Toast.makeText(this, "请输入性别", Toast.LENGTH_SHORT).show();
            return;
        }

        String age = regis_edit_age.getText().toString().trim();
        if (TextUtils.isEmpty(age)) {
            Toast.makeText(this, "请输入年龄", Toast.LENGTH_SHORT).show();
            return;
        }

        String mobile = regis_edit_mobile.getText().toString().trim();
        if (TextUtils.isEmpty(mobile)) {
            Toast.makeText(this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return;
        }

    }

    //解决mvp内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        regisPresenter.destory();
    }

    @Override
    public void regisSuccess(String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
