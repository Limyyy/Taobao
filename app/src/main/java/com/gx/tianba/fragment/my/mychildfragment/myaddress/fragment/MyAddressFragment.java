package com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.myaddaddress.fragment.MyAddAddressFragment;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.adapter.MyAddressListAdapter;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.bean.MyAddress;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.fragment.update.fragment.MyAddressUpdateFragment;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.presenter.MyAddressPresenter;
import com.gx.tianba.fragment.my.mychildfragment.myaddress.view.IMyAddress;
import com.gx.tianba.fragment.my.mymainfragment.MyMainFragment;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyAddressFragment extends Fragment implements IMyAddress {

    private TextView my_child_address_complet;
    private RecyclerView my_child_address_ryl;
    private MyAddressPresenter myAddressPresenter;
    private TextView my_child_address_addaddress;
    private int defaultId=0;
    private List<MyAddress.ResultBean> data=new ArrayList<>();
    private MyAddressListAdapter myAddressListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_address, container, false);
        initView(view);

        myAddressPresenter = new MyAddressPresenter(this);
        Login.ResultBean spData = SpUtil.getSpData();
        myAddressPresenter.setPreAddressListUrl("" + spData.getUserId(), spData.getSessionId());

        //新增收获地址
        my_child_address_addaddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.hide(MyAddressFragment.this);
                fragmentTransaction.add(R.id.my_framelayout, new MyAddAddressFragment(),"add");
                fragmentTransaction.addToBackStack("add");
                fragmentTransaction.commit();
            }
        });
        //点击完成修改默认收货地址
        my_child_address_complet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (defaultId!=0){
                    myAddressPresenter.setPreAddressDefault(defaultId);
                }
                else {
                    FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.my_framelayout, new MyMainFragment());
                    fragmentTransaction.commit();
                }
            }
        });
        return view;
    }

    private void initView(View view) {
        my_child_address_complet = (TextView) view.findViewById(R.id.my_child_address_complet);
        my_child_address_ryl = (RecyclerView) view.findViewById(R.id.my_child_address_ryl);
        my_child_address_addaddress = (TextView) view.findViewById(R.id.my_child_address_addaddress);
    }

    @Override
    public void onSuccess(MyAddress myAddress) {
        my_child_address_ryl.setLayoutManager(new LinearLayoutManager(getActivity(), 1, false));
        List<MyAddress.ResultBean> result = myAddress.getResult();
        data.addAll(result);
        myAddressListAdapter = new MyAddressListAdapter(result, getActivity());
        my_child_address_ryl.setAdapter(myAddressListAdapter);
        //接口回调 得到需要修改的id以及信息
        myAddressListAdapter.setOnDefaultAddress(new MyAddressListAdapter.OnDefaultAddress() {
            @Override
            public void defaultAddressClick(int id) {
                defaultId=id;
                //接口回调之后给用户首先显示选中的条目
                List<MyAddress.ResultBean> result1 = myAddressListAdapter.getResult();
                for (int i = 0; i < result1.size(); i++) {
                    MyAddress.ResultBean resultBean = result1.get(i);
                    int id1 = resultBean.getId();
                    if (id1==id){
                        resultBean.setWhetherDefault(1);
                    }
                    else {
                        resultBean.setWhetherDefault(2);
                    }
                }
                //数据修改之后更新适配器
                myAddressListAdapter.notifyDataSetChanged();
            }

            @Override
            public void deleleAddressClick(int id) {

            }

            @Override
            public void updateAddressClick(MyAddress.ResultBean resultBean){
                MyAddressUpdateFragment.getInstace(resultBean);
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.my_framelayout, new MyAddressUpdateFragment(),"update");
                fragmentTransaction.hide(MyAddressFragment.this);
                fragmentTransaction.addToBackStack("update");
                fragmentTransaction.commit();
            }
        });
    }

    //修改默认地址成功
    @Override
    public void onDefault(MyAddress myAddress) {
        defaultId=0;
        ToastUtil.Toast(myAddress.getMessage()+",再次点击完成则退出本界面");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myAddressPresenter.onDestroy();
    }
    /**
     * 返回键重定义
     */
    private void getFocus() {
        //设置焦点联系方式(正确的)
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {
                    FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.my_framelayout,new MyMainFragment());
                    fragmentTransaction.commit();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        getFocus();
    }

}
