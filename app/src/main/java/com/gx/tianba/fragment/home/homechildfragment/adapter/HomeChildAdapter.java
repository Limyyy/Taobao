package com.gx.tianba.fragment.home.homechildfragment.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gx.tianba.R;
import com.gx.tianba.fragment.home.homechildfragment.bean.HomeChildBean;
import com.gx.tianba.util.fresco.FrescoUtil;

import java.util.List;

/**
 * 根据商品列表归属标签查询商品信息的适配器
 *
 */
public class HomeChildAdapter extends RecyclerView.Adapter<HomeChildAdapter.HomeChildViewHolder> {

    private Context  context;
    private  List<HomeChildBean.ResultBean> result;
    private OnItemClickListner onItemClickListner;
    public HomeChildAdapter(Context context, List<HomeChildBean.ResultBean> result) {
        this.context = context;
        this.result = result;
    }

    @NonNull
    @Override
    public HomeChildViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.home_child_item, null);
        return new HomeChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final HomeChildViewHolder homeChildViewHolder, int i) {
        final HomeChildBean.ResultBean resultBean = result.get(i);
        FrescoUtil.load(resultBean.getMasterPic(),homeChildViewHolder.homechilditemimage);
        homeChildViewHolder.homechilditemtext.setText(resultBean.getCommodityName());
        homeChildViewHolder.homechilditemprice.setText("￥:"+resultBean.getPrice());
        homeChildViewHolder.homechilditemsalenum.setText(""+resultBean.getSaleNum());


        //点击图片接口回调显示详情
        homeChildViewHolder.homechilditemimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int commodityId = resultBean.getCommodityId();
                onItemClickListner.click(commodityId);
            }
        });

    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class HomeChildViewHolder extends RecyclerView.ViewHolder {

        SimpleDraweeView  homechilditemimage;
        TextView homechilditemtext,homechilditemprice,homechilditemsalenum;

        public HomeChildViewHolder(@NonNull View itemView) {
            super(itemView);
             homechilditemimage = itemView.findViewById(R.id.home_child_item_image);
            homechilditemtext=itemView.findViewById(R.id.home_child_item_text);
            homechilditemprice=itemView.findViewById(R.id.home_child_item_price);
            homechilditemsalenum=itemView.findViewById(R.id.home_child_item_salenum);
        }
    }

    public void setOnItemClickListner(OnItemClickListner onItemClickListner1){
        this.onItemClickListner=onItemClickListner1;
    }
    public interface OnItemClickListner{
        void click(int id);
    }
}
