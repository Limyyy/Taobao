package com.gx.tianba.load;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gx.tianba.MainActivity;
import com.gx.tianba.R;
import com.gx.tianba.load.adapter.LoadingPagerAdapter;
import com.gx.tianba.login.activity.LoginActivity;
import com.gx.tianba.util.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * 启动页面
 */
public class LoadingActivity extends AppCompatActivity {

    private ViewPager vpLoadingPage;
    private List<Integer> listImages=new ArrayList<>();
    private View jump;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        initView();
        initListener();;
        initData();
    }

    private void initView() {
        vpLoadingPage = (ViewPager) findViewById(R.id.vpLoadingPage);
        jump = findViewById(R.id.jump);
    }
    public void initListener(){
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jump();
            }
        });

        vpLoadingPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (i==listImages.size()-1){
                    jump.setVisibility(View.VISIBLE);
                }
                else {
                    jump.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }
    public void initData(){
        if (isFirst()){
            yesJump();
        }
        else {
            listImages.add(R.mipmap.chen1);
            listImages.add(R.mipmap.chen2);
            listImages.add(R.mipmap.chen3);
            vpLoadingPage.setAdapter(new LoadingPagerAdapter(listImages));
        }

    }
    public void jump(){
        SharedPreferences sharedPreferences = getSharedPreferences(Constant.SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putBoolean(Constant.IS_FIRST_LOGIN,true);
        edit.commit();
        Intent intent=new Intent(LoadingActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void yesJump(){
        Intent intent=new Intent(LoadingActivity.this,LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public boolean isFirst(){
        SharedPreferences sp=getSharedPreferences(Constant.SP_NAME,MODE_PRIVATE);
        boolean isFirst = sp.getBoolean(Constant.IS_FIRST_LOGIN, false);
        return isFirst;
    }
}
