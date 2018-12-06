package com.gx.tianba.fragment.home.homechildfragment.homechildadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * 首页页面跳转之后的适配器
 */
public class HomeChildAdapter extends RecyclerView.Adapter<HomeChildAdapter.HomeChildViewHolder> {

    @NonNull
    @Override
    public HomeChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeChildViewHolder homeChildViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    public class HomeChildViewHolder extends RecyclerView.ViewHolder{

        public HomeChildViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
