package com.gx.tianba.fragment.shopping.submit.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;

import java.util.List;

public class ShoppingSubmitAddressAdapter extends RecyclerView.Adapter<ShoppingSubmitAddressAdapter.ShoppingSubmitAddressViewHolder> {
    private List<MyAddress.ResultBean> result;
    private Context context;
    private ItemSelectMyAddressClickListner itemSelectMyAddressClickListner;
    public ShoppingSubmitAddressAdapter(List<MyAddress.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public ShoppingSubmitAddressViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shopping_submit_address_item, null);
        return new ShoppingSubmitAddressViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoppingSubmitAddressViewHolder shoppingSubmitAddressViewHolder, int i) {
        final MyAddress.ResultBean resultBean = result.get(i);
        //名字
        String realName = resultBean.getRealName();
        //电话
        String phone = resultBean.getPhone();
        //地址
        String address = resultBean.getAddress();
        shoppingSubmitAddressViewHolder.shopping_submit_address_item_address.setText(address);
        shoppingSubmitAddressViewHolder.shopping_submit_address_item_name.setText(realName);
        shoppingSubmitAddressViewHolder.shopping_submit_address_item_phone.setText(phone);

        //点击选择
        shoppingSubmitAddressViewHolder.shopping_submit_address_item_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemSelectMyAddressClickListner.click(resultBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public class ShoppingSubmitAddressViewHolder extends RecyclerView.ViewHolder {
        TextView shopping_submit_address_item_name,shopping_submit_address_item_phone,shopping_submit_address_item_address,shopping_submit_address_item_select;
        public ShoppingSubmitAddressViewHolder(@NonNull View itemView) {
            super(itemView);
            shopping_submit_address_item_name=itemView.findViewById(R.id.shopping_submit_address_item_name);
            shopping_submit_address_item_phone=itemView.findViewById(R.id.shopping_submit_address_item_phone);
            shopping_submit_address_item_address=itemView.findViewById(R.id.shopping_submit_address_item_address);
            shopping_submit_address_item_select=itemView.findViewById(R.id.shopping_submit_address_item_select);
        }
    }

    public void setItemSelectMyAddressClickListner(ItemSelectMyAddressClickListner itemSelectMyAddressClickListner1){
        this.itemSelectMyAddressClickListner=itemSelectMyAddressClickListner1;
    }
    public interface ItemSelectMyAddressClickListner{
        void click(MyAddress.ResultBean resultBean);
    }
}
