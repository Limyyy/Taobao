package com.gx.tianba;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gx.tianba.fragment.options.OptionsFragment;
import com.gx.tianba.fragment.seller.SellerFragment;
import com.gx.tianba.fragment.shopcart.ShopCartFragment;
import com.gx.tianba.fragment.sort.SortFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TabLayout tab;
    private ViewPager viewpager;
    private ShopCartFragment shopCartFragment;
    private OptionsFragment optionsFragment;
    private SellerFragment sellerFragment;
    private SortFragment sortFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        fragmentManager = getFragmentManager();
        sortFragment = new SortFragment();
        shopCartFragment = new ShopCartFragment();
        optionsFragment = new OptionsFragment();
        sellerFragment = new SellerFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, sellerFragment);
        fragmentTransaction.add(R.id.frame, sortFragment);
        fragmentTransaction.add(R.id.frame, shopCartFragment);
        fragmentTransaction.add(R.id.frame, optionsFragment);
        fragmentTransaction.hide(sortFragment);
        fragmentTransaction.hide(shopCartFragment);
        fragmentTransaction.hide(optionsFragment);
        fragmentTransaction.show(sellerFragment);
        fragmentTransaction.commit();
        for (int i = 0; i < 4; i++) {
            tab.addTab(tab.newTab());
        }
        tab.getTabAt(0).setText("首页");
        tab.getTabAt(1).setText("分类");
        tab.getTabAt(2).setText("购物车");
        tab.getTabAt(3).setText("我的");
        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.show(sellerFragment);
                        fragmentTransaction.hide(sortFragment);
                        fragmentTransaction.hide(shopCartFragment);
                        fragmentTransaction.hide(optionsFragment);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                        fragmentTransaction1.hide(sellerFragment);
                        fragmentTransaction1.show(sortFragment);
                        fragmentTransaction1.hide(shopCartFragment);
                        fragmentTransaction1.hide(optionsFragment);
                        fragmentTransaction1.commit();
                        break;
                    case 2:
                        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                        fragmentTransaction2.hide(sellerFragment);
                        fragmentTransaction2.hide(sortFragment);
                        fragmentTransaction2.show(shopCartFragment);
                        fragmentTransaction2.hide(optionsFragment);
                        fragmentTransaction2.commit();
                        break;
                    case 3:
                        FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                        fragmentTransaction3.hide(sellerFragment);
                        fragmentTransaction3.hide(sortFragment);
                        fragmentTransaction3.hide(shopCartFragment);
                        fragmentTransaction3.show(optionsFragment);
                        fragmentTransaction3.commit();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
    }
}
