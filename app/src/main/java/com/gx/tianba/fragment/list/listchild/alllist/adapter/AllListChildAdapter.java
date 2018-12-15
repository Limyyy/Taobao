package com.gx.tianba.fragment.list.listchild.alllist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.fragment.shopping.myview.MyView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllListChildAdapter extends RecyclerView.Adapter<AllListChildAdapter.AllListChildViewHolder>{

    private List<ListBean.OrderListBean.DetailListBean> detailList;
    private Context context;

    public AllListChildAdapter(List<ListBean.OrderListBean.DetailListBean> detailList, Context context) {
        this.detailList = detailList;
        this.context = context;
    }


    @NonNull
    @Override
    public AllListChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_list_child_item, null);
        return new AllListChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllListChildViewHolder allListChildViewHolder, int i) {
        ListBean.OrderListBean.DetailListBean detailListBean = detailList.get(i);
        //图片
        String commodityPic = detailListBean.getCommodityPic();
        if (commodityPic!=null){
            int i1 = commodityPic.indexOf(",");
            if (i1!=-1){
                String substring = commodityPic.substring(0, i1);
                Picasso.with(context).load(substring).into(allListChildViewHolder.all_list_child_item_image);
            }
            else{
                Picasso.with(context).load(commodityPic).into(allListChildViewHolder.all_list_child_item_image);
            }
        }
        //名字
        allListChildViewHolder.all_list_child_item_name.setText(detailListBean.getCommodityName());
        //价格
        allListChildViewHolder.all_list_child_item_price.setText(""+detailListBean.getCommodityPrice());
        //数量
        allListChildViewHolder.all_list_child_item_jiajian.setNum(detailListBean.getCommodityCount());

    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class AllListChildViewHolder extends RecyclerView.ViewHolder {
        TextView all_list_child_item_name,all_list_child_item_price;
        MyView all_list_child_item_jiajian;
        ImageView all_list_child_item_image;
        public AllListChildViewHolder(@NonNull View itemView) {
            super(itemView);
            all_list_child_item_name=itemView.findViewById(R.id.all_list_child_item_name);
            all_list_child_item_price=itemView.findViewById(R.id.all_list_child_item_price);
            all_list_child_item_jiajian=itemView.findViewById(R.id.all_list_child_item_jiajian);
            all_list_child_item_image=itemView.findViewById(R.id.all_list_child_item_image);
        }
    }
}
