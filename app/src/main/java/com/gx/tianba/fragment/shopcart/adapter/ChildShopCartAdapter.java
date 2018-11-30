package com.gx.tianba.fragment.shopcart.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gx.tianba.R;
import com.gx.tianba.fragment.seller.bean.Seller;
import com.gx.tianba.fragment.shopcart.view.MyView;
import com.gx.tianba.util.fresco.FrescoUtil;

import java.util.List;

public class ChildShopCartAdapter extends RecyclerView.Adapter<ChildShopCartAdapter.ChildShopCartViewHolder> {
    private Context context;
    private List<Seller.DataBean.ListBean> list;
    private Click click;
    public ChildShopCartAdapter(Context context, List<Seller.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ChildShopCartViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shop_cart_child_adapter_item, null);
        return new ChildShopCartViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull final ChildShopCartViewHolder childSellerViewHolder, int i) {
        final Seller.DataBean.ListBean listBean = list.get(i);
        //判断是否为空
        if (listBean.getTitle()!=null){
            //设置商品名称
            childSellerViewHolder.childtitle.setText(""+listBean.getTitle());
        }
        //数量
        childSellerViewHolder.myView.setNum(listBean.getNum());
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
            childSellerViewHolder.childcheck.setButtonDrawable(R.drawable.ic_check_circle_black_24dp);
        }
        //单个点击
        //变换价格要用接口回调
        childSellerViewHolder.childcheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (childSellerViewHolder.childcheck.isChecked()){
                    listBean.setSelected(0);
                }
                else {
                    listBean.setSelected(1);
                }
                notifyDataSetChanged();
                click.Listner();
            }
        });
        //点击数量增加通过接口回调变换总价
        childSellerViewHolder.myView.setAddOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得当当前的数量
                int num = childSellerViewHolder.myView.getNum();
                num++;
                //再设置数量
                listBean.setNum(num);
                //刷新适配器的数据
                notifyDataSetChanged();
                //使用监听返回去
                click.Listner();

            }
        });
        //点击数量减少通过接口回调变换总价
        childSellerViewHolder.myView.setJianOnclickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得当当前的数量
                int num = childSellerViewHolder.myView.getNum();
                //如果数量已经为1的话那么就不能在减少则退出并吐司
                if (num==1){
                    Toast.makeText(context,"不能再减了！！！",Toast.LENGTH_SHORT).show();
                    listBean.setNum(num);
                    //刷新适配器的数据
                    notifyDataSetChanged();
                    //使用监听返回去
                    click.Listner();
                }
                else {
                    num--;
                    listBean.setNum(num);
                    //刷新适配器的数据
                    notifyDataSetChanged();
                    //使用监听返回去
                    click.Listner();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ChildShopCartViewHolder extends RecyclerView.ViewHolder{

        CheckBox childcheck;
        SimpleDraweeView childimage;
        TextView childtitle;
        TextView childprice;
        MyView myView;
        public ChildShopCartViewHolder(@NonNull View itemView) {
            super(itemView);
            childcheck=itemView.findViewById(R.id.shop_cart_child_checkbox);
            childimage=itemView.findViewById(R.id.shop_cart_child_image);
            childtitle=itemView.findViewById(R.id.shop_cart_child_title);
            childprice=itemView.findViewById(R.id.shop_cart_child_price);
            myView=itemView.findViewById(R.id.myview);
        }
    }
    public void setOnChildShopCartAdapter(Click click1){
        this.click=click1;
    }

    public  interface  Click{
        void Listner();
    }
}
