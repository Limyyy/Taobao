package com.gx.tianba.fragment.shopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.gx.tianba.R;

import com.gx.tianba.fragment.shopping.bean.Shopping;
import com.gx.tianba.fragment.shopping.myview.MyView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShoppingAdapter extends RecyclerView.Adapter<ShoppingAdapter.ShoppingViewHolder> {

    private List<Shopping.ResultBean> result;
    private Context context;
    private ItemCheckOnClickListner itemCheckOnClickListner;
    private ItemJiaOnClickListner itemJiaOnClickListner;
    private ItemJianOnClickListner itemJianOnClickListner;
    private ItemDeleteOnClickListner itemDeleteOnClickListner;
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
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_item, null);
        return new ShoppingViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ShoppingViewHolder shoppingViewHolder, final int i) {
        Shopping.ResultBean resultBean = result.get(i);
        //购买的数量
        final int count = resultBean.getCount();
        //商品ID
        final int commodityId = resultBean.getCommodityId();
        //商品名称
        String commodityName = resultBean.getCommodityName();
        //缩略图
        String pic = resultBean.getPic();
        //价格
        int price = resultBean.getPrice();
        //多选框
        boolean checked = resultBean.isChecked();
        if (checked){
            shoppingViewHolder.shopping_item_check.setChecked(true);
        }
        else {
            shoppingViewHolder.shopping_item_check.setChecked(false);
        }
        shoppingViewHolder.shopping_item_jiajian.setNum(count);
        shoppingViewHolder.shopping_item_name.setText(commodityName);
        Picasso.with(context).load(pic).into(shoppingViewHolder.shopping_item_image);
        shoppingViewHolder.shopping_item_price.setText(""+price);

        //条目多选框点击事件
        shoppingViewHolder.shopping_item_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //当前条目checkbox选中状态
                boolean checked1 = shoppingViewHolder.shopping_item_check.isChecked();
                itemCheckOnClickListner.click(commodityId,checked1);
            }
        });
        //条目减点击事件
        shoppingViewHolder.shopping_item_jiajian.jianOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemJianOnClickListner.click(commodityId);
            }
        });
        //条目加点击事件
        shoppingViewHolder.shopping_item_jiajian.jiaOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemJiaOnClickListner.click(commodityId);
            }
        });
        //条目删除事件
        shoppingViewHolder.shopping_item_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemDeleteOnClickListner.click(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ShoppingViewHolder extends RecyclerView.ViewHolder {
        CheckBox shopping_item_check;
        ImageView shopping_item_image;
        TextView shopping_item_name,shopping_item_price,shopping_item_delete;
        MyView shopping_item_jiajian;
        public ShoppingViewHolder(@NonNull View itemView) {
            super(itemView);
            shopping_item_check=itemView.findViewById(R.id.shopping_item_check);
            shopping_item_image=itemView.findViewById(R.id.shopping_item_image);
            shopping_item_name=itemView.findViewById(R.id.shopping_item_name);
            shopping_item_price=itemView.findViewById(R.id.shopping_item_price);
            shopping_item_delete=itemView.findViewById(R.id.shopping_item_delete);
            shopping_item_jiajian=itemView.findViewById(R.id.shopping_item_jiajian);
        }
    }

    public void setItemCheckOnClickListner(ItemCheckOnClickListner itemCheckOnClickListner1){
        this.itemCheckOnClickListner=itemCheckOnClickListner1;
    }
    public interface ItemCheckOnClickListner{
        void click(int commodityId,boolean isChecked);
    }

    public void setItemJiaOnClickListner(ItemJiaOnClickListner itemJiaOnClickListner1){
        this.itemJiaOnClickListner=itemJiaOnClickListner1;
    }
    public interface ItemJiaOnClickListner{
        void click(int commodityId);
    }

    public void setItemJianOnClickListner(ItemJianOnClickListner itemJianOnClickListner){
        this.itemJianOnClickListner=itemJianOnClickListner;
    }
    public interface ItemJianOnClickListner{
        void click(int commodityId);
    }

    public void setItemDeleteOnClickListner(ItemDeleteOnClickListner itemDeleteOnClickListner1){
        this.itemDeleteOnClickListner=itemDeleteOnClickListner1;
    }
    public interface ItemDeleteOnClickListner{
        void click(int commodityId);
    }

}
