package com.gx.tianba.fragment.my.mychildfragment.myperson.fragment;


import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.gx.tianba.R;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatename.fragment.MyPersonUpdateNameFragment;
import com.gx.tianba.fragment.my.mychildfragment.myperson.fragment.update.updatepwd.fragment.MyPresonUpdatePwdFragment;
import com.gx.tianba.fragment.my.mymainfragment.MyMainFragment;
import com.gx.tianba.login.bean.Login;
import com.gx.tianba.util.ToastUtil;
import com.gx.tianba.util.fresco.FrescoUtil;
import com.gx.tianba.util.sp.SpUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyPersonFragment extends Fragment {

    private SimpleDraweeView chile_my_person_image;
    private TextView chile_my_person_name;
    private TextView chile_my_person_password;
    private RelativeLayout chile_my_person_image_up;
    private RelativeLayout chile_my_person_name_update;
    private RelativeLayout chile_my_person_password_update;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_person, container, false);
        initView(view);
        //得到数据
        Login.ResultBean bean = SpUtil.getSpData();
        //设置数据
        //图片
        FrescoUtil.load(bean.getHeadPic(), chile_my_person_image);
        //名字
        chile_my_person_name.setText(bean.getNickName());
        //密码
        chile_my_person_password.setText(bean.getPassword());

        //点击昵称修改昵称
        chile_my_person_name_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.my_framelayout, new MyPersonUpdateNameFragment(),"UpdateName");
                fragmentTransaction.addToBackStack("UpdateName");
                fragmentTransaction.commit();
            }
        });

        //点击修改密码
        chile_my_person_password_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction fragmentTransaction = getActivity().getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.my_framelayout, new MyPresonUpdatePwdFragment(),"UpdatePwd");
                fragmentTransaction.addToBackStack("UpdatePwd");
                fragmentTransaction.commit();
            }
        });
        return view;
    }

    //find view
    private void initView(View view) {
        chile_my_person_image = (SimpleDraweeView) view.findViewById(R.id.chile_my_person_image);
        chile_my_person_name = (TextView) view.findViewById(R.id.chile_my_person_name);
        chile_my_person_password = (TextView) view.findViewById(R.id.chile_my_person_password);
        chile_my_person_image_up = (RelativeLayout) view.findViewById(R.id.chile_my_person_image_up);
        chile_my_person_name_update = (RelativeLayout) view.findViewById(R.id.chile_my_person_name_update);
        chile_my_person_password_update = (RelativeLayout) view.findViewById(R.id.chile_my_person_password_update);
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
}
