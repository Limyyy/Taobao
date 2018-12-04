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

public class PzshAdapter extends RecyclerView.Adapter<PzshAdapter.PzshViewHolder> {
    private List<Home.ResultBean.PzshBean.CommodityListBeanX> commodityList;
    private Context context;

    public PzshAdapter(List<Home.ResultBean.PzshBean.CommodityListBeanX> commodityList, Context context) {
        this.commodityList = commodityList;
        this.context = context;
    }

    @NonNull
    @Override
    public PzshViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.pzsh_item, null);
        return new PzshViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PzshViewHolder pzshViewHolder, int i) {
        Home.ResultBean.PzshBean.CommodityListBeanX commodityListBeanX = commodityList.get(i);
        Picasso.with(context).load(commodityListBeanX.getMasterPic()).into(pzshViewHolder.pzshitemiamge);
        pzshViewHolder.pzshitemname.setText(""+commodityListBeanX.getCommodityName());
        pzshViewHolder.pzshitemprice.setText("$:"+commodityListBeanX.getPrice());
    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    public class PzshViewHolder  extends RecyclerView.ViewHolder{
        ImageView pzshitemiamge;
        TextView pzshitemname,pzshitemprice;
        public PzshViewHolder(@NonNull View itemView) {
            super(itemView);
            pzshitemiamge=itemView.findViewById(R.id.pzsh_item_iamge);
            pzshitemname=itemView.findViewById(R.id.pzsh_item_name);
            pzshitemprice=itemView.findViewById(R.id.pzsh_item_price);
        }
    }
}
