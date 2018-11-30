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
    public void event(final List<Seller.DataBean> list) {
        //先给选定的取消
        for (int i = 0; i < list.size(); i++) {
            Seller.DataBean dataBean = list.get(i);
            List<Seller.DataBean.ListBean> list1 = dataBean.getList();
            for (int i1 = 0; i1 < list1.size(); i1++) {
                Seller.DataBean.ListBean listBean = list1.get(i1);
                listBean.setSelected(0);
            }
        }
        //设置适配器
        mainShopCartAdapter = new MainShopCartAdapter(getActivity(), list);
        rlv_shopcart.setLayoutManager(new LinearLayoutManager(getActivity()));
        rlv_shopcart.setAdapter(mainShopCartAdapter);
        //全选
        tv_shopcart_addselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tv_shopcart_addselect.isChecked()){
                    selectAll(list,1);
                }
                else {
                    selectAll(list,0);
                }
                mainShopCartAdapter.notifyDataSetChanged();
                //当刷新的时候也可以调用方法来计算总价
                double v1 = mainShopCartAdapter.sumAllPrice();
                tv_shopcart_allprice.setText(""+v1);
            }
        });
        mainShopCartAdapter.GetAllPrice(new MainShopCartAdapter.setDoublePrice() {
            @Override
            public void setPrice(double allprice) {
                tv_shopcart_allprice.setText(""+allprice);
            }

            //监听是否数据单个的全部都选上了
            //如果有一个没有选中就自动取消全选
            @Override
            public void isSelAll(boolean b) {
                if (b){
                    //为了取消卡顿 其实不是卡顿 是按照chekbox的属性来的
                    tv_shopcart_addselect.setChecked(true);
                    tv_shopcart_addselect.setButtonDrawable(R.drawable.ic_check_circle_black_24dp_yes);
                }
                else {
                    tv_shopcart_addselect.setChecked(false);
                    tv_shopcart_addselect.setButtonDrawable(R.drawable.ic_check_circle_black_24dp);
                }
            }
        });

    }
    //全选
    public void selectAll(List<Seller.DataBean> list,int sel){
        for (int i = 0; i < list.size(); i++) {
            Seller.DataBean dataBean = list.get(i);
            List<Seller.DataBean.ListBean> list1 = dataBean.getList();
            for (int i1 = 0; i1 < list1.size(); i1++) {
                Seller.DataBean.ListBean listBean = list1.get(i1);
                listBean.setSelected(sel);
            }
        }
        if (sel==1){
            tv_shopcart_addselect.setChecked(true);
            tv_shopcart_addselect.setButtonDrawable(R.drawable.ic_check_circle_black_24dp_yes);
        }
        else {
            tv_shopcart_addselect.setChecked(false);
            tv_shopcart_addselect.setButtonDrawable(R.drawable.ic_check_circle_black_24dp);
        }
    }
    //撤销注册 EventBus
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
    //初始化
    private void initView(View view) {
        rlv_shopcart = (RecyclerView) view.findViewById(R.id.rlv_shopcart);
        tv_shopcart_addselect = (CheckBox) view.findViewById(R.id.tv_shopcart_addselect);
        tv_shopcart_allprice = (TextView) view.findViewById(R.id.tv_shopcart_allprice);
        tv_shopcart_submit = (TextView) view.findViewById(R.id.tv_shopcart_submit);
        emtryview = (View) view.findViewById(R.id.emtryview);
        tv_shopcart_addselect.setButtonDrawable(R.drawable.ic_check_circle_black_24dp);
    }
}
