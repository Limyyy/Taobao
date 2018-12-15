package com.gx.tianba.fragment.list.listchild.waitevaluate.adapter;

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
import com.squareup.picasso.Picasso;

import java.util.List;

public class WaitEvalueteChildAdapter extends RecyclerView.Adapter<WaitEvalueteChildAdapter.WaitEvalueteChildViewHolder> {

    private Context context;
    private List<ListBean.OrderListBean.DetailListBean> detailList;

    public WaitEvalueteChildAdapter(Context context, List<ListBean.OrderListBean.DetailListBean> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @NonNull
    @Override
    public WaitEvalueteChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.wait_evaluate_child_item, null);
        return new WaitEvalueteChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaitEvalueteChildViewHolder waitEvalueteChildViewHolder, int i) {
        ListBean.OrderListBean.DetailListBean detailListBean = detailList.get(i);
        //图片
        String commodityPic = detailListBean.getCommodityPic();
        if (commodityPic!=null){
            int i1 = commodityPic.indexOf(",");
            if (i1!=-1){
                String substring = commodityPic.substring(0, i1);
                Picasso.with(context).load(substring).into(waitEvalueteChildViewHolder.wait_evaluate_child_item_image);
            }
            else{
                Picasso.with(context).load(commodityPic).into(waitEvalueteChildViewHolder.wait_evaluate_child_item_image);
            }
        }
        //名字
        waitEvalueteChildViewHolder.wait_evaluate_child_item_name.setText(detailListBean.getCommodityName());
        //价格
        waitEvalueteChildViewHolder.wait_evaluate_child_item_price.setText(""+detailListBean.getCommodityPrice());
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }

    public class WaitEvalueteChildViewHolder extends RecyclerView.ViewHolder {
        ImageView wait_evaluate_child_item_image;
        TextView wait_evaluate_child_item_name,wait_evaluate_child_item_price,wait_evaluate_child_item_qupingjia;
        public WaitEvalueteChildViewHolder(@NonNull View itemView) {
            super(itemView);
            wait_evaluate_child_item_image=itemView.findViewById(R.id.wait_evaluate_child_item_image);
            wait_evaluate_child_item_name=itemView.findViewById(R.id.wait_evaluate_child_item_name);
            wait_evaluate_child_item_price=itemView.findViewById(R.id.wait_evaluate_child_item_price);
            wait_evaluate_child_item_qupingjia=itemView.findViewById(R.id.wait_evaluate_child_item_qupingjia);
        }
    }
}
