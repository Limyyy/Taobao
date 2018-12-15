package com.gx.tianba.fragment.list.listchild.waitreceive.fragment;


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
import com.gx.tianba.fragment.list.listchild.waitreceive.adapter.WaitReceiveAdapter;
import com.gx.tianba.fragment.list.listchild.waitreceive.presenter.WaitReceivePresenter;
import com.gx.tianba.fragment.list.listchild.waitreceive.view.IWaitReceiveView;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WaitReceiveFragment extends Fragment implements IWaitReceiveView {

    private RecyclerView list_wait_receive_ryl;
    private WaitReceivePresenter waitReceivePresenter;
    private WaitReceiveAdapter waitReceiveAdapter;
    private List<ListBean.OrderListBean> orderListSum=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wait_receive, container, false);
        initView(view);

        waitReceivePresenter = new WaitReceivePresenter(this);
        Login.ResultBean spData = SpUtil.getSpData();
        waitReceivePresenter.setPreWaitReceiveUrl(spData.getUserId(),spData.getSessionId(),2,1,20);
        return view;
    }

    private void initView(View view) {
        list_wait_receive_ryl = (RecyclerView) view.findViewById(R.id.list_wait_receive_ryl);
    }

    @Override
    public void waitReceiveSuccess(ListBean listBean) {
        if (listBean.getStatus().equals("0000")){
            orderListSum.clear();
            List<ListBean.OrderListBean> orderList = listBean.getOrderList();
            orderListSum.addAll(orderList);
            list_wait_receive_ryl.setLayoutManager(new LinearLayoutManager(getActivity()));
            waitReceiveAdapter = new WaitReceiveAdapter(getActivity(), orderListSum);
            list_wait_receive_ryl.setAdapter(waitReceiveAdapter);
            list_wait_receive_ryl.addItemDecoration(new SpacesItemDecoration(30));
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        waitReceivePresenter.onDestroy();
    }
}
