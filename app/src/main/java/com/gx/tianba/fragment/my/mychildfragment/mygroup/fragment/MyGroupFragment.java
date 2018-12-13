package com.gx.tianba.fragment.my.mychildfragment.mygroup.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.mygroup.adapter.MyGroupAdapter;
import com.gx.tianba.fragment.my.mychildfragment.mygroup.bean.MyGroup;
import com.gx.tianba.fragment.my.mychildfragment.mygroup.presenter.MyGroupPresenter;
import com.gx.tianba.fragment.my.mychildfragment.mygroup.view.IMyGroup;
import com.gx.tianba.fragment.my.mymainfragment.MyMainFragment;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyGroupFragment extends Fragment implements IMyGroup {

    private TextView my_group_setting;
    private ImageView my_group_delete;
    private RecyclerView my_group_ryl;
    private MyGroupPresenter myGroupPresenter;
    private MyGroupAdapter myGroupAdapter;
    private ArrayList<MyGroup.ResultBean> resultSum=new ArrayList<>();
    private boolean flag=true;
    private String idsSum=new String();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_group, container, false);
        initView(view);
        //首先请求数据展示数据
        myGroupPresenter = new MyGroupPresenter(this);
        final Login.ResultBean spData = SpUtil.getSpData();
        int userId = spData.getUserId();
        String sessionId = spData.getSessionId();
        myGroupPresenter.setPreMyGroup(userId,sessionId,1,5);
        //点击编辑展开checkbox
        my_group_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag){
                    for (int i = 0; i < resultSum.size(); i++) {
                        resultSum.get(i).setShowCheckBox(true);
                        resultSum.get(i).setChecked(false);
                    }
                    my_group_setting.setText("完成");
                    flag=false;
                    myGroupAdapter.notifyDataSetChanged();
                }
                else {
                    for (int i = 0; i < resultSum.size(); i++) {
                        resultSum.get(i).setShowCheckBox(false);
                        resultSum.get(i).setChecked(false);
                    }
                    my_group_setting.setText("编辑");
                    flag=true;
                    myGroupAdapter.notifyDataSetChanged();
                }
            }
        });
        //点击选中后删除
        my_group_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //是否展开
                if (flag){
                    ToastUtil.Toast("请展开选择再删除");
                }
                else {
                    for (int i = 0; i < resultSum.size(); i++) {
                        boolean checked = resultSum.get(i).isChecked();
                        //选中的话拼接字符串
                        if (checked) {
                            int id = resultSum.get(i).getId();
                            idsSum=id+","+idsSum;
                        }
                    }
                    if (idsSum.equals("")) {
                        ToastUtil.Toast("请至少选择一条再删除");
                    }
                    else {
                        Login.ResultBean spData1 = SpUtil.getSpData();
                        myGroupPresenter.setPreMyGroupDelete(spData.getUserId(),spData.getSessionId(),idsSum);
                    }
                }
            }
        });
        return view;
    }

    private void initView(View view) {
        my_group_setting = (TextView) view.findViewById(R.id.my_group_setting);
        my_group_delete = (ImageView) view.findViewById(R.id.my_group_delete);
        my_group_ryl = (RecyclerView) view.findViewById(R.id.my_group_ryl);
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
                    fragmentTransaction.replace(R.id.my_framelayout, new MyMainFragment());
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
        myGroupPresenter.onDestroy();
    }

    @Override
    public void onSuccess(MyGroup myGroup) {
        resultSum=new ArrayList<>();
        List<MyGroup.ResultBean> result = myGroup.getResult();
        for (int i = 0; i < result.size(); i++) {
            result.get(i).setShowCheckBox(false);
            result.get(i).setChecked(false);
        }
        resultSum.addAll(result);
        myGroupAdapter = new MyGroupAdapter(resultSum, getActivity());
        my_group_ryl.setLayoutManager(new LinearLayoutManager(getActivity(),1,false));
        my_group_ryl.setAdapter(myGroupAdapter);

        //设置选中的条目
        myGroupAdapter.setOnItemClickListner(new MyGroupAdapter.OnItemClickListner() {
            @Override
            public void click(int id) {
                for (int i = 0; i < resultSum.size(); i++) {
                    MyGroup.ResultBean resultBean = resultSum.get(i);
                    int id1 = resultBean.getId();
                    if (id1==id) {
                        boolean checked = resultBean.isChecked();
                        if (checked) {
                            resultBean.setChecked(false);
                        }
                        else {
                            resultBean.setChecked(true);
                        }
                    }
                }
                myGroupAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onFailer(String msg) {
        ToastUtil.Toast(msg);
    }

    @Override
    public void onDeleteSuccess(MyGroup myGroup) {
        ToastUtil.Toast(myGroup.getMessage());
        Login.ResultBean spData = SpUtil.getSpData();
        flag=true;
        my_group_setting.setText("编辑");
        idsSum=new String();
        myGroupPresenter.setPreMyGroup(spData.getUserId(),spData.getSessionId(),1,5);
    }

}
