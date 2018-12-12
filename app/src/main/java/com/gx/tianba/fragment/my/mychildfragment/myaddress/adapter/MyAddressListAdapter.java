package com.gx.tianba.fragment.my.mychildfragment.myaddress.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;

import java.util.List;

public class MyAddressListAdapter extends RecyclerView.Adapter<MyAddressListAdapter.MyAddressListViewHolder> {

    private List<MyAddress.ResultBean> result;
    private Context context;
    private OnDefaultAddress onDefaultAddress;

    public MyAddressListAdapter(List<MyAddress.ResultBean> result, Context context) {
        this.result = result;
        this.context = context;
    }

    @NonNull
    @Override
    public MyAddressListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_address_list_item, null);
        return new MyAddressListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAddressListViewHolder myAddressListViewHolder, int i) {
        final MyAddress.ResultBean resultBean = result.get(i);
        //默认收获地址
        int whetherDefault = resultBean.getWhetherDefault();
        if (whetherDefault==1){
            myAddressListViewHolder.my_child_address_item_checkbox.setButtonDrawable(R.drawable.ic_check_circle_black_24dp);
        }
        else {
            myAddressListViewHolder.my_child_address_item_checkbox.setButtonDrawable(R.drawable.ic_radio_button_unchecked_black_24dp);
        }
        //姓名
        myAddressListViewHolder.my_child_address_item_name.setText(resultBean.getRealName());
        //电话
        myAddressListViewHolder.my_child_address_item_phone.setText(resultBean.getPhone());
        //地址
        myAddressListViewHolder.my_child_address_item_address.setText(resultBean.getAddress());

        //点击设置默认收货地址
        myAddressListViewHolder.my_child_address_item_checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDefaultAddress.defaultAddressClick(resultBean.getId());
            }
        });
        //点击修改收货地址
        myAddressListViewHolder.my_child_address_item_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDefaultAddress.updateAddressClick(resultBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return result.size();
    }

    public List<MyAddress.ResultBean> getResult(){
        return result;
    }
    public class MyAddressListViewHolder extends RecyclerView.ViewHolder {
        TextView my_child_address_item_name,my_child_address_item_phone,my_child_address_item_address,my_child_address_item_update,my_child_address_item_delete;
        CheckBox my_child_address_item_checkbox;
        public MyAddressListViewHolder(@NonNull View itemView) {
            super(itemView);
            my_child_address_item_name=itemView.findViewById(R.id.my_child_address_item_name);
            my_child_address_item_phone=itemView.findViewById(R.id.my_child_address_item_phone);
            my_child_address_item_address=itemView.findViewById(R.id.my_child_address_item_address);
            my_child_address_item_update=itemView.findViewById(R.id.my_child_address_item_update);
            my_child_address_item_delete=itemView.findViewById(R.id.my_child_address_item_delete);
            my_child_address_item_checkbox=itemView.findViewById(R.id.my_child_address_item_checkbox);
        }

    }

    public void setOnDefaultAddress(OnDefaultAddress onDefaultAddress1){
        this.onDefaultAddress=onDefaultAddress1;
    }

    public interface OnDefaultAddress{
        void defaultAddressClick(int id);
        void deleleAddressClick(int id);

        void updateAddressClick(MyAddress.ResultBean resultBean);
    }
}
