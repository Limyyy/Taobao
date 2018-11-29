package com.gx.tianba.seller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.seller.bean.Seller;

import java.util.List;

public class MainSellerAdapter extends RecyclerView.Adapter<MainSellerAdapter.MainSellerViewHolder> {

    private Context context;
    private List<Seller.DataBean> data;

    public MainSellerAdapter(Context context, List<Seller.DataBean> data) {
        this.context = context;
        this.data = data;
    }
    @NonNull
    @Override
    public MainSellerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.seller_adapter_item,null);
        MainSellerViewHolder mainSellerViewHolder = new MainSellerViewHolder(view);
        return mainSellerViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainSellerViewHolder mainSellerViewHolder, int i) {
        //商家
        Seller.DataBean dataBean = data.get(i);
        String sellerName = dataBean.getSellerName();
        List<Seller.DataBean.ListBean> list = dataBean.getList();
        mainSellerViewHolder.sellername.setText(""+sellerName);
        //商品列表
        ChildSellerAdapter childSellerAdapter = new ChildSellerAdapter(context, list);
        mainSellerViewHolder.childrcy.setLayoutManager(new LinearLayoutManager(context));
        mainSellerViewHolder.childrcy.setAdapter(childSellerAdapter);
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MainSellerViewHolder extends RecyclerView.ViewHolder{

         TextView sellername;
         RecyclerView childrcy;

        public MainSellerViewHolder(@NonNull View itemView) {
            super(itemView);
            sellername= itemView.findViewById(R.id.seller_name);
            childrcy=itemView.findViewById(R.id.seller_recyclerview);
        }
    }
}
