package com.gx.tianba;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.gx.tianba.fragment.home.HomeFragment;
import com.gx.tianba.fragment.list.ListFragment;
import com.gx.tianba.fragment.my.MyFragment;
import com.gx.tianba.fragment.search.fragment.SearchFragment;
import com.gx.tianba.fragment.shopping.ShoppingFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView main_home;
    private ImageView main_search;
    private ImageView main_shopping;
    private ImageView main_list;
    private ImageView main_my;

    private SearchFragment searchFragment;
    private ListFragment listFragment;
    private ShoppingFragment shoppingFragment;
    private ViewPager viewpager;
    private MyFragment myFragment;
    private HomeFragment homeFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        List<Fragment> list = new ArrayList<Fragment>();
        myFragment = new MyFragment();
        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();
        listFragment = new ListFragment();
        shoppingFragment = new ShoppingFragment();

        list.add(homeFragment);
        list.add(searchFragment);
        list.add(shoppingFragment);
        list.add(listFragment);
        list.add(myFragment);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(), list);

        viewpager.setAdapter(viewPagerAdapter);

        //viewpager预加载
        viewpager.setOffscreenPageLimit(4);

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        main_home.setImageResource(R.mipmap.common_tab_btn_home_s_xxhdpi);
                        main_search.setImageResource(R.mipmap.common_tab_btn_circle_n_xxhdpi);
                        main_shopping.setImageResource(R.mipmap.commom_tab_btn_shopping_cart_n_xxhdpi);
                        main_list.setImageResource(R.mipmap.common_tab_btn_list_n_xxhdi);
                        main_my.setImageResource(R.mipmap.common_tab_btn_my_n_xxhdpi);
                        break;
                    case 1:
                        main_home.setImageResource(R.mipmap.common_tab_btn_home_n_xxhdpi);
                        main_search.setImageResource(R.mipmap.common_tab_btn_circle_s_xxhdpi);
                        main_shopping.setImageResource(R.mipmap.commom_tab_btn_shopping_cart_n_xxhdpi);
                        main_list.setImageResource(R.mipmap.common_tab_btn_list_n_xxhdi);
                        main_my.setImageResource(R.mipmap.common_tab_btn_my_n_xxhdpi);
                        break;
                    case 2:
                        main_home.setImageResource(R.mipmap.common_tab_btn_home_n_xxhdpi);
                        main_search.setImageResource(R.mipmap.common_tab_btn_circle_n_xxhdpi);
                        main_shopping.setImageResource(R.mipmap.commom_tab_btn_shopping_cart_n_xxhdpi);
                        main_list.setImageResource(R.mipmap.common_tab_btn_list_n_xxhdi);
                        main_my.setImageResource(R.mipmap.common_tab_btn_my_n_xxhdpi);
                        break;
                    case 3:
                        main_home.setImageResource(R.mipmap.common_tab_btn_home_n_xxhdpi);
                        main_search.setImageResource(R.mipmap.common_tab_btn_circle_n_xxhdpi);
                        main_shopping.setImageResource(R.mipmap.commom_tab_btn_shopping_cart_n_xxhdpi);
                        main_list.setImageResource(R.mipmap.common_tab_btn_list_s_xxhdpi);
                        main_my.setImageResource(R.mipmap.common_tab_btn_my_n_xxhdpi);
                        break;
                    case 4:
                        main_home.setImageResource(R.mipmap.common_tab_btn_home_n_xxhdpi);
                        main_search.setImageResource(R.mipmap.common_tab_btn_circle_n_xxhdpi);
                        main_shopping.setImageResource(R.mipmap.commom_tab_btn_shopping_cart_n_xxhdpi);
                        main_list.setImageResource(R.mipmap.common_tab_btn_list_n_xxhdi);
                        main_my.setImageResource(R.mipmap.common_tab_btn_my_s_xxhdpi);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initView() {
        main_home = (ImageView) findViewById(R.id.main_home);
        main_home.setOnClickListener(this);
        main_search = (ImageView) findViewById(R.id.main_search);
        main_search.setOnClickListener(this);
        main_shopping = (ImageView) findViewById(R.id.main_shopping);
        main_shopping.setOnClickListener(this);
        main_list = (ImageView) findViewById(R.id.main_list);
        main_list.setOnClickListener(this);
        main_my = (ImageView) findViewById(R.id.main_my);
        main_my.setOnClickListener(this);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.main_home:
                viewpager.setCurrentItem(0);
                break;
            case R.id.main_search:
                viewpager.setCurrentItem(1);
                break;
            case R.id.main_shopping:
                viewpager.setCurrentItem(2);
                break;
            case R.id.main_list:
                viewpager.setCurrentItem(3);
                break;
            case R.id.main_my:
                viewpager.setCurrentItem(4);
                break;
        }
    }
}
