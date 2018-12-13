package com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatename.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.MyPersonFragment;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.bean.MyPersonUpdate;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatename.presenter.MyPersonUpdateNamePre;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatename.view.IMyPersonUpdateNameView;
import com.gx.tianba.fragment.my.mymainfragment.MyMainFragment;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPersonUpdateNameFragment extends Fragment implements IMyPersonUpdateNameView {

    private MyPersonUpdateNamePre myPersonUpdateNamePre;
    private EditText my_person_update_name_edit;
    private TextView my_person_update_name_edit_commit;
    private String edit;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_person_update_name, container, false);
        initView(view);
        myPersonUpdateNamePre = new MyPersonUpdateNamePre(this);
        //点击提交修改
        my_person_update_name_edit_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        return view;
    }

    @Override
    public void onSuccess(MyPersonUpdate myPersonUpdate) {
        ToastUtil.Toast(myPersonUpdate.getMessage());
        if (myPersonUpdate.getMessage().equals("修改成功")){
            //修改之后并且修改SP里面的值
            SpUtil.updateName(edit);
            my_person_update_name_edit.setText("");
        }
        else {
            my_person_update_name_edit.setText("");
        }
    }

    private void initView(View view) {
        my_person_update_name_edit = (EditText) view.findViewById(R.id.my_person_update_name_edit);
        my_person_update_name_edit_commit = (TextView) view.findViewById(R.id.my_person_update_name_edit_commit);
    }

    private void submit() {
        Login.ResultBean spData = SpUtil.getSpData();
        // validate
        edit = my_person_update_name_edit.getText().toString().trim();
        if (TextUtils.isEmpty(edit)) {
            Toast.makeText(getActivity(), "请输入新昵称", Toast.LENGTH_SHORT).show();
            return;
        }
        myPersonUpdateNamePre.setPreMyPersonUpdateNameUrl(spData.getUserId(), spData.getSessionId(), edit);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        myPersonUpdateNamePre.onDestroy();
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
                    fragmentTransaction.replace(R.id.my_framelayout, new MyPersonFragment());
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
