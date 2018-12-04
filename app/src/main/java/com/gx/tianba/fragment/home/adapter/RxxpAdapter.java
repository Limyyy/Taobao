package com.gx.tianba.fragment.home.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.gx.tianba.R;
import com.gx.tianba.fragment.home.bean.Home;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RxxpAdapter extends RecyclerView.Adapter<RxxpAdapter.RxxpViewHolder> {
   private List<Home.ResultBean.RxxpBean.CommodityListBean> commodityList;
   private Context context;

    public RxxpAdapter(List<Home.ResultBean.RxxpBean.CommodityListBean> commodityList, Context context) {
        this.commodityList = commodityList;
        this.context = context;
    }

    @NonNull
    @Override
    public RxxpViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.rxxp_item, null);
        return new RxxpViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RxxpViewHolder rxxpViewHolder, int i) {
        Home.ResultBean.RxxpBean.CommodityListBean commodityListBean = commodityList.get(i);
        //设置数据
        Picasso.with(context).load(commodityListBean.getMasterPic()).into(rxxpViewHolder.rxxpitemiamge);
        rxxpViewHolder.rxxpitemname.setText(commodityListBean.getCommodityName());
        rxxpViewHolder.rxxpitemprice.setText("$:"+commodityListBean.getPrice());
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    public class RxxpViewHolder extends  RecyclerView.ViewHolder{
        ImageView rxxpitemiamge;
        TextView rxxpitemname;
        TextView rxxpitemprice;
        public RxxpViewHolder(@NonNull View itemView) {
            super(itemView);
            rxxpitemiamge=itemView.findViewById(R.id.rxxp_item_iamge);
            rxxpitemname=itemView.findViewById(R.id.rxxp_item_name);
            rxxpitemprice=itemView.findViewById(R.id.rxxp_item_price);
        }
    }
}
