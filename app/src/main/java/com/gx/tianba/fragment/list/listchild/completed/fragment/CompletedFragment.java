package com.gx.tianba.fragment.list.listchild.completed.fragment;


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
import com.gx.tianba.fragment.list.listchild.completed.presenter.CompletedPresenter;
import com.gx.tianba.fragment.list.listchild.completed.view.ICompletedView;
import com.gx.tianba.fragment.list.listchild.waitevaluate.adapter.WaitEvalueteAdapter;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CompletedFragment extends Fragment implements ICompletedView {

    private CompletedPresenter completedPresenter;
    private List<ListBean.OrderListBean> orderListSum = new ArrayList<>();
    private RecyclerView list_completed_ryl;
    private WaitEvalueteAdapter waitEvalueteAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_completed, container, false);
        completedPresenter = new CompletedPresenter(this);
        Login.ResultBean spData = SpUtil.getSpData();
        completedPresenter.setPreCompletedList(spData.getUserId(), spData.getSessionId(), 9, 1, 20);
        initView(view);
        return view;
    }

    //请求列表成功
    @Override
    public void onCompletedSuccess(ListBean listBean) {
        if (listBean.getStatus().equals("0000")) {
            orderListSum.clear();
            List<ListBean.OrderListBean> orderList = listBean.getOrderList();
            list_completed_ryl.setLayoutManager(new LinearLayoutManager(getActivity()));
            orderListSum.addAll(orderList);
            waitEvalueteAdapter = new WaitEvalueteAdapter(getActivity(), orderListSum);
            list_completed_ryl.setAdapter(waitEvalueteAdapter);
            list_completed_ryl.addItemDecoration(new SpacesItemDecoration(30));
        } else {
            ToastUtil.Toast(listBean.getMessage());
        }
    }

    //删除成功
    @Override
    public void onDeleteSuccess(ListBean listBean) {

    }

    private void initView(View view) {
        list_completed_ryl = (RecyclerView) view.findViewById(R.id.list_completed_ryl);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        completedPresenter.onDestroy();
    }
}
