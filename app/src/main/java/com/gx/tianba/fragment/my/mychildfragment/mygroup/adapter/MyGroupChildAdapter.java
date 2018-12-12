package com.gx.tianba.fragment.my.mychildfragment.mygroup.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gx.tianba.R;
import com.gx.tianba.util.fresco.FrescoUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyGroupChildAdapter extends RecyclerView.Adapter<MyGroupChildAdapter.MyGroupChildViewHolder> {

    private ArrayList<String> imagess;
    private Context context;

    public MyGroupChildAdapter(ArrayList<String> imagess, Context context) {
        this.imagess = imagess;
        this.context = context;
    }

    @NonNull
    @Override
    public MyGroupChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.my_group_child_item, null);
        return new MyGroupChildViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull MyGroupChildViewHolder myGroupChildViewHolder, int i) {
        String iamge=imagess.get(i);
        Uri uri=Uri.parse(iamge);
        Picasso.with(context).load(uri).into(myGroupChildViewHolder.my_group_child_item_image);
    }

    @Override
    public int getItemCount() {
        return imagess.size();
    }

    public class MyGroupChildViewHolder extends RecyclerView.ViewHolder {
        ImageView my_group_child_item_image;
        public MyGroupChildViewHolder(@NonNull View itemView) {
            super(itemView);
            my_group_child_item_image = itemView.findViewById(R.id.my_group_child_item_image);
        }
    }
}
