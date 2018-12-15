package com.gx.tianba.fragment.list.listchild.waitevaluate.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.fragment.list.listchild.waitreceive.adapter.WaitReceiveChildAdapter;

import java.util.List;

public class WaitEvalueteAdapter extends RecyclerView.Adapter<WaitEvalueteAdapter.WaitEvalueteViewHolder>{

    private Context context;
    private List<ListBean.OrderListBean> orderList;

    public WaitEvalueteAdapter(Context context, List<ListBean.OrderListBean> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public WaitEvalueteViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.wait_evaluete_item, null);
        return new WaitEvalueteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaitEvalueteViewHolder waitEvalueteViewHolder, int i) {
        ListBean.OrderListBean orderListBean = orderList.get(i);
        //订单编号
        String orderId = orderListBean.getOrderId();
        waitEvalueteViewHolder.wait_evaluate_item_orderId.setText(orderId);
        //订单具体信息
        List<ListBean.OrderListBean.DetailListBean> detailList = orderListBean.getDetailList();
        waitEvalueteViewHolder.wait_evaluate_item_ryl.setLayoutManager(new LinearLayoutManager(context));
        WaitEvalueteChildAdapter waitEvalueteChildAdapter = new WaitEvalueteChildAdapter(context, detailList);
        waitEvalueteViewHolder.wait_evaluate_item_ryl.setAdapter(waitEvalueteChildAdapter);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class WaitEvalueteViewHolder extends RecyclerView.ViewHolder {
        TextView wait_evaluate_item_orderId,wait_evaluate_item_time;
        ImageView  wait_evaluate_item_more;
        RecyclerView wait_evaluate_item_ryl;
        public WaitEvalueteViewHolder(@NonNull View itemView) {
            super(itemView);
            wait_evaluate_item_orderId=itemView.findViewById(R.id.wait_evaluate_item_orderId);
            wait_evaluate_item_more=itemView.findViewById(R.id.wait_evaluate_item_more);
            wait_evaluate_item_ryl=itemView.findViewById(R.id.wait_evaluate_item_ryl);
            wait_evaluate_item_time=itemView.findViewById(R.id.wait_evaluate_item_time);
        }
    }
}
