package com.gx.tianba.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gx.tianba.R;

/**
 * 启动页面
 */
public class LoadingActivity extends AppCompatActivity {

    private ViewPager vpLoadingPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initView();
        
    }

    private void initView() {
        vpLoadingPage = (ViewPager) findViewById(R.id.vpLoadingPage);
    }
}
