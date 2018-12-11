package com.gx.tianba.fragment.search.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gx.tianba.R;
import com.gx.tianba.util.fresco.FrescoUtil;

import java.util.ArrayList;

public class MyCircleImageAdapter extends RecyclerView.Adapter<MyCircleImageAdapter.MyCircleImageViewHolder> {

    private String[]  iamges;
    private Context context;
    public MyCircleImageAdapter(String[] iamges,Context context) {
        this.iamges = iamges;
        this.context=context;
    }

    @NonNull
    @Override
    public MyCircleImageViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_circle_item_image, null);
        return new MyCircleImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyCircleImageViewHolder myCircleImageViewHolder, int i) {
        String s = iamges[i];
        FrescoUtil.load(s,myCircleImageViewHolder.my_circle_item_image_one);
    }

    @Override
    public int getItemCount() {
        return iamges.length;
    }

    public class MyCircleImageViewHolder extends RecyclerView.ViewHolder {
        SimpleDraweeView my_circle_item_image_one;

        public MyCircleImageViewHolder(@NonNull View itemView) {
            super(itemView);
            my_circle_item_image_one=itemView.findViewById(R.id.my_circle_item_image_one);
        }
    }
}
