package com.gx.tianba.fragment.home.homeshowfragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx.tianba.R;

public class HomeShowMainAdapter extends RecyclerView.Adapter {

    private Context context;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType==0) {
            View view = LayoutInflater.from(context).inflate(R.layout.home_show_goods_item, null);
        }
        else if (itemViewType==1){

        }
        else {

        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    @Override
    public int getItemViewType(int position) {
        if (position==0) {
            return 0;
        }
        else if (position==1){
            return 1;
        }
        else {
            return 2;
        }
    }

    //商品
    public class HomeShowMainViewHolderOne extends RecyclerView.ViewHolder {

        public HomeShowMainViewHolderOne(@NonNull View itemView) {
            super(itemView);
        }
    }
    //详情
    public class HomeShowMainViewHolderTwo extends RecyclerView.ViewHolder {

        public HomeShowMainViewHolderTwo(@NonNull View itemView) {
            super(itemView);
        }
    }
    //评论
    public class HomeShowMainViewHolderThree extends RecyclerView.ViewHolder {

        public HomeShowMainViewHolderThree(@NonNull View itemView) {
            super(itemView);
        }
    }
}
