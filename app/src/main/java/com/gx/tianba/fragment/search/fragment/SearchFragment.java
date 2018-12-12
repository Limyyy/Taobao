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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment implements IMyCircle {

    private RecyclerView search_rly;
    private MyCirclePresenter myCirclePresenter;
    private ArrayList<MyCircle.ResultBean> resultSum=new ArrayList<>();
    private MyCircleAdapter myCircleAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        initView(view);
        myCirclePresenter = new MyCirclePresenter(this);
        Login.ResultBean spData = SpUtil.getSpData();
        myCirclePresenter.setPreMyCircleUrl(spData.getUserId(),spData.getSessionId(),1,20);
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
        resultSum=new ArrayList<>();
        List<MyCircle.ResultBean> result = myCircle.getResult();
        resultSum.addAll(result);
        myCircleAdapter = new MyCircleAdapter(resultSum, getActivity());
        search_rly.setAdapter(myCircleAdapter);

        //点赞
        myCircleAdapter.setOnItemHandClickListner(new MyCircleAdapter.OnItemHandClickListner() {
            @Override
            public void click(int id,int whetherGreatId) {
                //没有点过赞的去点赞
                if (whetherGreatId==2){
                    //设置小手
                    List<MyCircle.ResultBean> result1 = myCircleAdapter.getResult();
                    for (int i = 0; i < result1.size(); i++) {
                        MyCircle.ResultBean resultBean = result1.get(i);
                        if (id==resultBean.getId()){
                            resultBean.setWhetherGreat(1);
                            resultBean.setGreatNum(resultBean.getGreatNum()+1);
                        }
                    }
                    myCircleAdapter.notifyDataSetChanged();
                    //去请求数据点赞
                    Login.ResultBean spData = SpUtil.getSpData();
                    myCirclePresenter.setPreGreatCircleUrl(spData.getUserId(),spData.getSessionId(),id);
                }
                //点过赞的去取消点赞
                else {
                    //设置小手
                    List<MyCircle.ResultBean> result1 = myCircleAdapter.getResult();
                    for (int i = 0; i < result1.size(); i++) {
                        MyCircle.ResultBean resultBean = result1.get(i);
                        if (id==resultBean.getId()){
                            resultBean.setWhetherGreat(2);
                            resultBean.setGreatNum(resultBean.getGreatNum()-1);
                        }
                    }
                    myCircleAdapter.notifyDataSetChanged();
                    //去请求数据点赞
                    Login.ResultBean spData = SpUtil.getSpData();
                    myCirclePresenter.setPreNoGreatCircleUrl(spData.getUserId(),spData.getSessionId(),id);
                }

            }
        });
    }

    @Override
    public void onFailer(String msg) {
        ToastUtil.Toast(msg);
    }


    @Override
    public void onGreatSuccess(MyCircle myCircle) {
        ToastUtil.Toast(myCircle.getMessage());
    }

    @Override
    public void onNoGreatSuccess(MyCircle myCircle) {
        ToastUtil.Toast(myCircle.getMessage());
    }
}
