package com.gx.tianba.fragment.shopcart;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.seller.bean.Seller;
import com.gx.tianba.fragment.shopcart.adapter.MainShopCartAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShopCartFragment extends Fragment {

    private RecyclerView rlv_shopcart;
    private CheckBox tv_shopcart_addselect;
    private TextView tv_shopcart_allprice;
    private TextView tv_shopcart_submit;
    private View emtryview;
    private MainShopCartAdapter mainShopCartAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //注册一个eventBus
        View view = inflater.inflate(R.layout.fragment_shop_cart, container, false);
        EventBus.getDefault().register(this);
        initView(view);

        return view;
    }

    //使用EventBus和遍历集合中选中的商品得到加入购物车的商品
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void event(List<Seller.DataBean> list) {
        //设置适配器
        mainShopCartAdapter = new MainShopCartAdapter(getActivity(), list);
        rlv_shopcart.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlv_shopcart.setAdapter(mainShopCartAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    private void initView(View view) {
        rlv_shopcart = (RecyclerView) view.findViewById(R.id.rlv_shopcart);
        tv_shopcart_addselect = (CheckBox) view.findViewById(R.id.tv_shopcart_addselect);
        tv_shopcart_allprice = (TextView) view.findViewById(R.id.tv_shopcart_allprice);
        tv_shopcart_submit = (TextView) view.findViewById(R.id.tv_shopcart_submit);
        emtryview = (View) view.findViewById(R.id.emtryview);
    }
}
