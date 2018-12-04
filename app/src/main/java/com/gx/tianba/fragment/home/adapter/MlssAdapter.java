package com.gx.tianba.fragment.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.home.bean.Home;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MlssAdapter extends RecyclerView.Adapter<MlssAdapter.MlssViewHolder> {
    private List<Home.ResultBean.MlssBean.CommodityListBeanXX> commodityList;
    private Context context;

    public MlssAdapter(List<Home.ResultBean.MlssBean.CommodityListBeanXX> commodityList, Context context) {
        this.commodityList = commodityList;
        this.context = context;
    }

    @NonNull
    @Override
    public MlssViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.mlss_item, null);
        return new MlssViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MlssViewHolder mlssViewHolder, int i) {
        Home.ResultBean.MlssBean.CommodityListBeanXX commodityListBeanXX = commodityList.get(i);
        Picasso.with(context).load(commodityListBeanXX.getMasterPic()).into(mlssViewHolder.mlssitemiamge);
        mlssViewHolder.mlssitemname.setText(""+commodityListBeanXX.getCommodityName());
        mlssViewHolder.mlssitempricel.setText("$:"+commodityListBeanXX.getPrice());
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    public class MlssViewHolder extends RecyclerView.ViewHolder{
        ImageView mlssitemiamge;
        TextView  mlssitemname,mlssitempricel;

        public MlssViewHolder(@NonNull View itemView) {
            super(itemView);
            mlssitemiamge=itemView.findViewById(R.id.mlss_item_iamge);
            mlssitemname=itemView.findViewById(R.id.mlss_item_name);
            mlssitempricel=itemView.findViewById(R.id.mlss_item_price);
        }
    }
}
