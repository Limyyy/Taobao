package com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatepwd.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.MyPersonFragment;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.bean.MyPersonUpdate;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatepwd.presenter.MyPresonUpdatePwdPre;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatepwd.view.IMyPresonUpdatePwdView;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.sp.SpUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPresonUpdatePwdFragment extends Fragment implements IMyPresonUpdatePwdView {

    private TextView my_person_update_pwd_edit_commit;
    private MyPresonUpdatePwdPre myPresonUpdatePwdPre;
    private String editOld;
    private String editNew;
    private EditText my_person_update_pwd_edit_old;
    private EditText my_person_update_pwd_edit_new;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_preson_update_pwd, container, false);
        initView(view);

        myPresonUpdatePwdPre = new MyPresonUpdatePwdPre(this);
        //点击提交修改
        my_person_update_pwd_edit_commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });
        return view;
    }

    private void initView(View view) {
        my_person_update_pwd_edit_commit = (TextView) view.findViewById(R.id.my_person_update_pwd_edit_commit);
        my_person_update_pwd_edit_old = (EditText) view.findViewById(R.id.my_person_update_pwd_edit_old);
        my_person_update_pwd_edit_new = (EditText) view.findViewById(R.id.my_person_update_pwd_edit_new);
    }

    private void submit() {
        Login.ResultBean spData = SpUtil.getSpData();
        editOld = my_person_update_pwd_edit_old.getText().toString().trim();
        if (TextUtils.isEmpty(editOld)) {
            Toast.makeText(getActivity(), "请输入旧密码", Toast.LENGTH_SHORT).show();
            return;
        }
        editNew = my_person_update_pwd_edit_new.getText().toString().trim();
        if (TextUtils.isEmpty(editNew)) {
            Toast.makeText(getActivity(), "请输入新密码", Toast.LENGTH_SHORT).show();
            return;
        }
        myPresonUpdatePwdPre.setPreMyPersonUpdatePwdUrl(spData.getUserId(), spData.getSessionId(), editOld,editNew);
    }

    @Override
    public void onSuccess(MyPersonUpdate myPersonUpdate) {
        ToastUtil.Toast(myPersonUpdate.getMessage());
        if (myPersonUpdate.getMessage().equals("修改成功")) {
            //修改之后并且修改SP里面的值
            SpUtil.updatePwd(editNew);
            my_person_update_pwd_edit_old.setText("");
            my_person_update_pwd_edit_new.setText("");
        } else {
            my_person_update_pwd_edit_old.setText("");
            my_person_update_pwd_edit_new.setText("");
        }
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
