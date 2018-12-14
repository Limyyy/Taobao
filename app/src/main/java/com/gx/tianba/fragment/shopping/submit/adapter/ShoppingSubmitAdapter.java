package com.gx.tianba.fragment.shopping.submit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.shopping.bean.Shopping;
import com.gx.tianba.fragment.shopping.myview.MyView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShoppingSubmitAdapter extends RecyclerView.Adapter<ShoppingSubmitAdapter.ShoppingSubmitViewHolder> {

    private List<Shopping.ResultBean> result;
    private Context context;

    public ShoppingSubmitAdapter(List<Shopping.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public ShoppingSubmitViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.shop_submit_item, null);
        return new ShoppingSubmitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingSubmitViewHolder shoppingSubmitViewHolder, int i) {
        Shopping.ResultBean resultBean = result.get(i);
        //购买的数量
         int count = resultBean.getCount();
        //商品ID
         int commodityId = resultBean.getCommodityId();
        //商品名称
        String commodityName = resultBean.getCommodityName();
        //缩略图
        String pic = resultBean.getPic();
        //价格
        int price = resultBean.getPrice();

        shoppingSubmitViewHolder.shopping_submit_item_jiajian.setNum(count);
        shoppingSubmitViewHolder.shopping_submit_item_name.setText(commodityName);
        Picasso.with(context).load(pic).into(shoppingSubmitViewHolder.shopping_submit_item_image);
        shoppingSubmitViewHolder.shopping_submit_item_price.setText(""+price);
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ShoppingSubmitViewHolder extends RecyclerView.ViewHolder {
        ImageView shopping_submit_item_image;
        TextView shopping_submit_item_name,shopping_submit_item_price;
        MyView shopping_submit_item_jiajian;
        public ShoppingSubmitViewHolder(@NonNull View itemView) {
            super(itemView);
            shopping_submit_item_image=itemView.findViewById(R.id.shopping_submit_item_image);
            shopping_submit_item_name=itemView.findViewById(R.id.shopping_submit_item_name);
            shopping_submit_item_price=itemView.findViewById(R.id.shopping_submit_item_price);
            shopping_submit_item_jiajian=itemView.findViewById(R.id.shopping_submit_item_jiajian);
        }
    }
}
