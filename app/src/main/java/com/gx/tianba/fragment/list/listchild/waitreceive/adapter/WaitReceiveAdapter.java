package com.gx.tianba.fragment.list.listchild.waitreceive.adapter;

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

public class WaitReceiveAdapter extends RecyclerView.Adapter<WaitReceiveAdapter.WaitReceiveViewHolder> {

    private Context context;
    private List<ListBean.OrderListBean> orderList;

    public WaitReceiveAdapter(Context context, List<ListBean.OrderListBean> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public WaitReceiveViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.wait_receive_item, null);
        return new WaitReceiveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaitReceiveViewHolder waitReceiveViewHolder, int i) {
        ListBean.OrderListBean orderListBean = orderList.get(i);
        //订单编号
        String orderId = orderListBean.getOrderId();
        waitReceiveViewHolder.wait_receive_item_orderId.setText(orderId);
        //快递公司名称
        String expressCompName = orderListBean.getExpressCompName();
        waitReceiveViewHolder.wait_receive_item_CompName.setText(expressCompName);
        //快递单号
        String expressSn = orderListBean.getExpressSn();
        waitReceiveViewHolder.wait_receive_item_expressSn.setText(expressSn);
        //时间
        waitReceiveViewHolder.wait_receive_item_time.setText("2018-12-15");
        //具体信息
        List<ListBean.OrderListBean.DetailListBean> detailList = orderListBean.getDetailList();
        WaitReceiveChildAdapter waitReceiveChildAdapter = new WaitReceiveChildAdapter(detailList, context);
        waitReceiveViewHolder.wait_receive_item_ryl.setLayoutManager(new LinearLayoutManager(context));
        waitReceiveViewHolder.wait_receive_item_ryl.setAdapter(waitReceiveChildAdapter);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class WaitReceiveViewHolder extends RecyclerView.ViewHolder {
        TextView wait_receive_item_orderId,wait_receive_item_CompName,wait_receive_item_expressSn,wait_receive_item_isshou,wait_receive_item_time;
        RecyclerView wait_receive_item_ryl;
        public WaitReceiveViewHolder(@NonNull View itemView) {
            super(itemView);
            wait_receive_item_orderId=itemView.findViewById(R.id.wait_receive_item_orderId);
            wait_receive_item_CompName=itemView.findViewById(R.id.wait_receive_item_CompName);
            wait_receive_item_expressSn=itemView.findViewById(R.id.wait_receive_item_expressSn);
            wait_receive_item_isshou=itemView.findViewById(R.id.wait_receive_item_isshou);
            wait_receive_item_ryl=itemView.findViewById(R.id.wait_receive_item_ryl);
            wait_receive_item_time=itemView.findViewById(R.id.wait_receive_item_time);
        }
    }
}
