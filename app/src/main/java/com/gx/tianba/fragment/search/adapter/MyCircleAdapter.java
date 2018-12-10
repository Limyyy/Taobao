package com.gx.tianba.fragment.search.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class MyCircleAdapter extends RecyclerView.Adapter<MyCircleAdapter.MyCircleViewHolder> {


    @NonNull
    @Override
    public MyCircleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCircleViewHolder myCircleViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyCircleViewHolder extends RecyclerView.ViewHolder {
        public MyCircleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
