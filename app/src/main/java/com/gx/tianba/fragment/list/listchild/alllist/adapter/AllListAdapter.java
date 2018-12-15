package com.gx.tianba.fragment.list.listchild.alllist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.list.listchild.bean.ListBean;

import java.util.List;

public class AllListAdapter extends RecyclerView.Adapter<AllListAdapter.AllListViewHolder> {

    private List<ListBean.OrderListBean> orderList;
    private Context context;

    public AllListAdapter(List<ListBean.OrderListBean> orderList, Context context) {
        this.orderList = orderList;
        this.context = context;
    }

    @NonNull
    @Override
    public AllListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.all_list_item, null);
        return new AllListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllListViewHolder allListViewHolder, int i) {

        ListBean.OrderListBean orderListBean = orderList.get(i);
        //订单编号
        String orderId = orderListBean.getOrderId();
        allListViewHolder.all_list_item_orderId.setText(orderId);
        //订单时间
        allListViewHolder.all_list_item_time.setText("2018-12-15");
        //订单详情
        List<ListBean.OrderListBean.DetailListBean> detailList = orderListBean.getDetailList();
        AllListChildAdapter allListChildAdapter = new AllListChildAdapter(detailList, context);
        allListViewHolder.all_list_item_ryl.setLayoutManager(new LinearLayoutManager(context));
        allListViewHolder.all_list_item_ryl.setAdapter(allListChildAdapter);
        //订单数量
        int size = detailList.size();
        allListViewHolder.all_list_item_goods_num.setText(""+size);
        //订单总价
        int payAmount = orderListBean.getPayAmount();
        allListViewHolder.all_list_item_goods_sumprice.setText(""+payAmount);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class AllListViewHolder extends RecyclerView.ViewHolder {
        TextView all_list_item_orderId,all_list_item_time,all_list_item_goods_num,all_list_item_goods_sumprice;
        TextView all_list_item_cancle,all_list_item_gopay;
        RecyclerView all_list_item_ryl;
        public AllListViewHolder(@NonNull View itemView) {
            super(itemView);
            all_list_item_orderId=itemView.findViewById(R.id.all_list_item_orderId);
            all_list_item_time=itemView.findViewById(R.id.all_list_item_time);
            all_list_item_goods_num=itemView.findViewById(R.id.all_list_item_goods_num);
            all_list_item_goods_sumprice=itemView.findViewById(R.id.all_list_item_goods_sumprice);
            all_list_item_cancle=itemView.findViewById(R.id.all_list_item_cancle);
            all_list_item_gopay=itemView.findViewById(R.id.all_list_item_gopay);
            all_list_item_ryl=itemView.findViewById(R.id.all_list_item_ryl);
        }
    }
}
