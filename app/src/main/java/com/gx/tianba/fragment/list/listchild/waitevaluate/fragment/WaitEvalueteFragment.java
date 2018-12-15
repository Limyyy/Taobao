package com.gx.tianba.fragment.list.listchild.waitevaluate.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx.tianba.R;
import com.gx.tianba.fragment.list.listchild.alllist.recyclerviewutil.SpacesItemDecoration;
import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.fragment.list.listchild.waitevaluate.adapter.WaitEvalueteAdapter;
import com.gx.tianba.fragment.list.listchild.waitevaluate.presenter.WaitEvaluetePresenter;
import com.gx.tianba.fragment.list.listchild.waitevaluate.view.IWaitEvalueteView;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.sp.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitEvalueteFragment extends Fragment implements IWaitEvalueteView {
    private RecyclerView list_wait_evaluete_ryl;
    private WaitEvaluetePresenter waitEvaluetePresenter;
    private WaitEvalueteAdapter waitEvalueteAdapter;
    private List<ListBean.OrderListBean> orderListSum=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wait_evaluete, container, false);
        initView(view);
        waitEvaluetePresenter = new WaitEvaluetePresenter(this);
        Login.ResultBean spData = SpUtil.getSpData();
        waitEvaluetePresenter.setPreWaitEvalueteUrl(spData.getUserId(),spData.getSessionId(),3,1,20);
        return view;
    }

    @Override
    public void waitEvalueteSuccess(ListBean listBean) {
        if (listBean.getStatus().equals("0000")){
            orderListSum.clear();
            List<ListBean.OrderListBean> orderList = listBean.getOrderList();
            orderListSum.addAll(orderList);
            list_wait_evaluete_ryl.setLayoutManager(new LinearLayoutManager(getActivity()));
            waitEvalueteAdapter = new WaitEvalueteAdapter(getActivity(), orderListSum);
            list_wait_evaluete_ryl.setAdapter(waitEvalueteAdapter);
            list_wait_evaluete_ryl.addItemDecoration(new SpacesItemDecoration(20));
        }

    }

    private void initView(View view) {
        list_wait_evaluete_ryl = (RecyclerView) view.findViewById(R.id.list_wait_evaluete_ryl);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        waitEvaluetePresenter.onDestroy();
    }
}
