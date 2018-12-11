package com.gx.tianba.fragment.my.mychildfragment.myaddress.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;

import java.util.List;

public class MyAddressListAdapter extends RecyclerView.Adapter<MyAddressListAdapter.MyAddressListViewHolder> {

    private List<MyAddress.ResultBean> result;
    private Context context;

    public MyAddressListAdapter(List<MyAddress.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAddressListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAddressListViewHolder myAddressListViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class MyAddressListViewHolder extends RecyclerView.ViewHolder {

        public MyAddressListViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
