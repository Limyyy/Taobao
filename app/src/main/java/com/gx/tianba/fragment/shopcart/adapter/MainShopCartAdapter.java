package com.gx.tianba.fragment.shopcart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.seller.bean.Seller;

import java.util.List;

public class MainShopCartAdapter extends RecyclerView.Adapter<MainShopCartAdapter.MainShopCartViewHolder> {

    private Context context;
    private List<Seller.DataBean> data;
    private ChildShopCartAdapter childSellerAdapter;
    private setDoublePrice setDoublePrice;

    //得到购物车里面的数据
    public List<Seller.DataBean> getData() {
        return data;
    }

    public MainShopCartAdapter(Context context, List<Seller.DataBean> data) {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public MainShopCartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.shop_cart_adapter_item,null);
        MainShopCartViewHolder mainSellerViewHolder = new MainShopCartViewHolder(view);
        return mainSellerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainShopCartViewHolder mainSellerViewHolder, int i) {
        //商家
        Seller.DataBean dataBean = data.get(i);
        String sellerName = dataBean.getSellerName();
        List<Seller.DataBean.ListBean> list = dataBean.getList();
        mainSellerViewHolder.shopcartname.setText(""+sellerName);
        //商品列表
        childSellerAdapter = new ChildShopCartAdapter(context, list);
        mainSellerViewHolder.childrcy.setLayoutManager(new LinearLayoutManager(context));
        mainSellerViewHolder.childrcy.setAdapter(childSellerAdapter);

        //单个点击监听
        childSellerAdapter.setOnChildShopCartAdapter(new ChildShopCartAdapter.Click() {
            @Override
            public void Listner() {
                double v = sumAllPrice();
                //接口回调给展示页面的总价格
                setDoublePrice.setPrice(v);
                Boolean selectAll = isSelectAll();
                setDoublePrice.isSelAll(selectAll);
            }
        });
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MainShopCartViewHolder extends RecyclerView.ViewHolder{

         TextView shopcartname;
         RecyclerView childrcy;

        public MainShopCartViewHolder(@NonNull View itemView) {
            super(itemView);
            shopcartname= itemView.findViewById(R.id.shop_cart_name);
            childrcy=itemView.findViewById(R.id.shop_cart_recyclerview);
        }
    }
    //计算购物车的总价
    public double sumAllPrice(){
        double sumprice=0;
        List<Seller.DataBean> data = getData();
        for (int i = 0; i < data.size(); i++) {
            Seller.DataBean dataBean = data.get(i);
            List<Seller.DataBean.ListBean> list = dataBean.getList();
            for (int i1 = 0; i1 < list.size(); i1++) {
                Seller.DataBean.ListBean listBean = list.get(i1);
                if (listBean.getSelected()==1){
                    double price = listBean.getPrice();
                    int num = listBean.getNum();
                    sumprice=sumprice+(price*num);
                }
            }
        }
        return sumprice;
    }
    public void GetAllPrice(setDoublePrice setDoublePrice1){
        this.setDoublePrice=setDoublePrice1;
    }

    public interface setDoublePrice{
        void setPrice(double allprice);
        void isSelAll(boolean b);
    }
    //判断单个点击过后是不是还是全选  使用接口回调
    //集合中的数据  和  选中的个数
    public Boolean  isSelectAll(){
        int j=0,jj=0;
        for (int i = 0; i < data.size(); i++) {
            Seller.DataBean dataBean = data.get(i);
            List<Seller.DataBean.ListBean> list = dataBean.getList();
            for (int i1 = 0; i1 < list.size(); i1++) {
                j++;
                Seller.DataBean.ListBean listBean = list.get(i1);
                if (listBean.getSelected()==1){
                    jj++;
                }
            }
        }
        if (j==jj){
            return true;
        }
        else {
            return false;
        }
    }
}
