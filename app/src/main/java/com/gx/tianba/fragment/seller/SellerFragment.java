package com.gx.tianba.fragment.seller;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gx.tianba.R;
import com.gx.tianba.fragment.seller.adapter.MainSellerAdapter;
import com.gx.tianba.fragment.seller.bean.Seller;
import com.gx.tianba.fragment.seller.presenter.SellerPresenter;
import com.gx.tianba.fragment.seller.view.ISeller;
import com.gx.tianba.util.net.HttpUrl;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SellerFragment extends Fragment implements ISeller {
    private RecyclerView rlv_seller;
    private CheckBox seller_checkall;
    private TextView submit_shop_cart;
    private SellerPresenter sellerPresenter;
    private MainSellerAdapter mainSellerAdapter;
    private List<Seller.DataBean> list_all=new ArrayList<>();
    private Seller.DataBean dataBean=new Seller.DataBean();
    private  List<Seller.DataBean.ListBean> listBeans=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_seller, container, false);
        rlv_seller =view. findViewById(R.id.rlv_seller);
        seller_checkall =view. findViewById(R.id.seller_checkall);
        submit_shop_cart = view.findViewById(R.id.submit_shop_cart);
        sellerPresenter = new SellerPresenter(this);
        sellerPresenter.showAllGoods(HttpUrl.SHOPCART_URL);
        isSeleckAll();
        //选中的加入购物车
        submit_shop_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitShopCart();
            }
        });
        return view;
    }
    @Override
    public void backData(final String data) {
       getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson=new Gson();
                final Seller seller = gson.fromJson(data, Seller.class);
                if (seller.getCode().equals("0")){
                    List<Seller.DataBean> data1 = seller.getData();
                    data1.remove(0);
                    mainSellerAdapter = new MainSellerAdapter(getActivity(),data1);
                    rlv_seller.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rlv_seller.setAdapter(mainSellerAdapter);
                }
                else {
                    Toast.makeText(getActivity(),"请求失败",Toast.LENGTH_SHORT).show();
                }
                List<Seller.DataBean> data1 = mainSellerAdapter.getData();
                seleckAll(data1);
            }
        });
    }

    //设置全选
    public void seleckAll(final List<Seller.DataBean> data1){
        seller_checkall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < data1.size(); i++) {
                    Seller.DataBean dataBean = data1.get(i);
                    List<Seller.DataBean.ListBean> list = dataBean.getList();
                    for (int i1 = 0; i1 < list.size(); i1++) {
                        Seller.DataBean.ListBean listBean = list.get(i1);
                        if (seller_checkall.isChecked()){
                            listBean.setSelected(1);
                        }
                        else {
                            listBean.setSelected(0);
                        }

                    }
                }
                isSeleckAll();
                mainSellerAdapter.notifyDataSetChanged();
            }
        });
    }
    public void isSeleckAll(){
        if (seller_checkall.isChecked()){
            seller_checkall.setButtonDrawable(R.drawable.ic_check_circle_black_24dp_yes);
        }
        else {
            seller_checkall.setButtonDrawable(R.drawable.ic_check_circle_black_24dp);
        }
    }
    //加入购物车
    public void submitShopCart(){
        List<Seller.DataBean> data = mainSellerAdapter.getData();
        for (int i = 0; i < data.size(); i++) {
            Seller.DataBean dataBean = data.get(i);
            List<Seller.DataBean.ListBean> list1 = dataBean.getList();
            for (int i1 = 0; i1 < list1.size(); i1++) {
                Seller.DataBean.ListBean listBean = list1.get(i1);
                //选中的话加进去集合
                if (listBean.getSelected()==1){
                    listBeans.add(listBean);
                    dataBean.setList(listBeans);
                    list_all.add(dataBean);
                }
                else {

                }
            }
        }
        if (list_all.size()!=0){
            EventBus.getDefault().post(list_all);
            Toast.makeText(getActivity(),"加入购物车成功，请去购物车查看",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getActivity(),"请选择要添加到购物车的商品",Toast.LENGTH_SHORT).show();
        }


    }

}
