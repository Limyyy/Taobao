package com.gx.tianba;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.gx.tianba.fragment.options.OptionsFragment;
import com.gx.tianba.fragment.seller.SellerFragment;
import com.gx.tianba.fragment.shopcart.ShopCartFragment;
import com.gx.tianba.fragment.sort.SortFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private TabLayout tab;
    private ViewPager viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        List<Fragment> fragmentlists=new ArrayList<>();
        SortFragment sortFragment = new SortFragment();
        ShopCartFragment shopCartFragment = new ShopCartFragment();
        OptionsFragment optionsFragment = new OptionsFragment();
        SellerFragment sellerFragment = new SellerFragment();
        //加进集合
        fragmentlists.add(sellerFragment);
        fragmentlists.add(sortFragment);
        fragmentlists.add(shopCartFragment);
        fragmentlists.add(optionsFragment);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getFragmentManager(),fragmentlists);
        viewpager.setAdapter(viewPagerAdapter);
        //让tab和viewpager联动起来
        tab.setupWithViewPager(viewpager);
        for (int i = 0; i < fragmentlists.size(); i++) {
            tab.addTab(tab.newTab());
        }
        tab.getTabAt(0).setText("首页");
        tab.getTabAt(1).setText("分类");
        tab.getTabAt(2).setText("购物车");
        tab.getTabAt(3).setText("我的");
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
    }
}
