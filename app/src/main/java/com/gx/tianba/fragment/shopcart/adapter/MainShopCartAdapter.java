package com.gx.tianba.fragment.shopcart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.seller.adapter.ChildSellerAdapter;
import com.gx.tianba.fragment.seller.bean.Seller;

import java.util.List;

public class MainShopCartAdapter extends RecyclerView.Adapter<MainShopCartAdapter.MainShopCartViewHolder> {

    private Context context;
    private List<Seller.DataBean> data;
    private ChildSellerAdapter childSellerAdapter;

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
        childSellerAdapter = new ChildSellerAdapter(context, list);
        mainSellerViewHolder.childrcy.setLayoutManager(new LinearLayoutManager(context));
        mainSellerViewHolder.childrcy.setAdapter(childSellerAdapter);
        childSellerAdapter.SetChildCheckOnClickListner(new ChildSellerAdapter.CallBack() {
            @Override
            public void getGroupAndChild(int Group, int child,int isCheck) {
                //Toast.makeText(context,"组为："+Group+"子为："+child+"是否选中"+isCheck,Toast.LENGTH_SHORT).show();
                List<Seller.DataBean> data1 = getData();
                data1.get(Group).getList().get(child).setSelected(isCheck);
                notifyDataSetChanged();
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
}
