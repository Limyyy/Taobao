package com.gx.tianba.fragment.search.fragment;


import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx.tianba.R;
import com.gx.tianba.fragment.search.adapter.MyCircleAdapter;
import com.gx.tianba.fragment.search.bean.MyCircle;
import com.gx.tianba.fragment.search.presenter.MyCirclePresenter;
import com.gx.tianba.fragment.search.view.IMyCircle;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements IMyCircle {

    private RecyclerView search_rly;
    private MyCirclePresenter myCirclePresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initView(view);
        myCirclePresenter = new MyCirclePresenter(this);
        Login.ResultBean spData = SpUtil.getSpData();
        myCirclePresenter.setPreMyCircleUrl(spData.getUserId(),spData.getSessionId(),1,10);
        search_rly.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myCirclePresenter.onDestroy();
    }

    private void initView(View view) {
        search_rly = (RecyclerView) view.findViewById(R.id.search_rly);
    }


    @Override
    public void onSuccess(MyCircle myCircle) {
        List<MyCircle.ResultBean> result = myCircle.getResult();
        MyCircleAdapter myCircleAdapter = new MyCircleAdapter(result, getActivity());
        search_rly.setAdapter(myCircleAdapter);
    }

    @Override
    public void onFailer(String msg) {
        ToastUtil.Toast(msg);
    }
}
