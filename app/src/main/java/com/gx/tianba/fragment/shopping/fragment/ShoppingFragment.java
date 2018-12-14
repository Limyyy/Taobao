package com.gx.tianba.fragment.shopping.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.shopping.adapter.ShoppingAdapter;
import com.gx.tianba.fragment.shopping.bean.Shopping;
import com.gx.tianba.fragment.shopping.presenter.ShoppingPre;
import com.gx.tianba.fragment.shopping.view.IShoppingView;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

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
        return view;
    }

    private void initView(View view) {
        shopping_ryl = (RecyclerView) view.findViewById(R.id.shopping_ryl);
        shopping_check = (CheckBox) view.findViewById(R.id.shopping_check);
        shopping_price_sum = (TextView) view.findViewById(R.id.shopping_price_sum);
        shopping_go = (TextView) view.findViewById(R.id.shopping_go);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        shoppingPre.onDestroy();
    }

    @Override
    public void onSuccess(Shopping shopping) {
        if (shopping.getStatus().equals("0000")){
            resultSum=new ArrayList<>();
            List<Shopping.ResultBean> result = shopping.getResult();
            resultSum.addAll(result);
            shopping_ryl.setLayoutManager(new LinearLayoutManager(getActivity()));
            shoppingAdapter = new ShoppingAdapter(resultSum,getActivity());
            shopping_ryl.setAdapter(shoppingAdapter);
        }
        else{
            ToastUtil.Toast(shopping.getMessage());
        }
    }

}
