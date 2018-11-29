package com.gx.tianba.seller.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gx.tianba.R;
import com.gx.tianba.seller.bean.Seller;
import com.gx.tianba.util.fresco.FrescoUtil;

import java.util.List;

public class ChildSellerAdapter extends RecyclerView.Adapter<ChildSellerAdapter.ChildSellerViewHolder> {

    private Context context;
    private List<Seller.DataBean.ListBean> list;

    public ChildSellerAdapter(Context context, List<Seller.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ChildSellerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.child_adapter_item, null);
        return new ChildSellerViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildSellerViewHolder childSellerViewHolder, int i) {
        Seller.DataBean.ListBean listBean = list.get(i);
        //判断是否为空
        if (listBean.getTitle()!=null){
            //设置商品名称
            childSellerViewHolder.childtitle.setText(""+listBean.getTitle());
        }
        //价格
        if (listBean.getPrice()!=0){
            childSellerViewHolder.childprice.setText(""+listBean.getPrice());
        }
        //图片
        String images = listBean.getImages();
        if (images!=null){
            int i1 = images.indexOf("|");
            if (i1!=-1){
                String substring = images.substring(0, i1);
                FrescoUtil.load(substring,childSellerViewHolder.childimage);
            }
            else {
                FrescoUtil.load(images,childSellerViewHolder.childimage);
            }
        }
        //多选框
        if (listBean.getSelected()!=0){
            childSellerViewHolder.childcheck.setButtonDrawable(R.drawable.ic_check_circle_black_24dp_yes);
        }
        else {
            childSellerViewHolder.childcheck.setChecked(false);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ChildSellerViewHolder extends RecyclerView.ViewHolder{

        CheckBox childcheck;
        SimpleDraweeView childimage;
        TextView childtitle;
        TextView childprice;
        public ChildSellerViewHolder(@NonNull View itemView) {
            super(itemView);
            childcheck=itemView.findViewById(R.id.childcheckbox);
            childimage=itemView.findViewById(R.id.childimage);
            childtitle=itemView.findViewById(R.id.childtitle);
            childprice=itemView.findViewById(R.id.childprice);

        }
    }
}
