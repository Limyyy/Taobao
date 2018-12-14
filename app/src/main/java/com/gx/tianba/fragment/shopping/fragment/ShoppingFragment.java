package com.gx.tianba.fragment.shopping.fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.shopping.submit.activity.ShoppingSubmitActivity;
import com.gx.tianba.fragment.shopping.adapter.ShoppingAdapter;
import com.gx.tianba.fragment.shopping.bean.Shopping;
import com.gx.tianba.fragment.shopping.presenter.ShoppingPre;
import com.gx.tianba.fragment.shopping.view.IShoppingView;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment implements IShoppingView {


    private RecyclerView shopping_ryl;
    private CheckBox shopping_check;
    private TextView shopping_price_sum;
    private TextView shopping_go;
    private ShoppingPre shoppingPre;
    private  List<Shopping.ResultBean> resultSum;
    private ShoppingAdapter shoppingAdapter;
    private int checkSum=0;
    private int size=0;
    private List<Shopping.ResultBean> checkedResult=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);
        //find view
        initView(view);

        shoppingPre = new ShoppingPre(this);
        Login.ResultBean spData = SpUtil.getSpData();
        shoppingPre.setPreShoppingList(spData.getUserId(),spData.getSessionId());
        shopping_price_sum.setText("￥:0");

        //设置全选
        shopping_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Shopping.ResultBean> result = shoppingAdapter.getResult();
                for (int i = 0; i < result.size(); i++) {
                    if (shopping_check.isChecked()){
                        result.get(i).setChecked(true);
                    }
                    else {
                        result.get(i).setChecked(false);
                    }
                }
                shoppingAdapter.notifyDataSetChanged();
                //全选更新价格
                updataPriceSum();
            }
        });
        //跳转提交订单的activity
        shopping_go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //得到选中的数据
                checkedResult.clear();
                List<Shopping.ResultBean> result = shoppingAdapter.getResult();
                for (int i = 0; i < result.size(); i++) {
                    Shopping.ResultBean resultBean = result.get(i);
                    //选中的话添加进去
                    if (resultBean.isChecked()){
                        checkedResult.add(resultBean);
                    }
                }
                //如果有商品选中则跳转
                if (checkedResult.size()>0){
                    Intent intent=new Intent(getActivity(),ShoppingSubmitActivity.class);
                    intent.putExtra("result", (Serializable) checkedResult);
                    intent.putExtra("sumsize",checkedResult.size());
                    intent.putExtra("sumprice",shopping_price_sum.getText().toString().trim());
                    startActivityForResult(intent,0);
                }
                //如果没有商品选中则吐司
                else {
                    ToastUtil.Toast("请至少选择一件商品");
                }
            }
        });

        return view;
    }

    private void initView(View view) {
        shopping_ryl = (RecyclerView) view.findViewById(R.id.shopping_ryl);
        shopping_check = (CheckBox) view.findViewById(R.id.shopping_check);
        shopping_price_sum = (TextView) view.findViewById(R.id.shopping_price_sum);
        shopping_go = (TextView) view.findViewById(R.id.shopping_go);
    }


    //判断是否已经提交了
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0){
            if (resultCode==1){
                //提交删除
                boolean commit = data.getBooleanExtra("commit", false);
                if (commit){
                    List<Shopping.ResultBean> result = shoppingAdapter.getResult();
                    result.removeAll(checkedResult);
                    shopping_check.setChecked(false);
                    shopping_price_sum.setText(""+0);
                    shoppingAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shoppingPre.onDestroy();
    }

    //请求购物车列表
    @Override
    public void onSuccess(Shopping shopping) {
        if (shopping.getStatus().equals("0000")){
            resultSum=new ArrayList<>();
            List<Shopping.ResultBean> result = shopping.getResult();
            for (int i = 0; i < result.size(); i++) {
                result.get(i).setChecked(false);
            }
            resultSum.addAll(result);
            shopping_ryl.setLayoutManager(new LinearLayoutManager(getActivity()));
            shoppingAdapter = new ShoppingAdapter(resultSum,getActivity());
            shopping_ryl.setAdapter(shoppingAdapter);

            //条目多选框接口回调
            shoppingAdapter.setItemCheckOnClickListner(new ShoppingAdapter.ItemCheckOnClickListner() {
                @Override
                public void click(int commodityId,boolean is) {
                    if (is){
                        List<Shopping.ResultBean> result1 = shoppingAdapter.getResult();
                        for (int i = 0; i < result1.size(); i++) {
                            int commodityId1 = result1.get(i).getCommodityId();
                            if (commodityId==commodityId1){
                                result1.get(i).setChecked(is);
                            }
                        }
                        shoppingAdapter.notifyDataSetChanged();
                        //判断是否已经被动全选和更新价格
                        isAllChecked();
                    }
                    else {
                        List<Shopping.ResultBean> result1 = shoppingAdapter.getResult();
                        for (int i = 0; i < result1.size(); i++) {
                            int commodityId1 = result1.get(i).getCommodityId();
                            if (commodityId==commodityId1){
                                result1.get(i).setChecked(is);
                            }
                        }
                        shoppingAdapter.notifyDataSetChanged();
                        //判断是否已经被动全选和更新价格
                        isAllChecked();
                    }
                }
            });
            //数量加接口回调
            shoppingAdapter.setItemJiaOnClickListner(new ShoppingAdapter.ItemJiaOnClickListner() {
                @Override
                public void click(int commodityId) {
                    List<Shopping.ResultBean> result1 = shoppingAdapter.getResult();
                    for (int i = 0; i < result1.size(); i++) {
                        int commodityId1 = result1.get(i).getCommodityId();
                        if (commodityId==commodityId1){
                            result1.get(i).setCount(result1.get(i).getCount()+1);
                        }
                    }
                    shoppingAdapter.notifyDataSetChanged();
                    //计算价格
                    updataPriceSum();
                }
            });
            
            //数量减接口回调
            shoppingAdapter.setItemJianOnClickListner(new ShoppingAdapter.ItemJianOnClickListner() {
                @Override
                public void click(int commodityId) {
                    List<Shopping.ResultBean> result1 = shoppingAdapter.getResult();
                    for (int i = 0; i < result1.size(); i++) {
                        int commodityId1 = result1.get(i).getCommodityId();
                        if (commodityId==commodityId1){
                            if (result1.get(i).getCount()==1){
                                ToastUtil.Toast("不能再减了");
                                result1.get(i).setCount(1);
                            }
                            else {
                                result1.get(i).setCount(result1.get(i).getCount()-1);
                            }
                        }
                    }
                    shoppingAdapter.notifyDataSetChanged();
                    //计算价格
                    updataPriceSum();
                }
            });
            //删除购物车j
            shoppingAdapter.setItemDeleteOnClickListner(new ShoppingAdapter.ItemDeleteOnClickListner() {
                @Override
                public void click(int commodityId) {
                    List<Shopping.ResultBean> result1 = shoppingAdapter.getResult();
                    result1.remove(commodityId);
                    shoppingAdapter.notifyDataSetChanged();
                    ToastUtil.Toast("删除成功");
                }
            });
        }
        else{
            ToastUtil.Toast(shopping.getMessage());
        }
    }
    //判断商品是否全部选中从而选中多选框
    public void isAllChecked(){
        checkSum=0;
        size=0;
        List<Shopping.ResultBean> result = shoppingAdapter.getResult();
        for (int i = 0; i < result.size(); i++) {
            size++;
            boolean checked = result.get(i).isChecked();
            if (checked){
                checkSum++;
            }
        }
        //如果集合中条目的数量和选中的数量相等的话 全选框设置
        if (checkSum==size){
            shopping_check.setChecked(true);
        }
        else {
            shopping_check.setChecked(false);
        }
        //并且更新总价
        updataPriceSum();
    }
    //更新总价
    public void updataPriceSum(){
        int priceSum = 0;
        List<Shopping.ResultBean> result = shoppingAdapter.getResult();
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).isChecked()){
                int count = result.get(i).getCount();
                int price = result.get(i).getPrice();
                priceSum+=count*price;
            }
        }
        shopping_price_sum.setText(""+priceSum);
    }

}
