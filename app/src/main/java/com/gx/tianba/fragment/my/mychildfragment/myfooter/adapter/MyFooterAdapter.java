package com.gx.tianba.fragment.my.mychildfragment.myfooter.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.myfooter.bean.MyFooter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyFooterAdapter extends RecyclerView.Adapter<MyFooterAdapter.MyFooterViewHolder> {

    private List<MyFooter.ResultBean> result;
    private Context context;

    public MyFooterAdapter(List<MyFooter.ResultBean> result, Context context) {
        if (result!=null){
            this.result = result;
        }
        this.context = context;
    }


    @NonNull
    @Override
    public MyFooterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(context, R.layout.my_footer_item, null);
        return new MyFooterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyFooterViewHolder myFooterViewHolder, int i) {
        MyFooter.ResultBean resultBean = result.get(i);
        Picasso.with(context).load(resultBean.getMasterPic()).into(myFooterViewHolder.myfooteritemimage);
        myFooterViewHolder.myfooteritemname.setText(resultBean.getCommodityName());
        myFooterViewHolder.myfooteritemnum.setText(""+resultBean.getBrowseNum());
        myFooterViewHolder.myfooteritemprice.setText("￥:"+resultBean.getPrice());
        myFooterViewHolder.myfooteritemtime.setText(""+resultBean.getBrowseTime());
    }


    @Override
    public int getItemCount() {
        if (result!=null){
            return result.size();
        }
        else {
            return 0;
        }
    }


    public class MyFooterViewHolder extends RecyclerView.ViewHolder {
        ImageView myfooteritemimage;
        TextView myfooteritemname,myfooteritemprice,myfooteritemnum,myfooteritemtime;
        public MyFooterViewHolder(@NonNull View itemView) {
            super(itemView);
            myfooteritemimage=itemView.findViewById(R.id.my_footer_item_image);
            myfooteritemname=itemView.findViewById(R.id.my_footer_item_name);
            myfooteritemprice=itemView.findViewById(R.id.my_footer_item_price);
            myfooteritemnum=itemView.findViewById(R.id.my_footer_item_num);
            myfooteritemtime=itemView.findViewById(R.id.my_footer_item_time);
        }
    }
}
