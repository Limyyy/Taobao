package com.gx.tianba.fragment.shopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.gx.tianba.fragment.shopping.bean.Shopping;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder> {

    private List<Shopping.ResultBean> result;
    private Context context;

    public ShoppingAdapter(List<Shopping.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    public List<Shopping.ResultBean> getResult() {
        return result;
    }


    @NonNull
    @Override
    public ShoppingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingViewHolder shoppingViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ShoppingViewHolder extends RecyclerView.ViewHolder {

        public ShoppingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
