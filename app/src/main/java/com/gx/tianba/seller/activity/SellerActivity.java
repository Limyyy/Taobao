package com.gx.tianba.seller.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gx.tianba.R;
import com.gx.tianba.seller.adapter.MainSellerAdapter;
import com.gx.tianba.seller.bean.Seller;
import com.gx.tianba.seller.presenter.SellerPresenter;
import com.gx.tianba.seller.view.ISeller;
import com.gx.tianba.util.net.HttpUrl;

import java.util.List;

public class SellerActivity extends AppCompatActivity implements ISeller {

    private RecyclerView rlv_seller;
    private CheckBox seller_checkall;
    private TextView submit_shop_cart;
    private SellerPresenter sellerPresenter;
    private MainSellerAdapter mainSellerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller);
        initView();
        sellerPresenter = new SellerPresenter(this);
        sellerPresenter.showAllGoods(HttpUrl.SHOPCART_URL);
    }

    private void initView() {
        rlv_seller = (RecyclerView) findViewById(R.id.rlv_seller);
        seller_checkall = (CheckBox) findViewById(R.id.seller_checkall);
        submit_shop_cart = (TextView) findViewById(R.id.submit_shop_cart);
    }

    @Override
    public void backData(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Gson gson=new Gson();
                final Seller seller = gson.fromJson(data, Seller.class);
                if (seller.getCode().equals("0")){
                    List<Seller.DataBean> data1 = seller.getData();
                     data1.remove(0);
                    mainSellerAdapter = new MainSellerAdapter(SellerActivity.this,data1);
                    rlv_seller.setLayoutManager(new LinearLayoutManager(SellerActivity.this));
                    rlv_seller.setAdapter(mainSellerAdapter);
                }
                else {
                    Toast.makeText(SellerActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
