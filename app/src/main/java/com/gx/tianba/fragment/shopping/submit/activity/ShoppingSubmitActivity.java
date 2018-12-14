package com.gx.tianba.fragment.shopping.submit.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;
import com.gx.tianba.fragment.shopping.bean.Shopping;
import com.gx.tianba.fragment.shopping.submit.adapter.ShoppingSubmitAdapter;
import com.gx.tianba.fragment.shopping.submit.adapter.ShoppingSubmitAddressAdapter;
import com.gx.tianba.fragment.shopping.submit.bean.ShoppingSubmit;
import com.gx.tianba.fragment.shopping.submit.bean.SubmitBean;
import com.gx.tianba.fragment.shopping.submit.presenter.ShoppingAddressPre;
import com.gx.tianba.fragment.shopping.submit.view.IShoppingAddress;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ShoppingSubmitActivity extends AppCompatActivity implements IShoppingAddress {

    private TextView shopping_submit_address;
    private RecyclerView shopping_submit_ryl;
    private RecyclerView shopping_submit_show_address;
    private TextView shopping_submit_sumsize;
    private TextView shopping_submit_sumprice;
    private TextView shopping_submit_submit;
    private ShoppingAddressPre shoppingAddressPre;
    private ShoppingSubmitAddressAdapter shoppingSubmitAddressAdapter;
    private TextView shopping_submit_show_lin_name;
    private TextView shopping_submit_show_lin_phone;
    private TextView shopping_submit_show_lin_address;
    private LinearLayout shopping_submit_show_linearlayout;
    private boolean ready = false;
    private boolean flag=true;
    private ImageView shopping_submit_show_down_up;
    private int addressId=0;
    private ArrayList<SubmitBean> list=new ArrayList<>();
    private Gson gson=new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_submit);
        //请求地址列表
        shoppingAddressPre = new ShoppingAddressPre(this);
        final Login.ResultBean spData = SpUtil.getSpData();
        shoppingAddressPre.setPreShoppingAddressList("" + spData.getUserId(), spData.getSessionId());
        //find view
        initView();
        //得到传过来的值
        Intent intent = getIntent();
        int sumsize = intent.getIntExtra("sumsize", -1);
        final List<Shopping.ResultBean> result = (List<Shopping.ResultBean>) intent.getSerializableExtra("result");
        final String sumprice = intent.getStringExtra("sumprice");
        //设置值
        shopping_submit_sumsize.setText("" + sumsize);
        shopping_submit_sumprice.setText(sumprice);

        //设置商品的adapter
        shopping_submit_ryl.setLayoutManager(new LinearLayoutManager(this));
        ShoppingSubmitAdapter shoppingSubmitAdapter = new ShoppingSubmitAdapter(result, this);
        shopping_submit_ryl.setAdapter(shoppingSubmitAdapter);
        //提交订单
        shopping_submit_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //通过gson变成json结构
                list.clear();
                for (int i = 0; i < result.size(); i++) {
                    SubmitBean submitBean = new SubmitBean();
                    int commodityId = result.get(i).getCommodityId();
                    int count = result.get(i).getCount();
                    submitBean.setAmount(count);
                    submitBean.setCommodityId(commodityId);
                    list.add(submitBean);
                }
                String s = gson.toJson(list);
                //支付总金额转换为double
                double v1 = Double.parseDouble(sumprice);
                shoppingAddressPre.setPreShoppingSubmit(spData.getUserId(),spData.getSessionId(),v1,addressId,s);
            }
        });
        //点击添加我的收货地址
        shopping_submit_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ready) {
                    shopping_submit_address.setFocusable(false);
                } else {
                    shopping_submit_show_address.setVisibility(View.VISIBLE);
                }
            }
        });
        //点击下拉剪头选择我的收货地址
        shopping_submit_show_down_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    shopping_submit_show_address.setVisibility(View.VISIBLE);
                    flag=false;
                }
                else {
                    shopping_submit_show_address.setVisibility(View.GONE);
                    flag=true;
                }
            }
        });
    }

    private void initView() {
        shopping_submit_address = (TextView) findViewById(R.id.shopping_submit_address);
        shopping_submit_ryl = (RecyclerView) findViewById(R.id.shopping_submit_ryl);
        shopping_submit_show_address = (RecyclerView) findViewById(R.id.shopping_submit_show_address);
        shopping_submit_sumsize = (TextView) findViewById(R.id.shopping_submit_sumsize);
        shopping_submit_sumprice = (TextView) findViewById(R.id.shopping_submit_sumprice);
        shopping_submit_submit = (TextView) findViewById(R.id.shopping_submit_submit);
        shopping_submit_show_lin_name = (TextView) findViewById(R.id.shopping_submit_show_lin_name);
        shopping_submit_show_lin_phone = (TextView) findViewById(R.id.shopping_submit_show_lin_phone);
        shopping_submit_show_lin_address = (TextView) findViewById(R.id.shopping_submit_show_lin_address);
        shopping_submit_show_linearlayout = (LinearLayout) findViewById(R.id.shopping_submit_show_linearlayout);
        shopping_submit_show_down_up = (ImageView) findViewById(R.id.shopping_submit_show_down_up);
    }

    //我的地址查询成功
    @Override
    public void onShoppingAddressSuccess(MyAddress myAddress) {
        List<MyAddress.ResultBean> result = myAddress.getResult();
        shopping_submit_show_address.setLayoutManager(new LinearLayoutManager(this));
        shoppingSubmitAddressAdapter = new ShoppingSubmitAddressAdapter(result, this);
        shopping_submit_show_address.setAdapter(shoppingSubmitAddressAdapter);

        shoppingSubmitAddressAdapter.setItemSelectMyAddressClickListner(new ShoppingSubmitAddressAdapter.ItemSelectMyAddressClickListner() {
            @Override
            public void click(MyAddress.ResultBean resultBean) {
                shopping_submit_show_lin_name.setText(resultBean.getRealName());
                shopping_submit_show_lin_phone.setText(resultBean.getPhone());
                shopping_submit_show_lin_address.setText(resultBean.getAddress());
                addressId=resultBean.getId();
                //添加之后显示我的具体收货地址
                shopping_submit_show_linearlayout.setVisibility(View.VISIBLE);
                shopping_submit_show_address.setVisibility(View.GONE);
                ready = true;
            }
        });
    }

    //创建订单
    @Override
    public void onShoppingSubmit(ShoppingSubmit shoppingSubmit) {
        //创建订单成功
        if (shoppingSubmit.getStatus().equals("0000")){
            ToastUtil.Toast(shoppingSubmit.getMessage());
            Intent intent1 = new Intent();
            intent1.putExtra("commit", true);
            setResult(1, intent1);
            finish();
        }
        else{
            ToastUtil.Toast(shoppingSubmit.getMessage());
        }

    }

}
