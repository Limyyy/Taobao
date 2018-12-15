package com.gx.tianba.fragment.list.listchild.waitreceive.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.squareup.picasso.Picasso;

import java.util.List;

public class WaitReceiveChildAdapter extends RecyclerView.Adapter<WaitReceiveChildAdapter.WaitReceiveChildViewHolder> {

    private List<ListBean.OrderListBean.DetailListBean> detailList;
    private Context context;

    public WaitReceiveChildAdapter(List<ListBean.OrderListBean.DetailListBean> detailList, Context context) {
        this.detailList = detailList;
        this.context = context;
    }

    @NonNull
    @Override
    public WaitReceiveChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.wait_receive_child_item, null);
        return new WaitReceiveChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaitReceiveChildViewHolder waitReceiveChildViewHolder, int i) {
        ListBean.OrderListBean.DetailListBean detailListBean = detailList.get(i);
        //图片
        String commodityPic = detailListBean.getCommodityPic();
        if (commodityPic!=null){
            int i1 = commodityPic.indexOf(",");
            if (i1!=-1){
                String substring = commodityPic.substring(0, i1);
                Picasso.with(context).load(substring).into(waitReceiveChildViewHolder.wait_receive_child_item_image);
            }
            else{
                Picasso.with(context).load(commodityPic).into(waitReceiveChildViewHolder.wait_receive_child_item_image);
            }
        }
        //名字
        waitReceiveChildViewHolder.wait_receive_child_item_name.setText(detailListBean.getCommodityName());
        //价格
        waitReceiveChildViewHolder.wait_receive_child_item_price.setText(""+detailListBean.getCommodityPrice());
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class WaitReceiveChildViewHolder extends RecyclerView.ViewHolder {
        TextView wait_receive_child_item_price,wait_receive_child_item_name;
        ImageView wait_receive_child_item_image;
        public WaitReceiveChildViewHolder(@NonNull View itemView) {
            super(itemView);
            wait_receive_child_item_price=itemView.findViewById(R.id.wait_receive_child_item_price);
            wait_receive_child_item_name=itemView.findViewById(R.id.wait_receive_child_item_name);
            wait_receive_child_item_image=itemView.findViewById(R.id.wait_receive_child_item_image);
        }
    }
}
