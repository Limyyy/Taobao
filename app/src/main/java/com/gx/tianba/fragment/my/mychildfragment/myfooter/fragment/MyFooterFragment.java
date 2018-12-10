package com.gx.tianba.fragment.my.mychildfragment.myfooter.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.myfooter.adapter.MyFooterAdapter;
import com.gx.tianba.fragment.my.mychildfragment.myfooter.bean.MyFooter;
import com.gx.tianba.fragment.my.mychildfragment.myfooter.presenter.MyFooterPre;
import com.gx.tianba.fragment.my.mychildfragment.myfooter.view.IMyFooter;
import com.gx.tianba.fragment.my.mymainfragment.MyMainFragment;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFooterFragment extends Fragment implements IMyFooter {

    private RecyclerView my_child_footer_ryl;
    private MyFooterPre myFooterPre;
    private MyFooterAdapter myFooterAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_footer, container, false);
        initView(view);
        //连接P层请求数据
        myFooterPre = new MyFooterPre(this);

        Login.ResultBean spData = SpUtil.getSpData();
        String sessionId = spData.getSessionId();
        int userId = spData.getUserId();
        myFooterPre.setFooterPreUrl(userId,sessionId,1,5);
        return view;
    }

    //find view
    private void initView(View view) {
        my_child_footer_ryl = (RecyclerView) view.findViewById(R.id.my_child_footer_ryl);
    }

    @Override
    public void myFooterSuccess(MyFooter myFooter) {
        ToastUtil.Toast(myFooter.getMessage());
        List<MyFooter.ResultBean> result = myFooter.getResult();
        myFooterAdapter = new MyFooterAdapter(result, getActivity());
        my_child_footer_ryl.setLayoutManager(new StaggeredGridLayoutManager(2,1));
        my_child_footer_ryl.setAdapter(myFooterAdapter);
    }

    @Override
    public void myFooterFailer(String msg) {
        ToastUtil.Toast(msg);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        myFooterPre.onDestroy();
    }
}
