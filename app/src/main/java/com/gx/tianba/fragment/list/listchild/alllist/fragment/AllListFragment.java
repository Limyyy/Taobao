package com.gx.tianba.fragment.list.listchild.alllist.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx.tianba.R;
import com.gx.tianba.fragment.list.listchild.alllist.adapter.AllListAdapter;
import com.gx.tianba.fragment.list.listchild.alllist.presenter.AlllistPresenter;
import com.gx.tianba.fragment.list.listchild.alllist.recyclerviewutil.SpacesItemDecoration;
import com.gx.tianba.fragment.list.listchild.alllist.view.IAlllistView;
import com.gx.tianba.fragment.list.listchild.bean.ListBean;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllListFragment extends Fragment implements IAlllistView {

    private RecyclerView list_all_list_ryl;
    private AlllistPresenter alllistPresenter;
    private AllListAdapter allListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all_list, container, false);
        initView(view);

        alllistPresenter = new AlllistPresenter(this);
        Login.ResultBean spData = SpUtil.getSpData();
        alllistPresenter.setPreAllListUrl(spData.getUserId(),spData.getSessionId(),0,1,20);
        return view;
    }

    @Override
    public void alllistSuccess(ListBean listBean) {
        if (listBean.getStatus().equals("0000")){
            list_all_list_ryl.setLayoutManager(new LinearLayoutManager(getActivity()));
            List<ListBean.OrderListBean> orderList = listBean.getOrderList();
            allListAdapter = new AllListAdapter(orderList, getActivity());
            list_all_list_ryl.setAdapter(allListAdapter);
            list_all_list_ryl.addItemDecoration(new SpacesItemDecoration(30));
        }
        else {
            ToastUtil.Toast(listBean.getMessage());
        }
    }

    private void initView(View view) {
        list_all_list_ryl = (RecyclerView) view.findViewById(R.id.list_all_list_ryl);
    }
}
