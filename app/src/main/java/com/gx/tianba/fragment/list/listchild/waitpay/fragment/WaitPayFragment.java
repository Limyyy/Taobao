package com.gx.tianba.fragment.list.listchild.waitpay.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx.tianba.R;
import com.gx.tianba.fragment.list.listchild.alllist.adapter.AllListAdapter;
import com.gx.tianba.fragment.list.listchild.alllist.recyclerviewutil.SpacesItemDecoration;
import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.fragment.list.listchild.waitpay.presenter.WaitPayPresenter;
import com.gx.tianba.fragment.list.listchild.waitpay.view.IWaitPayView;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitPayFragment extends Fragment implements IWaitPayView {

    private RecyclerView list_wait_pay_ryl;
    private WaitPayPresenter waitPayPrgesenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wait_pay, container, false);
        initView(view);

        waitPayPrgesenter = new WaitPayPresenter(this);
        Login.ResultBean spData = SpUtil.getSpData();
        waitPayPrgesenter.setPreWaitPayUrl(spData.getUserId(),spData.getSessionId(),1,1,20);
        return view;
    }

    private void initView(View view) {
        list_wait_pay_ryl = (RecyclerView) view.findViewById(R.id.list_wait_pay_ryl);
    }

    @Override
    public void waitPaySuccess(ListBean listBean) {
        if (listBean.getStatus().equals("0000")){
            list_wait_pay_ryl.setLayoutManager(new LinearLayoutManager(getActivity()));
            List<ListBean.OrderListBean> orderList = listBean.getOrderList();
            AllListAdapter allListAdapter = new AllListAdapter(orderList, getActivity());
            list_wait_pay_ryl.setAdapter(allListAdapter);
            list_wait_pay_ryl.addItemDecoration(new SpacesItemDecoration(30));
        }
        else {
            ToastUtil.Toast(listBean.getMessage());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        waitPayPrgesenter.onDestroy();
    }
}
