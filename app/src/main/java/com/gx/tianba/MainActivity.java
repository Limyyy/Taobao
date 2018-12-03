package com.gx.tianba;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.gx.tianba.fragment.find.FindFragment;
import com.gx.tianba.fragment.options.OptionsFragment;
import com.gx.tianba.fragment.seller.SellerFragment;
import com.gx.tianba.fragment.shopcart.ShopCartFragment;
import com.gx.tianba.fragment.sort.SortFragment;

public class MainActivity extends AppCompatActivity {


    private TabLayout tablayout;
    private ShopCartFragment shopCartFragment;
    private OptionsFragment optionsFragment;
    private SellerFragment sellerFragment;
    private SortFragment sortFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
   /* private String titles[] = {"首页", "分类", "购物车", "我的","发现"};*/
    private int[] imageViews={R.mipmap.home_sel,R.mipmap.sort,R.mipmap.shop_cart,R.mipmap.option,R.mipmap.find};
    private int[] imageViewone={R.mipmap.home,R.mipmap.sort,R.mipmap.shop_cart,R.mipmap.option,R.mipmap.find};
    private FindFragment findFragment;

    //private int[] imageViews={R.mipmap.home_sel,R.mipmap.sort,R.mipmap.shop_cart,R.mipmap.option,R.mipmap.find};
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
        findFragment = new FindFragment();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frame, sellerFragment);
        fragmentTransaction.add(R.id.frame, sortFragment);
        fragmentTransaction.add(R.id.frame, shopCartFragment);
        fragmentTransaction.add(R.id.frame, optionsFragment);
        fragmentTransaction.add(R.id.frame,findFragment);
        fragmentTransaction.hide(sortFragment);
        fragmentTransaction.hide(shopCartFragment);
        fragmentTransaction.hide(optionsFragment);
        fragmentTransaction.show(sellerFragment);
        fragmentTransaction.hide(findFragment);
        fragmentTransaction.commit();
        for (int i = 0; i < tablayout.getTabCount(); i++) {
            TabLayout.Tab tab = tablayout.getTabAt(i);
            if (tab != null) {
                tab.setCustomView(getTabView(i,imageViews));
            }
        }

        tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        isseletab(0);
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.show(sellerFragment);
                        fragmentTransaction.hide(sortFragment);
                        fragmentTransaction.hide(shopCartFragment);
                        fragmentTransaction.hide(optionsFragment);
                        fragmentTransaction.hide(findFragment);
                        fragmentTransaction.commit();
                        break;
                    case 1:
                        isseletab(1);
                        FragmentTransaction fragmentTransaction1 = fragmentManager.beginTransaction();
                        fragmentTransaction1.hide(sellerFragment);
                        fragmentTransaction1.show(sortFragment);
                        fragmentTransaction1.hide(shopCartFragment);
                        fragmentTransaction1.hide(optionsFragment);
                        fragmentTransaction1.hide(findFragment);
                        fragmentTransaction1.commit();
                        break;
                    case 2:
                        isseletab(2);
                        FragmentTransaction fragmentTransaction2 = fragmentManager.beginTransaction();
                        fragmentTransaction2.hide(sellerFragment);
                        fragmentTransaction2.hide(sortFragment);
                        fragmentTransaction2.show(shopCartFragment);
                        fragmentTransaction2.hide(optionsFragment);
                        fragmentTransaction2.hide(findFragment);
                        fragmentTransaction2.commit();
                        break;
                    case 3:
                        isseletab(3);
                        FragmentTransaction fragmentTransaction3 = fragmentManager.beginTransaction();
                        fragmentTransaction3.hide(sellerFragment);
                        fragmentTransaction3.hide(sortFragment);
                        fragmentTransaction3.hide(shopCartFragment);
                        fragmentTransaction3.show(optionsFragment);
                        fragmentTransaction3.hide(findFragment);
                        fragmentTransaction3.commit();
                        break;
                    case 4:
                        isseletab(4);
                        FragmentTransaction fragmentTransaction4 = fragmentManager.beginTransaction();
                        fragmentTransaction4.hide(sellerFragment);
                        fragmentTransaction4.hide(sortFragment);
                        fragmentTransaction4.hide(shopCartFragment);
                        fragmentTransaction4.hide(optionsFragment);
                        fragmentTransaction4.show(findFragment);
                        fragmentTransaction4.commit();
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

    public View getTabView(int position,int[] imag){
        //首先为子tab布置一个布局
        View v = LayoutInflater.from(MainActivity.this).inflate(R.layout.tab,null);
        ImageView iv = (ImageView) v.findViewById(R.id.tab_Dial_Img);
        iv.setImageResource(imag[position]);
        return v;
    }
    //传过来的索引
    public void isseletab(int position){
        for (int i = 0; i < 5; i++) {
            //得到具体的每一个tab
            ImageView image1 = tablayout.getTabAt(i).getCustomView().findViewById(R.id.tab_Dial_Img);
            //判断i是否和position相等
            //相等的话说明点的就是这个 就给它设置图片
            if (i==position&&position==0){
                image1.setImageResource(R.mipmap.home_sel);
                continue;
            }
            else if (i==position&&position==1){
                image1.setImageResource(R.mipmap.sort_sel);
                continue;
            }
            else if (i==position&&position==2){
                image1.setImageResource(R.mipmap.shop_cart_sel);
                continue;
            }
            else if (i==position&&position==3){
                image1.setImageResource(R.mipmap.option_sel);
                continue;
            }
            else if (i==position&&position==4){
                image1.setImageResource(R.mipmap.refresh);
                continue;
            }
            else {
                image1.setImageResource(imageViewone[i]);
            }
        }

    }
    private void initView() {
        tablayout = (TabLayout) findViewById(R.id.tab);
    }
}
